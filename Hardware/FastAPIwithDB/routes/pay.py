from asyncio import run
from routes.models import CardId, GuestCardInfo
from core.config import BASE_URL, KIOSK_ID
from fastapi import APIRouter, Request
from datetime import datetime
from functions.test import cardReader
import requests

router = APIRouter(
    prefix="/api/pay", # url 앞에 고정적으로 붙는 경로추가
) # Route 분리


@router.post("/member")
def 키오스크_회원_결제요청(request: Request, cardId: CardId):
    data = run(request.json())
    cardId = data["cardId"]
    # testdata
    userId = 1234
    kioskId = KIOSK_ID
    date = datetime.now()
    shopping = [
        {
            "productId": 1234, 
            "itemName" : "노트북",
            "count" :3,
            "price": 20000,
        },
		{
			"productId": 134,
			"itemName" : "과자",
			"count" :5,
			"price": 30000,
		},
    ]
    priceSum = 0
    for product in shopping:
        priceSum += product["price"] * product["count"]
    # spring 요청
    url = BASE_URL + "/api/pay/member"
    payload = {
        "userId" : userId,
        "kioskId" : kioskId,
        "cardId" : cardId,
        "date" : date,
        "priceSum" : priceSum,
        "shopping" : shopping
    }
    print(requests.post(url, data=payload).text)

@router.post("/guest")
def 키오스크_비회원_결제요청(request: Request, cardInfo: GuestCardInfo):
    kioskId = KIOSK_ID
    date = datetime.now()
    shopping = [
        {
            "productId": 1234, 
            "itemName" : "노트북",
            "count" :3,
            "price": 20000,
        },
		{
			"productId": 134,
			"itemName" : "과자",
			"count" :5,
			"price": 30000,
		},
    ]
    priceSum = 0
    for product in shopping:
        priceSum += product["price"] * product["count"]
    # spring 요청
    url = BASE_URL + "/api/pay/guest"
    payload = {
        "kioskId" : kioskId,
        "cardInfo" : cardInfo,
        "date" : date,
        "priceSum" : priceSum,
        "shopping" : shopping
    }
    requests.post(url, data=payload)