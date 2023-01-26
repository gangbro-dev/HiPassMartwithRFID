void setup() {
  Serial.begin(9600);
  Serial.println("Device ON");
  pinMode(13, OUTPUT);
}

void loop() {
  char request[20] = {63,63,63,63,63,63,63,63,63,63,63,63,63,63,63,63,63,63,63,63};
  if(Serial.available()) {
    delay(100);
    char request_char = 0;
    while (request_char != 33 && Serial.available()) {
      request_char = Serial.read();
      delay(5);
    }
    if (request_char == 33) {
      request_char = Serial.read();
      digitalWrite(13, !digitalRead(13));
      int i = 0;
      while (request_char != 33 && i < 20 && Serial.available()) {
        request[i] = request_char;
        request_char = Serial.read();
        i++;
      }
      for(int j=0;j<i;j++) {
        Serial.print(request[j]);
      }
      Serial.println();
    }
  }
}