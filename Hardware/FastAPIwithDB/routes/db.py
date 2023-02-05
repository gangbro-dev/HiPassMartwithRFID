from asyncio import run

from fastapi import APIRouter, Depends, Request
from sqlalchemy.orm import Session

from crud.crud import copy_products, create_product, delete_product
from db.connection import get_db
from routes.models import ProductIds, ProductList

router = APIRouter(
    prefix="/api/db", # url 앞에 고정적으로 붙는 경로추가
) # Route 분리


@router.post("")
def 상품_DB_추가(request: Request, product: ProductList, db: Session = Depends(get_db)):
    data = run(request.json())['product']
    count = create_product(data, db)
    return {
        "message": f"{count} data added",
        "count": count
        }


@router.delete("")
def 상품_db_삭제(request: Request, ProductIds: ProductIds, db: Session = Depends(get_db)):
    data = run(request.json())['productId']
    count = delete_product(data, db)
    return {
        "message": f"{count} data deleted",
        "count": count
    }


@router.post("/all")
def 상품_DB_이식(request: Request, product: ProductList, db: Session = Depends(get_db)):
    data = run(request.json())['product']
    count = copy_products(data, db)
    return {
        "message": f"{count} data in database",
        "count": count
        }

