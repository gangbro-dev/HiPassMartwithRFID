import serial
import threading
import time
import requests

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
            ser.write(data.encode())
        time.sleep(0.5)


# 데이터 받는 함수 => 스레드 생성해서 병렬로 처리 예정
def readthread(ser):
    # 스레드가 종료될 때 까지 진행
    while True:
        time.sleep(1)
        if ser.readable():
            data_line = set()
            tag_uid = dict()
            data = ser.readline()
            # print("Received data", list(map(hex, data)))
            while len(data) > 3:
                uid = ''
                received_command = [0, 0]
                if data[0] == 0x33:                         # 시작바이트 : 0x33 이어야 정상적인 요청
                    received_command[0] = data[1]           # 요청 총 길이
                    received_command[1] = data[2]           # 요청 코드
                    data_now = data[:received_command[0]]   # 요청 1개 추출
                    data = data[received_command[0]:]       # 나머지 저장
                else:                                       # 시작바이트로 시작하는 요청이 아닌 경우 시작바이트 찾기
                    x = data.find(0x33)
                    if x > 0:
                        print(data[:x])
                        data = data[x:]
                        continue
                    else:
                        break
                if received_command[1] == 0x3B:             # RFID UID 1개 읽기
                    for byte in data_now[3:-1]:
                        uid += hex(byte)[2:]
                    if uid in tag_uid.keys():
                        tag_uid[uid] += 1
                    else:
                        tag_uid[uid] = 1
                elif received_command[1] == 0x1B:           # 읽기 Stay 모드
                    print("Reading Stay Mode ON")
                    continue
                elif received_command[1] == 0x2B:           # 읽기 Continue 모드
                    print("Reading Continue Mode ON")
                    continue
                elif received_command[1] == 0x1C:           # RF ON/OFF
                    print("RF Setting Change")
                    continue
                elif received_command[1] == 0x2C:           # RFID Reader 동작 확인
                    print("RFID Reader Check OK")
                    continue
                elif received_command[1] == 0x1A:             # RFID 1개 읽기 (UID를 통해서 조회)
                    num = received_command[0]
                    fn, nb = data_now[3:4]
                    uid = data_now[5:13]
                    bd = data_now[13:-1]
                else:
                    print("Unknown Request")
                    continue
                if data_now[-1] == 0x99:                    # 종료 바이트
                    for u in tag_uid.keys():
                        if tag_uid[uid] > 3:
                            data_line.add(uid)
                else:
                    print("Error Request")
            if data_line:
                print(data_line)
                request_data = {
                    "kioskId": 1,
                    "products": data_line
                }
                r = requests.post("http://himart.shop/api/iot/rfid", request_data).json()
                print(r)
    ser.close()

main()
