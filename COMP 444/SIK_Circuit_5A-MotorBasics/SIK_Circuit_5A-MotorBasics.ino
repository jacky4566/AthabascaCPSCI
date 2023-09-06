/*
  SparkFun Inventor’s Kit
  Circuit 5A - Motor Basics

  Learn how to control one motor with the motor driver.

  This sketch was written by SparkFun Electronics, with lots of help from the Arduino community.
  This code is completely free for any use.

  View circuit diagram and instructions at: https://learn.sparkfun.com/tutorials/sparkfun-inventors-kit-experiment-guide---v41
  Download drawings and code at: https://github.com/sparkfun/SIK-Guide-Code
*/

//PIN VARIABLES
//the motor will be controlled by the motor A pins on the motor driver
const int AIN1 = 13;           //control pin 1 on the motor driver for the right motor
const int AIN2 = 12;            //control pin 2 on the motor driver for the right motor
const int PWMA = 11;            //speed control pin on the motor driver for the right motor

int switchPin = 7;             //switch to turn the robot on and off
int lightSensor = A0;             //switch to turn the robot on and off

//VARIABLES
int motorSpeed = 0;       //starting speed for the motor

byte motorState = 0;

void setup() {
  pinMode(switchPin, INPUT_PULLUP);   //set this as a pullup to sense whether the switch is flipped

  //set the motor control pins as outputs
  pinMode(AIN1, OUTPUT);
  pinMode(AIN2, OUTPUT);
  pinMode(PWMA, OUTPUT);
}

void loop() {
  int light = analogRead(lightSensor);
  motorSpeed = map(light, 100, 500, 0, 255);

  if (!digitalRead(switchPin))
    motorState++;

  spinMotor(motorSpeed);
  delay(150);
}

/********************************************************************************/
void spinMotor(int motorSpeed)                       //function for driving the right motor
{
  switch (motorState) {
    case 0:
      digitalWrite(AIN1, LOW);                          //set pin 1 to low
      digitalWrite(AIN2, LOW);                          //set pin 2 to low
      break;
    case 1:
      digitalWrite(AIN1, LOW);                          //set pin 1 to low
      digitalWrite(AIN2, HIGH);                         //set pin 2 to high
      break;
    case 2:
      digitalWrite(AIN1, HIGH);                         //set pin 1 to high
      digitalWrite(AIN2, LOW);                          //set pin 2 to low
      break;
    default:
      motorState = 0;
      break;
  }
  analogWrite(PWMA, abs(motorSpeed));                 //now that the motor direction is set, drive it at the entered speed
}
