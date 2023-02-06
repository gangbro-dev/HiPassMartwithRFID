from sqlalchemy.orm import Session

from db.models.model import Product_Kiosk, Shopping


def copy_products(products: list, db: Session):
    db.query(Product_Kiosk).all()
    for prd in products:
        product = Product_Kiosk(
            product_id= prd['productId'],
            name= prd['name'],
            price= prd['price'],
            rfid= prd['rfid'],
            barcode= prd['barcode'],
            image= prd['image']
        )
        db.add(product)
    db.commit()
    i = len(db.query(Product_Kiosk).all())
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
    i = len(products)
    db.commit()
    return i


def delete_product(ids: list, db: Session):
    for id in ids:
        prd = db.query(Product_Kiosk).get(product_id=id)
        db.delete(prd)
    i = len(ids)
    db.commit()
    return i
