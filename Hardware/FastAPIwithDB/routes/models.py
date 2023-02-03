from typing import List
from pydantic import BaseModel


class RFIDList(BaseModel):
    kioskId: int
    rfid: list


class BarcodeList(BaseModel):
    kioskId: int
    barcode: str


class CardInfo(BaseModel):
    defaultCardId: int
    cardList: List[dict]
