void flushSerialBuffer() {
  while (Serial.available()) {
    Serial.read();
  }
}

String command_line(unsigned char len, unsigned char com, unsigned char data = 0x00) {
  const unsigned char sf = 0x33;
  const unsigned char ef = 0x99;
  unsigned char commandLine[len];
  if (len == 4) {
    commandLine[0] = sf;
    commandLine[1] = len;
    commandLine[2] = com;
    commandLine[3] = ef;
  } else if (len == 5) {
    commandLine[0] = sf;
    commandLine[1] = len;
    commandLine[2] = com;
    commandLine[3] = data;
    commandLine[4] = ef;
  }
  return commandLine;
}

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  Serial.print("test: ");
  String cm = command_line(0x05, 0xB1, 0x01);
  for (int i = 0; i < cm.length(); i++) {
    Serial.print((unsigned char) cm[i], HEX);
    Serial.print(" ");
  }
  Serial.println();
  delay(3000);
  while(1) {}
}

void loop() {
  // put your main code here, to run repeatedly:
  
}
