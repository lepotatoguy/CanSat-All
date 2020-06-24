#include <SPI.h>
#include <nRF24L01.h>
#include <RF24.h>
#include <Wire.h>
#include <Adafruit_BMP085.h>

Adafruit_BMP085 bmp;

RF24 radio(9, 10); // CE, CSN
const byte address[6] = "00001";


struct package
{
  float pressure;
  float temperature;
  float aaltitude;
  
  
};


typedef struct package Package;
Package data;


void setup() {
  radio.begin();
  radio.openWritingPipe(address);
  radio.setPALevel(RF24_PA_MIN);
  radio.stopListening();
  
  Serial.begin(9600);
  bmp.begin();
}
void loop() {
   delay(1000);
   float pressure=bmp.readPressure();
   float temperature=bmp.readTemperature();
   float aaltitude=bmp.readAltitude();
  

  Serial.println();
  delay(2000);
                                //int  id=analogRead(A0);
                                 //Serial.println(id);
                                  //write your temp code herer
 
radio.write(&pressure, sizeof(pressure));
Serial.println(pressure);
radio.write(&temperature, sizeof(temperature));
Serial.println(temperature);
radio.write(&aaltitude, sizeof(aaltitude));
Serial.println(aaltitude);
  
  
  
  /*const char text[] = "Paisos? rhyin the ultimate bolod?";
  radio.write(&text, sizeof(text));
  delay(1000);*/
}
