
#include <SPI.h>
#include <nRF24L01.h>
#include <RF24.h>
RF24 radio(9, 10); // CE, CSN
const byte address[6] = "00001";
static float floatVal= 000000.00;    
static String stringVal = "";
static char outstr[10];
static char pressure_text[10];
static char temp_text[10];
static char altitude_text[10];
static String output;
static char text[40];
static String bef_out; //before output, trimmed one

struct package{
   float pressure;
   float temperature;
   float aaltitude;
   
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
void loop() {radio.begin();
  if (radio.available()) {
   
   radio.read(&data, sizeof(data));
   
    float a= data.pressure;
    //Serial.println(a);

    float b=data.temperature;
    //Serial.println(b);

    float c=data.aaltitude;
    //Serial.println(c);
   for(int i=0; i<3;i++){
   //stringVal+=String(a);//+ "."+String(getDecimal(floatVal)); //combining both whole and decimal part in string with a fullstop between them
   //Serial.print("stringVal: ");Serial.println(stringVal);
   if(i==2){
    
    int j=0;
    bef_out=String(a);
    bef_out.trim();
    stringVal+=bef_out; //taking single data
    output=stringVal; // passing to another data to secure it
    //Serial.print(output);
    //Serial.print(",");
    while(j<3){ //3 loops to send 3 datas in 3 different places
      if(j==0){ //for first array
        //101439.0   29.00   -9.15
        //int len=output.length();//length of the string or float
        for(int k=0;k< 10;k++){
            pressure_text[k]=output.charAt(k);//putting data to array so that we can make it happen to gui
        }
        stringVal = "";//making string empty once again
        j++;//to work on the next array
        //Serial.print(pressure_text);
        //Serial.print(",");
        break; //to work from while loop again
      }
      if(j==1){
        
        //int len=output.length();
        for(int k=0;k<5;k++){
            temp_text[k]=output.charAt(k);
        }
        stringVal = "";
        j++;
        break;
      }
      if(j==2){
        
        //int len=output.length();
        for(int k=0;k<5;k++){
            altitude_text[k]=output.charAt(k);
        }
        stringVal = "";
        j==0;//to make it for the next round's first info.
        break;
      }
      }//next round 
      
      
      //Serial.println();
      //Serial.println(output);
      snprintf(text, 40, "%s%s%s", pressure_text, temp_text, altitude_text);
      //text.trim();
      for(int p=0;p<40;p++){
        if(isWhitespace(text[p])){
          String err=String(text[p]);
          err.trim();
          text[p]=err.charAt(0);
          //text[p]='\0';
        }
        
      }
      
      Serial.println(text); //uncomment this
      
    
    
   }
   }
//   Serial.println(text);
   //stringVal = "";
   //Serial.print(pressure_text);
   //snprintf(text, 40, "%s,%s,%s", pressure_text, temp_text, altitude_text);
   //Serial.print(text);
   
   //Serial.println();
   delay(200);
   
   }
   
  /* Serial.print(a);
   Serial.print(",");
   Serial.print(b);
   Serial.print(",");
   Serial.print(c);
   Serial.println(""); */

   
/*dtostrf(a, 8, 2, outstr);
   char text[40];
  snprintf(text, 9, "%s,", outstr);
  text[8]="";
  text[9]="";
  text[10]=",";
  //text.replace(" ", ",");
  Serial.print(text); */
  //Serial.print(",");
   //101439.0   29.00   -9.15
    
   
   //Serial.println("");

  
   
   
  }
  void space(){
    Serial.println();
  }
  
