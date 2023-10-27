import {
  PLACEMENT_TYPE_HERO,
  PLACEMENT_TYPE_GOAL,
  PLACEMENT_TYPE_WALL,
  PLACEMENT_TYPE_CELEBRATION,
  PLACEMENT_TYPE_ENGINE_SMOKE,
  PLACEMENT_TYPE_WATER,
  PLACEMENT_TYPE_FIRE,
  PLACEMENT_TYPE_MINERAL_DIRT,
  PLACEMENT_TYPE_MINERAL_GOLD,
  PLACEMENT_TYPE_MINERAL_COPPER,
  PLACEMENT_TYPE_MINERAL_ZINC,
  PLACEMENT_TYPE_MINERAL_LITHIUM,
  PLACEMENT_TYPE_MINERAL_YTTRIUM,
  PLACEMENT_TYPE_MINERAL_NEODYMIUM,
  PLACEMENT_TYPE_TOPSOIL,
  PLACEMENT_TYPE_TUTORIAL,
  PLACEMENT_TYPE_ENDGAME,
} from "../helpers/consts";
import { HeroPlacement } from "../game-objects/HeroPlacement";
import { GoalPlacement } from "../game-objects/GoalPlacement";
import { WallPlacement } from "../game-objects/WallPlacement";
import { CelebrationPlacement } from "../game-objects/CelebrationPlacement";
import { EngineSmokePlacement } from "../game-objects/EngineSmokePlacement";
import { WaterPlacement } from "../game-objects/WaterPlacement";
import { FirePlacement } from "../game-objects/FirePlacement";
import { MineralPlacement } from "../game-objects/MineralPlacement";
import { TopsoilPlacement } from "../game-objects/TopsoilPlacement";
import { TutorialPlacement } from "../game-objects/TutorialPlacement";
import { EndGamePlacement } from "../game-objects/EndGamePlacement";

const placementTypeClassMap = {
  [PLACEMENT_TYPE_HERO]: HeroPlacement,
  [PLACEMENT_TYPE_GOAL]: GoalPlacement,
  [PLACEMENT_TYPE_WALL]: WallPlacement,
  [PLACEMENT_TYPE_CELEBRATION]: CelebrationPlacement,
  [PLACEMENT_TYPE_ENGINE_SMOKE]: EngineSmokePlacement,
  [PLACEMENT_TYPE_WATER]: WaterPlacement,
  [PLACEMENT_TYPE_FIRE]: FirePlacement,
  [PLACEMENT_TYPE_MINERAL_DIRT]: MineralPlacement,
  [PLACEMENT_TYPE_MINERAL_COPPER]: MineralPlacement,
  [PLACEMENT_TYPE_MINERAL_ZINC]: MineralPlacement,
  [PLACEMENT_TYPE_MINERAL_LITHIUM]: MineralPlacement,
  [PLACEMENT_TYPE_MINERAL_GOLD]: MineralPlacement,
  [PLACEMENT_TYPE_MINERAL_NEODYMIUM]: MineralPlacement,
  [PLACEMENT_TYPE_MINERAL_YTTRIUM]: MineralPlacement,
  [PLACEMENT_TYPE_TOPSOIL]: TopsoilPlacement,
  [PLACEMENT_TYPE_TUTORIAL]: TutorialPlacement,
  [PLACEMENT_TYPE_ENDGAME]: EndGamePlacement,
};

class PlacementFactory {
  createPlacement(config, level) {
    const placementClass = placementTypeClassMap[config.type];
    if (!placementClass) {
      console.warn("NO TYPE FOUND", config.type);
    }
    // Generate a new instance with random ID
    const instance = new placementClass(config, level);
    instance.id = Math.floor(Math.random() * 999999999) + 1;
    return instance;
  }
}

export const placementFactory = new PlacementFactory();