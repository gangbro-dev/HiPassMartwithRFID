from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from core.config import settings
from db.models.model import Base


SQLALCHEMY_DATABASE_URL = settings.DATABASE_URL
engine = create_engine(SQLALCHEMY_DATABASE_URL)

Base.metadata.create_all(engine)
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)
