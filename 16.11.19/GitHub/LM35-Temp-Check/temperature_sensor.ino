#define LM35 A0
//int val;
//int tempPin = 0;

void setup()
{
Serial.begin(9600);
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
}
