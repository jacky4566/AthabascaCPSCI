/*
  Jackson Wiebe
  3519635
  08/04/2023

  SparkFun Inventor’s Kit
  Circuit 2B-ButtonTrumpet

  Use 3 buttons plugged to play musical notes on a buzzer.

  This sketch was written by SparkFun Electronics, with lots of help from the Arduino community.
  This code is completely free for any use.

  View circuit diagram and instructions at: https://learn.sparkfun.com/tutorials/sparkfun-inventors-kit-experiment-guide---v41
  Download drawings and code at: https://github.com/sparkfun/SIK-Guide-Code
*/

//set the pins for the button and buzzer
const int firstKeyPin = 2;
const int secondKeyPin = 3;
const int thirdKeyPin = 4;
const int analogPin = A0;

const int buzzerPin = 10;

const int notes[] = {523, 494, 440, 392, 349, 330, 294, 0}; //Array of notes for lookup

void setup() {
  //set the button pins as inputs
  pinMode(firstKeyPin, INPUT_PULLUP);
  pinMode(secondKeyPin, INPUT_PULLUP);
  pinMode(thirdKeyPin, INPUT_PULLUP);

  //set the buzzer pin as an output
  pinMode(buzzerPin, OUTPUT);

}

void loop() {
  int wammiBar = map(analogRead(analogPin), 0, 1023, 0, 200); //Get our analog value for the wammi bar and map accordingly

  byte noteLookup = (digitalRead(firstKeyPin) << 0) | (digitalRead(secondKeyPin) << 1) | (digitalRead(thirdKeyPin) << 2); //create a lookup value from the keys

  if (notes[noteLookup])  //If there is a note to play
    tone(buzzerPin, notes[noteLookup] + wammiBar); //Play the note with the wami bar mod
  else {
    noTone(buzzerPin);  //Else quiet the pin
  }
}

/*
  note  frequency
  c     262 Hz
  d     294 Hz
  e     330 Hz
  f     349 Hz
  g     392 Hz
  a     440 Hz
  b     494 Hz
  C     523 Hz
*/
