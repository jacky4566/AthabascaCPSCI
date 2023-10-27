import { Placement } from "./Placement";
import Hero from "../components/object-graphics/Hero";
import soundsManager, { SFX } from "../classes/Sounds";
import {
  DIRECTION_LEFT,
  DIRECTION_RIGHT,
  directionUpdateMap,
  BODY_SKINS,
  HERO_RUN_1,
  HERO_RUN_2,
  Z_INDEX_LAYER_SIZE,
  PLACEMENT_TYPE_CELEBRATION,
  PLACEMENT_TYPE_ENGINE_SMOKE,
  CELL_SIZE,
  DIRECTION_DOWN,
  DIRECTION_UP,
} from "../helpers/consts";
import { TILES } from "../helpers/tiles";
import { Collision } from "../classes/Collision";

const heroSkinMap = {
  [BODY_SKINS.NORMAL]: TILES.HERO_RIGHT,
  [HERO_RUN_1]: TILES.HERO_RUN_1_RIGHT,
  [HERO_RUN_2]: TILES.HERO_RUN_2_RIGHT,
};

export class HeroPlacement extends Placement {
  controllerMoveRequested(direction) {
    //Reset Drill
    this.mining = false;
    this.drillDirection = direction;

    //Hero is already moving
    if (this.movingPixelsRemaining > 0) {
      return;
    }

    /* Get potential collision */
    const collision = this.getCollisionAtNextPosition(direction);

    /* Mine object if possible */
    if (collision.withMinable() && direction !== DIRECTION_UP) {
      const collideWithMinable = collision.withPlacementAddsToInventory();
      if (collideWithMinable) {
        collideWithMinable.mine();
        this.mining = true;
      }
    }

    //Make sure the next space is available
    if (this.isSolidAtNextPosition(direction)) {
      return;
    }

    //Start the move
    this.movingPixelsRemaining = CELL_SIZE;
    this.movingPixelDirection = direction;
    this.updateFacingDirection();
    this.updateWalkFrame();
    this.level.fuel.consumeFuel(direction);

    //Play sound
    soundsManager.playSfx(SFX.DRIVING);

    //Add smoke
    this.level.addPlacement({
      type: PLACEMENT_TYPE_ENGINE_SMOKE,
      x: this.x,
      y: this.y,
    });
  }

  gravityMoveRequested() {
    //Called if no user move was requested

    //Hero is already moving
    if (this.movingPixelsRemaining > 0) {
      return;
    }

    //Make sure the next space is available
    if (this.isSolidAtNextPosition(DIRECTION_DOWN)) {
      return;
    }

    //Start the move
    this.movingPixelsRemaining = CELL_SIZE;
    this.movingPixelDirection = DIRECTION_DOWN;
    this.updateFacingDirection();
    this.updateWalkFrame();
  }

  getCollisionAtNextPosition(direction) {
    const { x, y } = directionUpdateMap[direction];
    const nextX = this.x + x;
    const nextY = this.y + y;
    return new Collision(this, this.level, {
      x: nextX,
      y: nextY,
    });
  }

  getLockAtNextPosition(direction) {
    const collision = this.getCollisionAtNextPosition(direction);
    return collision.withLock();
  }

  isSolidAtNextPosition(direction) {
    const collision = this.getCollisionAtNextPosition(direction);
    const isOutOfBounds = this.level.isPositionOutOfBounds(
      collision.x,
      collision.y
    );
    if (isOutOfBounds) {
      return true;
    }
    return Boolean(collision.withSolidPlacement());
  }

  updateFacingDirection() {
    if (
      this.movingPixelDirection === DIRECTION_LEFT ||
      this.movingPixelDirection === DIRECTION_RIGHT
    ) {
      this.spriteFacingDirection = this.movingPixelDirection;
    }
  }

  updateWalkFrame() {
    this.spriteWalkFrame = this.spriteWalkFrame === 1 ? 0 : 1;
  }

  tick() {
    this.tickMovingPixelProgress();
    this.level.fuel.consumeFuel("IDLE");
  }

  onDoneMoving() {
    //Update my x/y!
    const { x, y } = directionUpdateMap[this.movingPixelDirection];
    this.x += x;
    this.y += y;
    this.handleCollisions();
  }

  handleCollisions() {
    // handle collisions!
    const collision = new Collision(this, this.level);

    this.skin = BODY_SKINS.NORMAL;
    const changesHeroSkin = collision.withChangesHeroSkin();
    if (changesHeroSkin) {
      this.skin = changesHeroSkin.changesHeroSkinOnCollide();
    }

    const collideThatAddsToInventory = collision.withPlacementAddsToInventory();
    if (collideThatAddsToInventory) {
      collideThatAddsToInventory.collect();
      this.level.addPlacement({
        type: PLACEMENT_TYPE_CELEBRATION,
        x: this.x,
        y: this.y,
      });
    }

    const takesDamages = collision.withSelfGetsDamaged();
    if (takesDamages) {
      this.level.setDeathOutcome(takesDamages.type);
    }

    const completesLevel = collision.withCompletesLevel();
    if (completesLevel) {
      this.level.completeLevel();
    }
  }

  getFrame() {
    // If dead, show the dead skin
    /* TODO
    if (this.level.deathOutcome) {
      return heroSkinMap[BODY_SKINS.DEATH];
    }*/

    //Use correct walking frame per direction
    if (this.movingPixelsRemaining > 0 && this.skin === BODY_SKINS.NORMAL) {
      const walkKey = this.spriteWalkFrame === 0 ? HERO_RUN_1 : HERO_RUN_2;
      return heroSkinMap[walkKey];
    }

    return heroSkinMap[this.skin];
  }

  getYTranslate() {
    // Stand on ground when not moving
    if (this.movingPixelsRemaining === 0 || this.skin !== BODY_SKINS.NORMAL) {
      return 0;
    }

    //Elevate ramp up or down at beginning/end of movement
    const PIXELS_FROM_END = 2;
    if (
      this.movingPixelsRemaining < PIXELS_FROM_END ||
      this.movingPixelsRemaining > CELL_SIZE - PIXELS_FROM_END
    ) {
      return -1;
    }

    // Highest in the middle of the movement
    return -2;
  }

  getDrill() {
    if (this.mining) {
      switch (this.drillDirection) {
        case DIRECTION_RIGHT:
        case DIRECTION_LEFT:
          return "DRILL_1_RIGHT"
        case DIRECTION_DOWN:
          return "DRILL_1_DOWN"
        case DIRECTION_UP:
          return null;
      }
    }
    else
      return null
  }

  getEngine() {
    return "ENGINE_1";
  }

  tickMovingPixelProgress() {
    if (this.movingPixelsRemaining === 0) {
      return;
    }
    this.movingPixelsRemaining -= this.travelPixelsPerFrame;
    if (this.movingPixelsRemaining <= 0) {
      this.movingPixelsRemaining = 0;
      this.onDoneMoving();
    }
  }

  zIndex() {
    return this.y * Z_INDEX_LAYER_SIZE + 1;
  }

  renderComponent() {
    return (
      <Hero
        frameCoord={this.getFrame()}
        xMirror={this.spriteFacingDirection === DIRECTION_RIGHT ? 1 : -1}
        drill={this.getDrill()}
      />
    );
  }
}