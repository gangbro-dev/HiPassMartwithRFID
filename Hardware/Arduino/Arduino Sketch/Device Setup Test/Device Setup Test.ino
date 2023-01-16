#define SF 0x33
#define EF 0x99
// #define READ 0x06A1
// #define WRITE 0xA2
// #define WRITE_TI 0xA4
// #define LOCK_BLOCK 0x05A3
// #define LOCK_BLOCK_TI 0x05A5
// #define READING_STAY 0x04B1
// #define READING_NONSTOP 0x04B2
// #define RF_POWERSWITCH 0x05C1
// #define READER_CHECK 0x04C2

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  Serial.print("Device ON");
  pinMode(13, OUTPUT);

}

void loop() {
  // put your main code here, to run repeatedly:
  Serial.print("Serial Request \n");
  Serial.print("Serial Response Flag : ");
  Serial.println(Serial.available());
  while (Serial.available()) {
    digitalWrite(13, HIGH);
    char a;
    a = Serial.read();
    Serial.print(a);
  }
  Serial.println();
  delay(5000);
  digitalWrite(13, LOW);
}
