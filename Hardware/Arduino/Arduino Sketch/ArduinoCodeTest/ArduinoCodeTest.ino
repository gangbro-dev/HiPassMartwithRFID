#include <SoftwareSerial.h>

// SoftwareSerial 사용 핀 설정
const int rxPin = 4;
const int txPin = 7;

SoftwareSerial mySerial(rxPin, txPin);

void flushSerialBuffer() {
  while (Serial.available()) {
    Serial.read();
  }
}
const uint8_t check_reader[4] = {0x33, 0x04, 0xC2, 0x99};
const uint8_t set_baud_request[5] = {0x33, 0x05, 0xC3, 0x00, 0x99};

void execute_commandline(String message, uint8_t * cl) {
  uint8_t c[20] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
  int i;
  
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
  delay(1000);
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

void setup() {
  // put your setup code here, to run once:
  pinMode(rxPin, INPUT);
  pinMode(txPin, OUTPUT);
  Serial.begin(9600);
  mySerial.begin(115200);
  // baudrate 세팅 (9600)
  execute_commandline("Baudrate Setting...", set_baud_request);
  mySerial.end();
  mySerial.begin(9600);
  execute_commandline("Reader check...", check_reader);
  for (int i = 0; i < check_reader[1]; i++) {
    mySerial.write(check_reader[i]);
  }
  while(true) {}
}

void loop() {
  // put your main code here, to run repeatedly:
  Serial.print("input: ");
  Serial.println(mySerial.available());
  while(mySerial.available()) {
    Serial.print(mySerial.read(), HEX);
    Serial.print(" ");
  }
  Serial.println("\n input end");
  delay(1000);
}
