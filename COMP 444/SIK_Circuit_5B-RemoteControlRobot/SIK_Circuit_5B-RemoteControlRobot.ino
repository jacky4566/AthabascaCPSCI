/*
  SparkFun Inventor’s Kit
  Circuit 5B - Remote Control Robot

  Control a two wheeled robot by sending direction commands through the serial monitor.
  This sketch was adapted from one of the activities in the SparkFun Guide to Arduino.
  Check out the rest of the book at
  https://www.sparkfun.com/products/14326

  This sketch was written by SparkFun Electronics, with lots of help from the Arduino community.
  This code is completely free for any use.

  View circuit diagram and instructions at: https://learn.sparkfun.com/tutorials/sparkfun-inventors-kit-experiment-guide---v41
  Download drawings and code at: https://github.com/sparkfun/SIK-Guide-Code
*/


//the right motor will be controlled by the motor A pins on the motor driver
const int AIN1 = 13;           //control pin 1 on the motor driver for the right motor
const int AIN2 = 12;            //control pin 2 on the motor driver for the right motor
const int PWMA = 11;            //speed control pin on the motor driver for the right motor

//the left motor will be controlled by the motor B pins on the motor driver
const int PWMB = 10;           //speed control pin on the motor driver for the left motor
const int BIN2 = 9;           //control pin 2 on the motor driver for the left motor
const int BIN1 = 8;           //control pin 1 on the motor driver for the left motor

int switchPinLeft = 5;        //Button for LEFT
int switchPinForward = 6;     //Button for FORWARDS
int switchPinRight = 7;       //Button for RIGHT

const int driveTime = 1000;      //this is the number of milliseconds that it takes the robot to drive 1 inch
//it is set so that if you tell the robot to drive forward 25 units, the robot drives about 25 inches

const int turnTime = 250;        //this is the number of milliseconds that it takes to turn the robot 1 degree
//it is set so that if you tell the robot to turn right 90 units, the robot turns about 90 degrees

/********************************************************************************/
void setup()
{
  pinMode(switchPinLeft, INPUT_PULLUP);       //set this as a pullup
  pinMode(switchPinForward, INPUT_PULLUP);    //set this as a pullup
  pinMode(switchPinRight, INPUT_PULLUP);      //set this as a pullup

  //set the motor control pins as outputs
  pinMode(AIN1, OUTPUT);
  pinMode(AIN2, OUTPUT);
  pinMode(PWMA, OUTPUT);

  pinMode(BIN1, OUTPUT);
  pinMode(BIN2, OUTPUT);
  pinMode(PWMB, OUTPUT);
}

/********************************************************************************/
void loop()
{
  if (!digitalRead(switchPinLeft)) {
    rightMotor(255);                                //drive the right wheel forward
    leftMotor(-200);                                //drive the left wheel forward
    delay(turnTime);                                 //drive the motors long enough
  } else if (!digitalRead(switchPinForward)) {
    rightMotor(200);                                //drive the right wheel forward
    leftMotor(200);                                 //drive the left wheel forward
    delay(driveTime);                                //drive the motors long enough
  } else if (!digitalRead(switchPinRight)) {
    rightMotor(-200);                               //drive the right wheel forward
    leftMotor(255);                                 //drive the left wheel forward
    delay(turnTime);                                //drive the motors long enough turn
  }
  rightMotor(0);                                  //turn the right motor off
  leftMotor(0);                                   //turn the left motor off
}
/********************************************************************************/
void rightMotor(int motorSpeed)                       //function for driving the right motor
{
  if (motorSpeed > 0)                                 //if the motor should drive forward (positive speed)
  {
    digitalWrite(AIN1, HIGH);                         //set pin 1 to high
    digitalWrite(AIN2, LOW);                          //set pin 2 to low
  }
  else if (motorSpeed < 0)                            //if the motor should drive backward (negative speed)
  {
    digitalWrite(AIN1, LOW);                          //set pin 1 to low
    digitalWrite(AIN2, HIGH);                         //set pin 2 to high
  }
  else                                                //if the motor should stop
  {
    digitalWrite(AIN1, LOW);                          //set pin 1 to low
    digitalWrite(AIN2, LOW);                          //set pin 2 to low
  }
  analogWrite(PWMA, abs(motorSpeed));                 //now that the motor direction is set, drive it at the entered speed
}

/********************************************************************************/
void leftMotor(int motorSpeed)                        //function for driving the left motor
{
  if (motorSpeed > 0)                                 //if the motor should drive forward (positive speed)
  {
    digitalWrite(BIN1, HIGH);                         //set pin 1 to high
    digitalWrite(BIN2, LOW);                          //set pin 2 to low
  }
  else if (motorSpeed < 0)                            //if the motor should drive backward (negative speed)
  {
    digitalWrite(BIN1, LOW);                          //set pin 1 to low
    digitalWrite(BIN2, HIGH);                         //set pin 2 to high
  }
  else                                                //if the motor should stop
  {
    digitalWrite(BIN1, LOW);                          //set pin 1 to low
    digitalWrite(BIN2, LOW);                          //set pin 2 to low
  }
  analogWrite(PWMB, abs(motorSpeed));                 //now that the motor direction is set, drive it at the entered speed
}
