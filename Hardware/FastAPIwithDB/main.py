from fastapi import FastAPI
from routes.kiosk import router as kiosk_router

app = FastAPI() # FastAPI 모듈
app.include_router(kiosk_router) # 다른 route파일들을 불러와 포함시킴

@app.get("/")
def index():
    return {
        "response": "API test OK",
    }
