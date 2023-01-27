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
  // Serial.println("Baudrate Setting...");
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
  // Serial.println("============================");
  // char c = 0;
  // while (mySerial.available() > 0) {
  //   Serial.print(mySerial.read(), HEX);
  //   Serial.print(" ");
  // }
  // Serial.println("\n============================");  
}

void loop() {
  // Serial.println("\nRFID Test\n");
  // RFID_COMMAND check;
  // String request = "";
  // if (Serial.available() > 0) {

  // }
  // else {
  //   request += check.SF;
  //   request += check.LEN;
  //   request += check.COM;
  //   request += check.data;
  //   request += check.EF;
  // }

  // mySerial.print(request);
  // delay(50);
  // Serial.println("============================");
  // char c = 0;
  // while (mySerial.available() > 0) {
  //   Serial.print(mySerial.read(), HEX);
  //   Serial.print(" ");
  // }
  // Serial.println("\n============================");
  // delay(5000);
}
