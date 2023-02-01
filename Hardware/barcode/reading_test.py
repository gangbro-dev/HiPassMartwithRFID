import serial
import time

while True:

    data = serial.Serial('/dev/hidraw0', 9600)

    print(data)

    time.sleep(1)
