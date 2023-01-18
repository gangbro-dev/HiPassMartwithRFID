import serial
import threading
import time

PORT = "COM6"                               # 포트 번호
baud = 9600                                 # 보드레이트
ser = serial.Serial(PORT, baud, timeout=0.1)  # serial 통신 세팅


def main():
    thread = threading.Thread(target=readthread, args=(ser,))   # 통신을 다른 코드와 병렬처리 하기 위한 스레드 생성
    thread.start(daemon=True)                                              # 스레드 시작

    # 1초마다 데이터 전송
    while True:
        data = input().strip()
        if data == "serial exit": # 종료 명령어
            break
        if data:
            ser.write(bytes(input().strip(), 'utf-8'))
        time.sleep(0.5)


# 데이터 받는 함수 => 스레드 생성해서 병렬로 처리 예정
def readthread(ser):

    # 스레드가 종료될 때 까지 진행
    while True:
        if ser.readable():
            res = ser.readline()
            if res:
                print(res.decode('utf-8').strip())

    ser.close()

main()
