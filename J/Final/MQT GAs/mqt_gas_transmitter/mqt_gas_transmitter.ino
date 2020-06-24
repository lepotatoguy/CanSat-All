#include "MQ135.h"
#define ANALOGPIN A0    //  Define Analog PIN on Arduino Board
#define RZERO 76.63    //  Define RZERO Calibration Value
MQ135 gasSensor = MQ135(ANALOGPIN);

#include <SPI.h>
#include <nRF24L01.h>
#include <RF24.h>
RF24 radio(9, 10); // CE, CSN
const byte address[6] = "00001";


struct package{
   float ppm;
   
   
};
typedef struct package Package;
Package data;




void setup()
{
  Serial.begin(9600);
  float rzero = gasSensor.getRZero();
  delay(1000);
  Serial.print("MQ135 RZERO Calibration Value : ");
  Serial.println(rzero);
  radio.begin();
  radio.openWritingPipe(address);
  radio.setPALevel(RF24_PA_MIN);
  radio.stopListening();
  
}





void loop() {
  float ppm = gasSensor.getPPM();
  delay(1000);
  digitalWrite(13,HIGH);
  radio.write(&ppm, sizeof(ppm));
  Serial.print("CO2 ppm value : ");
  Serial.println(ppm);
}
//https://www.google.com/search?q=RZERO+Calibration+Value&rlz=1C1CHBD_enBD860BD860&oq=rzero&aqs=chrome.0.69i59j69i57j0l4.7760j0j7&sourceid=chrome&ie=UTF-8
