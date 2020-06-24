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



void setup() {
  Serial.begin(9600);
  radio.begin();
  radio.openReadingPipe(0, address);
  radio.setPALevel(RF24_PA_MIN);
  radio.startListening();
}
void loop() {
  if (radio.available()) {
   
   radio.read(&data, sizeof(data));

   float ppm_value=data.ppm;
   Serial.println(ppm_value);
   
    
   
   
    
   
   Serial.println("");

  
   
   
  }
  
}
