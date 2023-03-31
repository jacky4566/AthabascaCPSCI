/*
  SparkFun Inventor’s Kit
  Circuit 1D-RGB Nightlight

  Turns an RGB LED on or off based on the light level read by a photoresistor.
  Change colors by turning the potentiometer.

  This sketch was written by SparkFun Electronics, with lots of help from the Arduino community.
  This code is completely free for any use.

  View circuit diagram and instructions at: https://learn.sparkfun.com/tutorials/sparkfun-inventors-kit-experiment-guide---v41
  Download drawings and code at: https://github.com/sparkfun/SIK-Guide-Code
*/

int photoresistor = A0;          //variable for storing the photoresistor value
int potentiometer = A1;          //this variable will hold a value based on the position of the knob
int threshold = 700;            //if the photoresistor reading is lower than this value the light will turn on

//LEDs are connected to these pins
int RedPin = 9;
int GreenPin = 10;
int BluePin = 11;

void setup() {
  Serial.begin(9600);           //start a serial connection with the computer

  //set the LED pins to output
  pinMode(RedPin, OUTPUT);
  pinMode(GreenPin, OUTPUT);
  pinMode(BluePin, OUTPUT);
}

void loop() {

  int photoresistor = analogRead(A0);         //read the value of the photoresistor
  int potentiometer = analogRead(A1);         //read the value of the potentiometer

  int redValue;
  int greenValue;
  int blueValue;

  //Do our color wheel calc
  switch (potentiometer) {         //Perform a switch case on the potentiometer value
    case 0 ... 255:                //Fade from OFF to RED
      redValue = map(potentiometer, 0, 255, 0 , 255);     //Ramp up
      greenValue = 0;
      blueValue = 0;
      break;
    case 256 ... 511:               //Fade from RED to GREEN
      redValue = map(potentiometer, 256, 511, 255, 0);    //Ramp down
      greenValue = map(potentiometer, 256, 511, 0 , 255); //Ramp up
      blueValue = 0;
      break;
    case 512 ... 767:               //Fade from GREEN to BLUE
      redValue = 0;
      greenValue = map(potentiometer, 512, 767, 255, 0);  //Ramp down
      blueValue = map(potentiometer, 512, 767, 0 , 255);  //Ramp up
      break;
    case 768 ... 1023:              //Fade from BLUE to OFF
      redValue = 0;
      greenValue = 0;
      blueValue =  map(potentiometer, 768, 1023, 255, 0);  //Ramp down
      break;
  }

  //Apply brightess
  float brightness = (float)photoresistor / 1023.0;  //Scale photoresistor input to a value of 0.0 through 1.0
  redValue = (float)redValue * brightness;          //Multiple current value by our brightness modifier
  greenValue = (float)greenValue * brightness;      //Multiple current value by our brightness modifier
  blueValue = (float)blueValue * brightness;        //Multiple current value by our brightness modifier

  Serial.println(brightness);

  //Write the values to the pins after casting to int
  analogWrite(RedPin, redValue);
  analogWrite(GreenPin, greenValue);
  analogWrite(BluePin, blueValue);

}
