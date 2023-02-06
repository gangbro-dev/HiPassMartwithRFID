from sqlalchemy.orm import Session
from crud import crud_test

def cardReader():
    # 테스트용 데이터 반환 함수
    data = {
        "cardholder_name": "John Doe",
        "card_number": "1234567890123456",
        "expiration_date": "12/25",
        "issuing_agency": "Visa",
        "card_logo": "Visa",
        "encrypted_payment_info": "dfg857jdfg87jdkljg87dfg87"
    }

    return data