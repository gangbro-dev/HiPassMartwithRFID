import requests


ContextPath = "http://192.168.30.173:8080/"


def test_request():
    userPk = "1"
    URL = ContextPath + "api/user/" + userPk
    r = requests.get(URL)
    print(r.json())


def RFID_DataRequest(Kiosk_ID, RFID_UID_LIST):
    URL = ContextPath + "api/iot/rfid"
    payload = {"data": RFID_UID_LIST}
    req = requests.post(URL, data=payload)
    print(req)


# test_request()
RFID_DataRequest(1, ["AAAAAAAAAAAAAAAA", "BBBBBBBBBBBBBBBB"])
