#include <SPI.h>
#include <nRF24L01.h>
#include <RF24.h>
RF24 radio(9, 10); // CE, CSN
const byte address[6] = "00001";

struct package{
   float accelerometer_x;
  float accelerometer_y;
  float accelerometer_z;
  float gyro_x;
  float gyro_y;
  float gyro_z;
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
   
    float accelerometer_x_value= data.accelerometer_x;
    Serial.println(accelerometer_x_value);

    float accelerometer_y_value=data.accelerometer_y;
    Serial.println(accelerometer_y_value);

    float accelerometer_z_value=data.accelerometer_z;
    Serial.println(accelerometer_z_value);

    
    float gyro_x_value=data.gyro_x;
    Serial.println(gyro_x_value);

    
    float gyro_y_value=data.gyro_y;
    Serial.println(gyro_y_value);

    
    float gyro_z_value=data.gyro_z;
    Serial.println(gyro_z_value);
   
   
   
    
   
   Serial.println("");

  
   
   
  }
  
}
