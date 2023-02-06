from typing import List, Optional

from pydantic import BaseModel


class RFIDList(BaseModel):
    kioskId: int
    rfid: list


class BarcodeList(BaseModel):
    kioskId: int
    barcode: str

class Card(BaseModel):
    cardId: int
    cardName: str
    cardNo: str


class CardList(BaseModel):
    defaultCardId: int
    cardList: List[Card]


class Product(BaseModel):
    product_id: int
    name: str
    price: int
    rfid: Optional[str] = None
    barcode: Optional[str] = None
    image: Optional[str] = None


class ProductList(BaseModel):
    product: List[Product]


class ProductIds(BaseModel):
    productId: List[int]