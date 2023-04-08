/*
  Jackson Wiebe
  31/03/2023
  3519635

  SparkFun Inventor’s Kit
  Circuit 3A-Servo

  Move a servo attached to pin 9 so that it's angle matches a light sensor attached to A0.
*/

#include <Servo.h>          //include the servo library

const int button = 0;     //Input button for reverse direction

int potPosition;           //this variable will store the position of the potentiometer
int servoPosition;         //the servo will move to this position

Servo myservo;              //create a servo object

void setup() {
  pinMode(button, INPUT_PULLUP);
  myservo.attach(9);        //tell the servo object that its servo is plugged into pin 9

}

void loop() {
  potPosition = analogRead(A0);                     //use analog read to measure the position of the potentiometer (0-1023)

  if (digitalRead(button))
    servoPosition = map(potPosition, 0, 1023, 20, 160); //convert the potentiometer number to a servo position from 20-160
  else
    servoPosition = map(potPosition, 0, 1023, 160, 20); //Reverse the direction

  myservo.write(servoPosition);                      //move the servo to the 10 degree position
}
