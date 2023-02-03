from routes.models import Products
from fastapi import APIRouter, Depends, Request
from sqlalchemy.orm import Session
from crud.crud import copy_products
from db.connection import get_db
# from apis import test # main logic

router = APIRouter(
    prefix="/api/pay", # url 앞에 고정적으로 붙는 경로추가
) # Route 분리


@router.post("/product")
def 상품_DB_이식(request: Request, products: Products, db: Session = Depends(get_db)):
    data = request.json()
    count = copy_products(data, db)
    return {"response": f"{count} data added"}
    