import { PLACEMENT_TYPE_HERO } from "../helpers/consts";
import { placementFactory } from "./PlacementFactory";
import { GameLoop } from "./GameLoop";
import { DirectionControls } from "./DirectionControls";
import LevelsMap from "../levels/LevelsMap";
import { Inventory } from "./Inventory";
import { Camera } from "./Camera";
import { LevelAnimatedFrames } from "./LevelAnimatedFrames";
import LevelGenerator from "../components/level-layout/LevelGenerator";
import { Fuel } from "./Fuel";

export class LevelState {
  constructor(levelId, onEmit) {
    this.id = levelId;
    this.onEmit = onEmit;
    this.directionControls = new DirectionControls();
    this.leveldata = [];

    //Start the level!
    this.start();
  }

  start() {
    console.log("Start")

    /* Default level stuff */
    this.isCompleted = false;
    this.deathOutcome = null;
    this.levelData = LevelsMap[this.id];

    /* Copy rest of level data */
    this.theme = this.levelData.theme;
    this.tilesWidth = this.levelData.tilesWidth;
    this.tilesHeight = this.levelData.tilesHeight;

    /* Generate Placements */
    const levelPlacements = LevelGenerator(this.levelData);

    this.placements = levelPlacements.map((item) => {
      return placementFactory.createPlacement(item, this);
    });

    // Create a fresh inventory
    this.inventory = new Inventory();

    // Fill fuel tank
    this.fuel = new Fuel(this);

    // Create a frame animation manager
    this.animatedFrames = new LevelAnimatedFrames();

    // Cache a reference to the hero
    this.heroRef = this.placements.find((p) => p.type === PLACEMENT_TYPE_HERO);

    // Create a camera
    this.camera = new Camera(this);

    this.startGameLoop();
  }

  setDeathOutcome(causeOfDeath) {
    this.deathOutcome = causeOfDeath;
    this.gameLoop.stop();
  }

  startGameLoop() {
    this.gameLoop?.stop();
    this.gameLoop = new GameLoop(() => {
      this.tick();
    });
  }

  addPlacement(config) {
    this.placements.push(placementFactory.createPlacement(config, this));
  }

  deletePlacement(placementToRemove) {
    this.placements = this.placements.filter((p) => {
      return p.id !== placementToRemove.id;
    });
  }

  tick() {
    // Check for movement here...
    if (this.directionControls.direction) {
      this.heroRef.controllerMoveRequested(this.directionControls.direction);
    } else {
      this.heroRef.gravityMoveRequested();
    }

    // Call 'tick' on any Placement that wants to update
    this.placements.forEach((placement) => {
      placement.tick();
    });

    // Update the camera
    this.camera.tick();

    //Tick fuel
    this.fuel.tick();

    // Work on animation frames
    this.animatedFrames.tick();

    //Emit any changes to React
    this.onEmit(this.getState());
  }
  0
  isPositionOutOfBounds(x, y) {
    return (
      x === 0 ||
      y < 0 ||
      x >= this.tilesWidth + 1 ||
      y >= this.tilesHeight + 1
    );
  }

  completeLevel() {
    this.isCompleted = true;
    this.gameLoop.stop();
  }

  getState() {
    return {
      theme: this.theme,
      tilesWidth: this.tilesWidth,
      tilesHeight: this.tilesHeight,
      placements: this.placements,
      deathOutcome: this.deathOutcome,
      isCompleted: this.isCompleted,
      cameraTransform: this.camera.transform,
      inventory: this.inventory,
      finishGoals: this.levelData.finishGoals,
      fuel: this.fuel,
      restart: () => {
        this.start();
      },
    };
  }

  destroy() {
    // Tear down the level.
    this.gameLoop.stop();
    this.directionControls.unbind();
  }
}