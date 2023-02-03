from sqlalchemy.orm import Session
from db.models.model import Shopping, Product_Kiosk
from db.session import engine


def copy_products(products: list, db: Session):
    i = 0
    for prd in products:
        product = Product_Kiosk(
            product_id= prd['product_id'],
            name= prd['name'],
            price= prd['price'],
            RFID= prd['RFID'],
            barcode= prd['barcode'],
            image= prd['image']
        )
        db.add(product)
        i += 1
    db.commit()
    return i


def create_product(products: list, db: Session):
    for prd in products:
        product = Product_Kiosk(
            product_id= prd['product_id'],
            name= prd['name'],
            price= prd['price'],
            RFID= prd['RFID'],
            barcode= prd['barcode'],
            image= prd['image']
        )
        db.add(product)
    db.commit()


def create_product(ids: list, db: Session):
    for id in ids:
        prd = db.query(Product_Kiosk).get(product_id=id)
        db.delete(prd)
    db.commit()