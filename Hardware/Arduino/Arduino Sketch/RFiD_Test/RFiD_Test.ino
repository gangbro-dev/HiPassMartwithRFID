#include <SoftwareSerial.h>
// SoftwareSerial 사용 핀 설정
#define RX_PIN 4
#define TX_PIN 5

SoftwareSerial mySerial (RX_PIN, TX_PIN);

void setup() {
  pinMode(RX_PIN, INPUT);
  pinMode(TX_PIN, OUTPUT);

  mySerial.begin(9600);
}

void loop() {
  // put your main code here, to run repeatedly:
  if (mySerial.available() > 0) {
    mySerial.read();
  }
}
