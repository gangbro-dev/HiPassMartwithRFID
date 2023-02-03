from routes.models import RFIDList, BarcodeList, CardInfo
from fastapi import APIRouter, Depends
from sqlalchemy.orm import Session
# from db.connection import get_db
# from crud import crud_test
# from apis import test # main logic

router = APIRouter(
    prefix="/api/kiosk", # url 앞에 고정적으로 붙는 경로추가
) # Route 분리


# @router.get("/test_route") # Route Path
# def test_index(db: Session = Depends(get_db)):
	
# 	res = test.test_index(db=db) # apis 호출
	
# 	return {
#         "res" : res,
# 	} # 결과


@router.post("/rfid")
def 장바구니_상품담기_RFID(RFIDList: RFIDList):
    return {"ans": "일단 있다고 치자"}


@router.post("/barcode")
def 장바구니_상품담기_Barcode(BarcodeList: BarcodeList):
    return {"ans": "일단 있다고 치자"}


@router.post("/cardinfo")
def 카드정보전송(CardInfo: CardInfo):
    return {"ans": "일단 있다고 치자"}
