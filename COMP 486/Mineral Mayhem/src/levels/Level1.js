import {
  LEVEL_THEMES,
  PLACEMENT_TYPE_HERO,
  PLACEMENT_TYPE_GOAL,
  PLACEMENT_TYPE_WATER,
  PLACEMENT_TYPE_MINERAL_DIRT,
  PLACEMENT_TYPE_MINERAL_COPPER,
  PLACEMENT_TYPE_MINERAL_ZINC,
  PLACEMENT_TYPE_MINERAL_GOLD,
} from "../helpers/consts";
import { TILES } from "../helpers/tiles";

const level = {
  theme: LEVEL_THEMES.EARTH,  // Theme of level sets background
  title: "Getting Started",
  tilesWidth: 24,             // Size of Level
  tilesHeight: 24,           // Size of Level
  RNG: [                       // Variable for the RNG to fill in our map
    { depthStart: 2, depthEnd: 24, odds: 20, type: PLACEMENT_TYPE_MINERAL_COPPER },
    { depthStart: 2, depthEnd: 24, odds: 20, type: PLACEMENT_TYPE_MINERAL_ZINC },
    { depthStart: 10, depthEnd: 24, odds: 50, type: PLACEMENT_TYPE_MINERAL_GOLD },
    { depthStart: 5, depthEnd: 24, odds: 50, type: PLACEMENT_TYPE_WATER },
    { depthStart: 2, depthEnd: 24, odds: 1.5, type: PLACEMENT_TYPE_MINERAL_DIRT },
  ],
  //Fix level placements
  placements: [
    { x: 1, y: 0, type: PLACEMENT_TYPE_HERO },
    { x: 24, y: 0, type: PLACEMENT_TYPE_GOAL },
  ],
  //What we need to collect to finish the level
  finishGoals: [
    {
      key: "MINERAL_COPPER",
      tile: TILES.MINERAL_COPPER,
      amount: 5,
    },
    {
      key: "MINERAL_ZINC",
      tile: TILES.MINERAL_ZINC,
      amount: 5,
    },
    {
      key: "MINERAL_GOLD",
      tile: TILES.MINERAL_GOLD,
      amount: 5,
    },
    {
      key: "MINERAL_LITHIUM",
      tile: TILES.MINERAL_LITHIUM,
      amount: 0,
    },
    {
      key: "MINERAL_NEODYMIUM",
      tile: TILES.MINERAL_NEODYMIUM,
      amount: 0,
    },
    {
      key: "MINERAL_YTTRIUM",
      tile: TILES.MINERAL_YTTRIUM,
      amount: 0,
    },
  ]
};

export default level;