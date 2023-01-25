import serial
import threading
import time

PORT = "COM6"                               # 포트 번호
baud = 9600                                 # 보드레이트
# baud = 115200                             # RFID 보드레이트 115200 기본값
ser = serial.Serial(PORT, baud, timeout=0.1)  # serial 통신 세팅


def main():
    thread = threading.Thread(target=readthread, args=(ser,), daemon=True)   # 통신을 다른 코드와 병렬처리 하기 위한 스레드 생성
    thread.start()                                              # 스레드 시작

    # 0.5초마다 데이터 전송
    while True:
        # RFID 통신 바이트 정리
        # data = 0x3304C299 # RFID 확인 -> 0x33052C0199 정상응답
        # data = 0x3304B199 # Reading Stay
        # data = 0x3304B299 # Reading Nonstop

        data = input().strip()
        if data == "serial exit": # 종료 명령어
            break
        if data:
            ser.write(data)
        time.sleep(0.5)


# 데이터 받는 함수 => 스레드 생성해서 병렬로 처리 예정
def readthread(ser):

    # 스레드가 종료될 때 까지 진행
    while True:
        if ser.readable():
            res = ser.readline()
            if res:
                print(res.decode('utf-8').strip())

    while True:
        if ser.readable():
            res = ser.readline()
            if res:
                print(res.strip())

    ser.close()

main()
