from sqlalchemy import Column, String, Integer, ForeignKey, DATE
from sqlalchemy.orm import DeclarativeBase
from sqlalchemy.orm import Mapped, mapped_column, relationship
from typing import Optional


class Base(DeclarativeBase):
    pass


class Product_Kiosk(Base):
    __tablename__ = "Product_Kiosk"
    id: Mapped[int] = mapped_column(primary_key=True)
    product_id: Mapped[int] = mapped_column(Integer)
    name: Mapped[str] = mapped_column(String)
    price: Mapped[int] = mapped_column(Integer)
    rfid: Mapped[str] = mapped_column(String, nullable=True)
    barcode: Mapped[str] = mapped_column(String, nullable=True)
    image: Mapped[str] = mapped_column(String, nullable=True)
    shopping = relationship("Shopping")


class Shopping(Base):
    __tablename__ = "Shopping"

    id = Column(Integer, primary_key=True, index=True)
    count = Column(Integer)
    price = Column(Integer)
    date = Column(DATE)
    product_kiosk_id = Column(Integer, ForeignKey("Product_Kiosk.id"))
