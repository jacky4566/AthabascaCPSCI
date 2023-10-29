import {
  DIRECTION_RIGHT,
  DIRECTION_DOWN,
  CELL_SIZE,
  DIRECTION_LEFT,
  DIRECTION_UP,
  BODY_SKINS,
} from "../helpers/consts";
import soundsManager, { SFX } from "../classes/Sounds";

/*
Base class for placements that exports nessvary functions.

Writte with help from Ciabatta's revenge Tutorial by Drew Conley

Jackson Wiebe 
3519635
09/10/2023
*/

export class Placement {
  constructor(properties, level) {
    this.id = properties.id;
    this.type = properties.type;
    this.x = properties.x;
    this.y = properties.y;
    this.level = level;
    this.heath = 1;

    this.skin = BODY_SKINS.NORMAL;
    this.travelPixelsPerFrame = 1.5;
    this.movingPixelsRemaining = 0;
    this.movingPixelDirection = DIRECTION_RIGHT;
    this.spriteFacingDirection = DIRECTION_RIGHT;
    this.spriteWalkFrame = 0;

    this.hasBeenCollected = false;
  }

  tick() { }

  collect() {
    soundsManager.playSfx(SFX.COLLECT);
    this.level.inventory.add(this.addsItemToInventoryOnCollide());
    this.hasBeenCollected = true;
  }

  isSolidForBody(_body) {
    return false;
  }

  isMinable(){
    return false;
  }

  mine(){
    return null;
  }

  addsItemToInventoryOnCollide() {
    return null;
  }

  displayXY() {
    if (this.movingPixelsRemaining > 0) {
      return this.displayMovingXY();
    }

    const x = this.x * CELL_SIZE;
    const y = this.y * CELL_SIZE;
    return [x, y];
  }

  changesHeroSkinOnCollide() {
    return null;
  }

  damagesBodyOnCollide(_body) {
    return null;
  }

  completesLevelOnCollide() {
    return false;
  }

  displayMovingXY() {
    const x = this.x * CELL_SIZE;
    const y = this.y * CELL_SIZE;
    const progressPixels = CELL_SIZE - this.movingPixelsRemaining;
    switch (this.movingPixelDirection) {
      case DIRECTION_LEFT:
        return [x - progressPixels, y];
      case DIRECTION_RIGHT:
        return [x + progressPixels, y];
      case DIRECTION_UP:
        return [x, y - progressPixels];
      case DIRECTION_DOWN:
        return [x, y + progressPixels];
    }
  }

  canBeUnlocked() {
    return false;
  }

  zIndex() {
    return this.y;
  }

  renderComponent() {
    return null;
  }
}