#include <SoftwareSerial.h>

// SoftwareSerial 사용 핀 설정
const byte rxPin = 4;
const byte txPin = 7;

// Set up a new SoftwareSerial object
SoftwareSerial mySerial(rxPin, txPin);

void flushSerialBuffer() {
  while (Serial.available()) {
    Serial.read();
  }
}

void flushmySerialBuffer() {
  while (mySerial.available()) {
    mySerial.read();
  }
}

void execute_commandline(String message, uint8_t* cl) {
  uint8_t c[20] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
  char i;

  Serial.println(message);
  Serial.print("request bit : ");
  for (i = 0; i < cl[1]; i++) {
    Serial.print(cl[i], HEX);
    Serial.print(" ");
  }
  Serial.println();
  for (i = 0; i < cl[1]; i++) {
    mySerial.write(cl[i]);
  }
  delay(2000);
  Serial.println("============================");
  i = 0;
  Serial.print("response len : ");
  Serial.println(mySerial.available());
  Serial.print("response bit : ");
  while (mySerial.available() > 0) {
    c[i] = mySerial.read();
    Serial.print(c[i], HEX);
    Serial.print(" ");
    i++;
  }
  Serial.println();
  if (c[3]) {
    Serial.println("OK");
  } else {
    Serial.println("FAIL");
  }
  Serial.println("============================\n\n\n");
}


// 명령어 생성

// // set_baud_request = baudrate 값 세팅
uint8_t set_baud_request[5] = { 0x33, 0x05, 0xC3, 0x00, 0x99 };

// // check_reader = 리더 동작 체크
uint8_t check_reader[4] = { 0x33, 0x04, 0xC2, 0x99 };

// // set_RF_request = RFID 읽기모드 ON/OFF
uint8_t set_RF_request_on[5] = { 0x33, 0x05, 0xC1, 0x01, 0x99 };
uint8_t set_RF_request_off[5] = { 0x33, 0x05, 0xC1, 0x00, 0x99 };

// // readingStay_request = RFID Reading STAY 모드 설정
uint8_t readingStay_request[4] = { 0x33, 0x04, 0xB1, 0x99 };

// // readingContinue_request = RFID Reading Continue 모드 설정
unsigned char readingContinue_request[4] = { 0x33, 0x04, 0xB2, 0x99 };

void setup() {
  pinMode(rxPin, INPUT);
  pinMode(txPin, OUTPUT);
  uint8_t c[10];
  int i = 0;
  Serial.begin(9600);
  mySerial.begin(9600);
  Serial.println("Device ON");
  Serial.println("initailizing...");
  // 버퍼 비우기
  flushSerialBuffer();
  flushmySerialBuffer();
  // RFID 리더 체크
  execute_commandline("RFID reader check", check_reader);
  // RF 리딩모트 끄기
  // execute_commandline("RF carrier off", set_RF_request_off);
  // baudrate 세팅 (9600)
  // execute_commandline("Baudrate Setting...", set_baud_request);
  // // Reading Stay 모드 테스트
  // execute_commandline("RFID Reading Stay", readingStay_request);
  // Reading Continue 모드 테스트
  execute_commandline("RFID Reading Continue", readingContinue_request);
}

void loop() {
  // while (!Serial.available()) {}
  flushSerialBuffer();
  flushmySerialBuffer();
  // // // 입력이 오면 리딩모드 on
  // execute_commandline("RF carrier on", set_RF_request_on);
  int count = 0;
  char data;
  while (count < 50) {
    if (mySerial.available() > 0) {
      while (mySerial.available() > 0) {
        data = mySerial.read();
        Serial.print(data);
        if (data == 0x99) Serial.println();
      }
    }
    count++;
    delay(100);
  }
  // execute_commandline("RF carrier off", set_RF_request_off);
  delay(500);
}
