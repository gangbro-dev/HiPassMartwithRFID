# 트리거 만들기 -> 어케함? 진짜모름
# write 형태 짜두기 -> write에 뭐 적어놓을거임? 나도 모름

import sys
import time
import serial
import threading
import queue

class Protocol(object):
    """\
    Protocol as used by the ReaderThread. This base class provides empty
    implementations of all methods.
    """

    def connection_made(self, transport):
        """Called when reader thread is started"""

    def data_received(self, data):
        """Called with snippets received from the serial port"""

    def connection_lost(self, exc):
        """\
        Called when the serial port is closed or the reader loop terminated
        otherwise.
        """
        if isinstance(exc, Exception):
            raise exc


class ReaderThread(threading.Thread):
    """\
    Implement a serial port read loop and dispatch to a Protocol instance (like
    the asyncio.Protocol) but do it with threads.
    Calls to close() will close the serial port but it is also possible to just
    stop() this thread and continue the serial port instance otherwise.
    """

    def __init__(self, serial_instance, protocol_factory):
        """\
        Initialize thread.
        Note that the serial_instance' timeout is set to one second!
        Other settings are not changed.
        """
        super(ReaderThread, self).__init__()
        self.daemon = True
        self.serial = serial_instance
        self.protocol_factory = protocol_factory
        self.alive = True
        self._lock = threading.Lock()
        self._connection_made = threading.Event()
        self.protocol = None

    def stop(self):
        """Stop the reader thread"""
        self.alive = False
        if hasattr(self.serial, 'cancel_read'):
            self.serial.cancel_read()
        self.join(2)

    def run(self):
        """Reader loop"""
        if not hasattr(self.serial, 'cancel_read'):
            self.serial.timeout = 1
        self.protocol = self.protocol_factory()
        try:
            self.protocol.connection_made(self)
        except Exception as e:
            self.alive = False
            self.protocol.connection_lost(e)
            self._connection_made.set()
            return
        error = None
        self._connection_made.set()
        while self.alive and self.serial.is_open:
            try:
                # read all that is there or wait for one byte (blocking)
                data = self.serial.read(self.serial.in_waiting or 1)
            except serial.SerialException as e:
                # probably some I/O problem such as disconnected USB serial
                # adapters -> exit
                error = e
                break
            else:
                if data:
                    # make a separated try-except for called used code
                    try:
                        self.protocol.data_received(data)
                    except Exception as e:
                        error = e
                        break
        self.alive = False
        self.protocol.connection_lost(error)
        self.protocol = None

    def write(self, data):
        """Thread safe writing (uses lock)"""
        with self._lock:

            print(data)
            self.serial.write(data)

    def close(self):
        """Close the serial port and exit reader thread (uses lock)"""
        # use the lock to let other threads finish writing
        with self._lock:
            # first stop reading, so that closing can be done on idle port
            self.stop()
            self.serial.close()

    def connect(self):
        """
        Wait until connection is set up and return the transport and protocol
        instances.
        """
        if self.alive:
            self._connection_made.wait()
            if not self.alive:
                raise RuntimeError('connection_lost already called')
            return (self, self.protocol)
        else:
            raise RuntimeError('already stopped')

    # - -  context manager, returns protocol

    def __enter__(self):
        """\
        Enter context handler. May raise RuntimeError in case the connection
        could not be created.
        """
        self.start()
        self._connection_made.wait()
        if not self.alive:
            raise RuntimeError('connection_lost already called')
        return self.protocol

    def __exit__(self, exc_type, exc_val, exc_tb):
        """Leave context: close port"""
        self.close()


class rawProtocal(Protocol):
    reading = False
    data_line = []
    received_command = [0, 0]

    # 연결 시작시 발생
    def connection_made(self, transport):
        self.transport = transport
        self.running = True

    # 연결 종료시 발생
    def connection_lost(self, exc):
        self.transport = None

    # 데이터가 들어오면 이곳에서 처리함.
    def data_received(self, data):
        print(data)
        while data:
            if data[0] == 0x33:                                 # 시작바이트 : 0x33 이어야 정상적인 요청
                self.reading = True
                self.received_command[0] = data[1]              # 요청 총 길이
                self.received_command[1] = data[2]              # 요청 코드
                data_now = data[:self.received_command[0]]      # 요청 1개 추출
                data = data[self.received_command[0]:]          # 나머지 저장
                print('start')
            else:                                               # 시작바이트로 시작하는 요청이 아닌 경우 시작바이트 찾기
                x = data.find(0x33)
                if x > 0:
                    data = data[x:]
                    continue
                else:
                    return
            print('start_end')
            if self.received_command[1] == 0x3B:                # RFID UID 1개 읽기
                print('uid check', data_now[-1])
                for byte in data_now[3:-1]:
                    self.data_line.append(str(byte))
            elif self.received_command[1] == 0x1A:              # RFID FULL INFO 1개 읽기
                for byte in data_now[3:-1]:
                    self.data_line.append(str(byte))            # FN, NB, BD 내용 추가 해야 함
                    pass
                    pass
                    pass
            elif self.received_command[1] == 0x1B:              # 읽기 Stay 모드
                self.data_line.append("Reading Stay Mode ON")
            elif self.received_command[1] == 0x2B:              # 읽기 Continue 모드
                self.data_line.append("Reading Continue Mode ON")
            elif self.received_command[1] == 0x2B:              # RFID Reader 동작 확인
                self.data_line.append("RFID Reader Check OK")
            else:
                print("Unknown Request")
                continue
            if data_now[-1] == 0x99:                            # 종료 바이트
                print('end check')
                print(" ".join(self.data_line))                  # 통신 종료 시 데이터 출력
            else:
                print("Error Request")
            self.reading = False
            self.data_line = []
            self.received_command = [0, 0]
            continue

    # 데이터 보낼 때 함수
    def write(self, data):
        print(data)
        self.transport.write(data)

    # 종료 체크
    def isDone(self):
        return self.running


PORT = "COM6"                               # 포트 번호
baud = 9600                                 # 보드레이트
# baud = 115200                             # RFID 보드레이트 115200 기본값
ser = serial.Serial(PORT, baud, timeout=0.1)  # serial 통신 세팅
time.sleep(1)
# 쓰레드 시작
with ReaderThread(ser, rawProtocal) as p:
    while p.isDone():
        time.sleep(1)
