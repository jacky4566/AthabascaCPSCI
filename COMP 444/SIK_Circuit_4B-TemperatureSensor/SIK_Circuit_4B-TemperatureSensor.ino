/*
  SparkFun Inventor’s Kit
  Circuit 4B - Temperature Sensor

  The LCD will display readings from a temperature sensor in degrees Celsius and Fahrenheit.

  This sketch was written by SparkFun Electronics, with lots of help from the Arduino community.
  This code is completely free for any use.

  View circuit diagram and instructions at: https://learn.sparkfun.com/tutorials/sparkfun-inventors-kit-experiment-guide---v41
  Download drawings and code at: https://github.com/sparkfun/SIK-Guide-Code
*/

#include <LiquidCrystal.h>                  //the liquid crystal library contains commands for printing to the display
LiquidCrystal lcd(13, 12, 11, 10, 9, 8);    // tell the RedBoard what pins are connected to the display

void setup() {

  lcd.begin(16, 2);                         //tell the lcd library that we are using a display that is 16 characters wide and 2 characters high
  lcd.clear();                              //clear the display
}

void loop() {
  double voltage = (double)analogRead(A0) * 0.004882813;   //convert the analog reading, which varies from 0 to 1023, back to a voltage value from 0-5 volts
  double degreesK = (((double)voltage - 0.5) * 100.0) + 273.15;

  writeDegreesK(degreesK);
  DrawGraph(degreesK);
}

void writeDegreesK(double d) {
  static unsigned long tick = 0; //Create a static variable as a timer
  if (millis() - tick < 500)   //If enough time has passed
    return;                     //Return becuase enough time has not passed
  tick = millis();              //Update our timer
  lcd.setCursor(0, 0);          //set the cursor to the top left position
  lcd.print("Degrees K: ");     //print a label for the data
  lcd.print(d);                 //print the degrees Kelvin
  lcd.print("   ");             //Pad out the display
}

void DrawGraph(double d) {
  static unsigned long tick = 0; //Create a static variable as a timer
  if (millis() - tick < 100)   //If enough time has passed
    return;                     //Return becuase enough time has not passed
  tick = millis();              //Update our timer
  lcd.setCursor(0, 1);          //set the cursor to the top left position
  byte barGraph = map(d, 290, 310, 0, 15);
  for (byte pos = 0; pos < 16; pos++) {
    if (barGraph > pos)
      lcd.print('#');
    else
      lcd.print(" ");
  }
}
