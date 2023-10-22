import {
  LEVEL_THEMES,
  PLACEMENT_TYPE_HERO,
  PLACEMENT_TYPE_WATER,
  PLACEMENT_TYPE_MINERAL_DIRT,
  PLACEMENT_TYPE_MINERAL_COPPER,
  PLACEMENT_TYPE_MINERAL_ZINC,
  PLACEMENT_TYPE_MINERAL_GOLD,
  PLACEMENT_TYPE_MINERAL_LITHIUM,
  PLACEMENT_TYPE_MINERAL_YTTRIUM,
  PLACEMENT_TYPE_MINERAL_NEODYMIUM,
} from "../helpers/consts";
import { TILES } from "../helpers/tiles";

const level = {
  theme: LEVEL_THEMES.EARTH,  // Theme of level sets background
  tilesWidth: 24,             // Size of Level
  tilesHeight: 100,           // Size of Level
  RNG: [                       // Variable for the RNG to fill in our map
    { depthStart: 2, depthEnd: 16, odds: 20, type: PLACEMENT_TYPE_WATER },
    { depthStart: 50, depthEnd: 100, odds: 100, type: PLACEMENT_TYPE_MINERAL_GOLD },
    { depthStart: 2, depthEnd: 20, odds: 20, type: PLACEMENT_TYPE_MINERAL_COPPER },
    { depthStart: 2, depthEnd: 50, odds: 20, type: PLACEMENT_TYPE_MINERAL_ZINC },
    { depthStart: 2, depthEnd: 100, odds: 1.5, type: PLACEMENT_TYPE_MINERAL_DIRT },
  ],
  placements: [
    { x: 1, y: 0, type: PLACEMENT_TYPE_HERO },
    { x: 1, y: 2, type: PLACEMENT_TYPE_MINERAL_DIRT },
    { x: 2, y: 2, type: PLACEMENT_TYPE_MINERAL_COPPER },
    { x: 3, y: 2, type: PLACEMENT_TYPE_MINERAL_ZINC },
    { x: 4, y: 2, type: PLACEMENT_TYPE_MINERAL_GOLD },
    { x: 5, y: 2, type: PLACEMENT_TYPE_MINERAL_LITHIUM },
    { x: 6, y: 2, type: PLACEMENT_TYPE_MINERAL_NEODYMIUM },
    { x: 7, y: 2, type: PLACEMENT_TYPE_MINERAL_YTTRIUM },
  ],
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