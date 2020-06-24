#include <SPI.h>
#include <nRF24L01.h>
#include <RF24.h>
RF24 radio(9, 10); // CE, CSN         
const byte address[6] = "00001";     //Byte of array representing the address. This is the address where we will send the data. This should be same on the receiving side.
int button_pin = 2;
boolean button_state = 0;


#define LM35 A0
//int val;
//int tempPin = 0;





void setup() {
pinMode(button_pin, INPUT);
radio.begin();                  //Starting the Wireless communication
radio.openWritingPipe(address); //Setting the address where we will send the data
radio.setPALevel(RF24_PA_MAX);  //You can set it as minimum or maximum depending on the distance between the transmitter and receiver.
radio.stopListening();   
Serial.begin(9600);  //This sets the module as transmitter
}



void loop()
{
 
float val = analogRead(LM35);
//float mv = ( val/1024.0)*5000;
//float cel = mv/10;
//float farh = (cel*9)/5 + 32;
float temp = (val * 500)/1023;

//Serial.print("TEMPRATURE = ");
Serial.print(temp); //celcius
//Serial.print("*C");
Serial.println();
delay(1500);

/* uncomment this to get temperature in farenhite
Serial.print("TEMPRATURE = ");
Serial.print(farh);
Serial.print("*F");
Serial.println();
*/
 text[]=temp;
radio.write(&text, sizeof(text)); 

}
