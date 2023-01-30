#include <SoftwareSerial.h>
// SoftwareSerial 사용 핀 설정
const byte rxPin = 4;
const byte txPin = 3;

// Set up a new SoftwareSerial object
SoftwareSerial mySerial (rxPin, txPin);

typedef struct RFID_COMMAND {
  char SF = 0x33;
  char LEN = 0x00;
  char COM = 0x00;
  char EF = 0x99;
  String data = "";
};

void setup() {
  pinMode(rxPin, INPUT);
  pinMode(txPin, OUTPUT);
  Serial.begin(9600);
  mySerial.begin(9600);
  Serial.println("Device ON");
  Serial.println("initailizing...");
  // Serial.println("Baudrate Setting...");
  // Serial.println("============================");
  // RFID_COMMAND baud_setting;
  // baud_setting.SF = 0x33;
  // baud_setting.LEN = 0x05;
  // baud_setting.COM = 0xC3;
  // baud_setting.EF = 0x99;
  // baud_setting.data = "";
  // char baudrate = 0x00;  // 9600
  // String setBaudRequest = "";
  // setBaudRequest += baud_setting.SF;
  // setBaudRequest += baud_setting.LEN;
  // setBaudRequest += baud_setting.COM;
  // setBaudRequest += baudrate;
  // setBaudRequest += baud_setting.EF;
  // mySerial.print(setBaudRequest);
  // delay(50);
  char c[10];
  int i = 0;
  // while (mySerial.available() > 0) {
  //  c[i] = mySerial.read();
  //  i++;
  // }
  // if (c[3]) {
  //   Serial.println(c[3], HEX);
  //   Serial.println("OK");
  // }
  // else {
  //   Serial.println(c[3], HEX);
  //   Serial.println("FAIL");
  // }
  // Serial.println("============================\n\n\n");
  Serial.println("RF Setting...");
  Serial.println("============================");
  RFID_COMMAND RF_setting;
  RF_setting.SF = 0x33;
  RF_setting.LEN = 0x05;
  RF_setting.COM = 0xC1;
  RF_setting.EF = 0x99;
  RF_setting.data = "";
  char RF = 0x01;  // 9600
  String setRFRequest = "";
  setRFRequest += RF_setting.SF;
  setRFRequest += RF_setting.LEN;
  setRFRequest += RF_setting.COM;
  setRFRequest += RF;
  setRFRequest += RF_setting.EF;
  mySerial.print(setRFRequest);
  delay(1000);
  i = 0;
  while (mySerial.available() > 0) {
    c[i] = mySerial.read();
    Serial.print(c[i], HEX);
    Serial.print(" ");
    i++;
  }
  if (c[3]) {
    Serial.println(c[3], HEX);
    Serial.println("OK");
  }
  else {
    Serial.println(c[3], HEX);
    Serial.println("FAIL");
  }
  Serial.println("============================\n\n\n");
  // // Reading Stay 모드 테스트
  // Serial.println("RFID Reading Stay");
  // Serial.println("============================");
  // RFID_COMMAND ReadingStay;
  // ReadingStay.SF = 0x33;
  // ReadingStay.LEN = 0x04;
  // ReadingStay.COM = 0xB1;
  // ReadingStay.EF = 0x99;
  // ReadingStay.data = "";
  // String request = "";
  // request += ReadingStay.SF;
  // request += ReadingStay.LEN;
  // request += ReadingStay.COM;
  // request += ReadingStay.data;
  // request += ReadingStay.EF;
  // mySerial.print(request);
  // delay(1000);
  // i = 0;
  // while (mySerial.available() > 0) {
  //   c[i] = mySerial.read();
  //   Serial.print(c[i], HEX);
  //   Serial.print(" ");
  //   i++;
  // }
  // if (c[3]) {
  //   Serial.println(c[3], HEX);
  //   Serial.println("OK");
  // }
  // else {
  //   Serial.println(c[3], HEX);
  //   Serial.println("FAIL");
  // }
  // Serial.println("============================\n\n\n");
  // Reading Continue 모드 테스트
  Serial.println("RFID Reading Continue");
  Serial.println("============================");
  RFID_COMMAND ReadingStay;
  ReadingStay.SF = 0x33;
  ReadingStay.LEN = 0x04;
  ReadingStay.COM = 0xB2;
  ReadingStay.EF = 0x99;
  ReadingStay.data = "";
  String request = "";
  request += ReadingStay.SF;
  request += ReadingStay.LEN;
  request += ReadingStay.COM;
  request += ReadingStay.data;
  request += ReadingStay.EF;
  mySerial.print(request);
  while (mySerial.available() > 0) {
    c[i] = mySerial.read();
    Serial.print(c[i], HEX);
    Serial.print(" ");
    i++;
  }
  if (c[3]) {
    Serial.println(c[3], HEX);
    Serial.println("OK");
  }
  else {
    Serial.println(c[3], HEX);
    Serial.println("FAIL");
  }
  Serial.println("============================\n\n\n");
}

void loop() {
  Serial.println("\n\n============================");
  char c = 0;
  while (mySerial.available() > 0) {
    Serial.print(mySerial.read(), HEX);
    Serial.print(" ");
  }
  Serial.println("\n============================");
  delay(300);
}
