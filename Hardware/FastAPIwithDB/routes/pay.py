from asyncio import run

from fastapi import APIRouter, Request

router = APIRouter(
    prefix="/api/pay", # url 앞에 고정적으로 붙는 경로추가
) # Route 분리


@router.post("/member")
def 키오스크_회원_결제요청(request: Request,):
    data = run(request.json())
    id = data["id"]
    kioskId = data["kioskId"] # 필요없는듯
    cardId = data["cardId"]
    date = data["date"]
    priceSum = data["priceSum"]
    # 자료형 2개의 Key:value가 안맞음

@router.post("/guest")
def 키오스크_비회원_결제요청(request: Request,):
    data = run(request.json())
    kioskId = data["kioskId"] # 필요없는듯
    date = data["date"]
    cardNum = data["cardNum"]
    password = data["password"] # ?
    priceSum = data["priceSum"]
    # 자료형 2개의 Key:value가 안맞음