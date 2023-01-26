#include <SoftwareSerial.h>
// SoftwareSerial 사용 핀 설정
#define RX_PIN 4
#define TX_PIN 5

SoftwareSerial mySerial (RX_PIN, TX_PIN);

typedef struct RFID_data{
  char SF = 0x33;
  char LEN = 0x0C;
  char COM = 0x3B;
  String data = "ABCDEFGH";
  char EF = 0x99;
};

void setup() {
  pinMode(RX_PIN, INPUT);
  pinMode(TX_PIN, OUTPUT);
  Serial.begin(9600);
  Serial.println("Device ON");
}

void loop() {
  RFID_data dummy;
  String sendingData = "";
  sendingData += dummy.SF;
  sendingData += dummy.LEN;
  sendingData += dummy.COM;
  sendingData += dummy.data;
  sendingData += dummy.EF;

  Serial.print(sendingData);
  delay(400);
}
