import { Howl } from "howler";

export const SFX = {
  COLLECT: "COLLECT",
  WIN: "WIN",
  TELEPORT: "TELEPORT",
  LOWFUEL: "LOWFUEL",
  DRILL: "DRILL",
  DRIVING: "DRIVING",
};

const SFX_FILES = {
  [SFX.COLLECT]: "/sfx/collect.mp3",
  [SFX.WIN]: "/sfx/win.mp3",
  [SFX.TELEPORT]: "/sfx/teleport.mp3",
  [SFX.LOWFUEL]: "/sfx/low-fuel.wav",
  [SFX.DRILL]: "/sfx/Drill.wav",
  [SFX.DRIVING]: "/sfx/Driving.wav",
};

export class Sounds {
  constructor() {
    this.howls = {};
    this.sfxVolume = 0.5;
    this.currentlyPlaying = {};
  }

  init() {
    Object.keys(SFX_FILES).forEach((key) => {
      const file = SFX_FILES[key];
      this.howls[key] = new Howl({
        src: [file],
        onend: () => {
          // Set currentlyPlaying to null when the sound ends
          this.currentlyPlaying[key] = false;
        },
      });
    });
  }

  playSfx(key) {
    if (this.currentlyPlaying[key]) {
      // If the same sound is playing, don't play a new one
      return;
    }

    // Reference our sound in memory
    const howl = this.howls[key];

    // Play it with current volume setting
    howl.volume(this.sfxVolume);
    howl.play();

    // Set currentlyPlaying to true for the key of the sound being played
    this.currentlyPlaying[key] = true;
  }
}

const soundsManager = new Sounds();

export default soundsManager;