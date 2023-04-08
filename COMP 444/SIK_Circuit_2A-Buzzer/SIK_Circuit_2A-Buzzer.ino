/*
 * Jackson Wiebe
 * 3519635
 * 08/04/2023
 * 
 * Inspriation taken from:
 * https://github.com/robsoncouto/arduino-songs/blob/master/doom/doom.ino
 * 
*/

#include "music.h"      //Abstract away our big data

const int buzzerPin = 10;   //Pin of our buzzer

const int tempo = 120;  //Tempo to run the notes

// sizeof gives the number of bytes, each int value is composed of two bytes (16 bits)
// there are two values per note (pitch and duration), so for each note there are four bytes
int notes = sizeof(melody) / sizeof(melody[0]) / 2;

// this calculates the duration of a whole note in ms
int wholenote = (60000 * 4) / tempo;

int divider = 0, noteDuration = 0;

void setup() {
  // iterate over the notes of the melody.
  // Remember, the array is twice the number of notes (notes + durations)
  for (int thisNote = 0; thisNote < notes * 2; thisNote = thisNote + 2) {

    // calculates the duration of each note
    divider = pgm_read_word_near(melody+thisNote + 1);
    noteDuration = (wholenote) / divider;

    // we only play the note for 90% of the duration, leaving 10% as a pause
    tone(buzzerPin, pgm_read_word_near(melody+thisNote), noteDuration * 0.9);

    // Wait for the specief duration before playing the next note.
    delay(noteDuration);

    // stop the waveform generation before the next note.
    noTone(buzzerPin);
  }
}

void loop() {
  // no need to repeat the melody.
}
