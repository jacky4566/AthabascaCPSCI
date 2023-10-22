import {
  LEVEL_THEMES,
  PLACEMENT_TYPE_HERO,
  PLACEMENT_TYPE_WATER,
  PLACEMENT_TYPE_TOPSOIL,
  PLACEMENT_TYPE_MINERAL_DIRT,
  PLACEMENT_TYPE_MINERAL_GOLD,
  PLACEMENT_TYPE_MINERAL_COPPER,
  PLACEMENT_TYPE_MINERAL_ZINC,
  PLACEMENT_TYPE_MINERAL_LITHIUM,
  PLACEMENT_TYPE_MINERAL_YTTRIUM,
  PLACEMENT_TYPE_MINERAL_NEODYMIUM,
} from "../helpers/consts";

const level = {
  theme: LEVEL_THEMES.EARTH,  // Theme of level sets background
  tilesWidth: 24,             // Size of Level
  tilesHeight: 100,           // Size of Level         
  RNG:[                       // Variable for the RNG to fill in our map
    {depthStart: 2, depthEnd: 16, odds: 20, type: PLACEMENT_TYPE_WATER},
  ],
  placements: [
    { x: 1, y: 0, type: PLACEMENT_TYPE_HERO },
    { x: 3, y: 0, type: PLACEMENT_TYPE_TOPSOIL },
    { x: 1, y: 2, type: PLACEMENT_TYPE_MINERAL_DIRT },
    { x: 2, y: 2, type: PLACEMENT_TYPE_MINERAL_COPPER },
    { x: 3, y: 2, type: PLACEMENT_TYPE_MINERAL_ZINC },
    { x: 4, y: 2, type: PLACEMENT_TYPE_MINERAL_GOLD },
    { x: 5, y: 2, type: PLACEMENT_TYPE_MINERAL_LITHIUM },
    { x: 6, y: 2, type: PLACEMENT_TYPE_MINERAL_NEODYMIUM },
    { x: 7, y: 2, type: PLACEMENT_TYPE_MINERAL_YTTRIUM },
  ],
};

export default level;