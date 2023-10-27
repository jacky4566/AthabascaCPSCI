import soundsManager, { SFX } from "./Sounds";
import {
    DEATH_TYPE_FUEL,
    DIRECTION_UP,
    DIRECTION_RIGHT,
    DIRECTION_LEFT,
    DIRECTION_IDLE,
} from "../helpers/consts";

const TIME_PER_TICK = 16.6;
const START_VALUE = 100.0; //This should remain at 100 for drawing
const COMSUME_LEFT_RIGHT = .1;
const COMSUME_UP = 0.2;
const COMSUME_IDLE = .001;
const WARNING_SOUND_LEVEL = 10;

export class Fuel {
    constructor(level) {
        this.fuelRemaining = START_VALUE;
        this.level = level;
        this.msRemainingInSecond = 1000;
    }

    consumeFuel(direction) {
        if (direction === DIRECTION_RIGHT || direction === DIRECTION_LEFT)
            this.fuelRemaining -= COMSUME_LEFT_RIGHT;
        else if (direction === DIRECTION_UP)
            this.fuelRemaining -= COMSUME_UP;
        else if (direction === DIRECTION_IDLE)
            this.fuelRemaining -= COMSUME_IDLE;
    }

    tick() {
        // run this once per second not per tick
        this.msRemainingInSecond -= TIME_PER_TICK;
        if (this.msRemainingInSecond <= 0) {
            this.msRemainingInSecond = 1000;

            //Trigger things based on second change
            // Lose if the clock hits 0
            if (this.fuelRemaining <= 0) {
                this.level.setDeathOutcome(DEATH_TYPE_FUEL);
                return;
            }

            // Warning sound effects!
            if (this.fuelRemaining <= WARNING_SOUND_LEVEL) {
                // SFX Goes here...
                soundsManager.playSfx(SFX.LOWFUEL);
            }
        }
    }


}