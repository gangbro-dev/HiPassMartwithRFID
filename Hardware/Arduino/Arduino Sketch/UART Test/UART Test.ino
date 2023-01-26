#include <SoftwareSerial.h>

#define BT_RXD 4
#define BT_TXD 5
#define LED 2

SoftwareSerial HM10(BT_RXD, BT_TXD);  // RX핀(7번)은 HM10의 TX에 연결
                                        // TX핀(8번)은 HM10의 RX에 연결  
void setup() {
  Serial.begin(9600);
  pinMode(LED, OUTPUT);    // LED를 출력으로 설정
  HM10.begin(9600); 
}

void loop() {
  if (HM10.available()){       
    char h =(char)HM10.read();
    Serial.print(h);
    if(h == 'o') {                 // 알파벳 소문자 'o'를 입력하면
      digitalWrite(LED, HIGH);     // LED가 점등된다
      Serial.write("ON");
    }              
    if(h == 'f') {                // 알파벳 소문자 'f'를 입력하면
      digitalWrite(LED, LOW);      // LED가 소등된다    
      Serial.write("OFF");
    }                  
      
  }
  if(Serial.available()){
    char h = (char)Serial.read();
    HM10.print(h);
  }
}