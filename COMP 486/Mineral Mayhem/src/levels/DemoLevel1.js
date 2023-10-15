import {
  LEVEL_THEMES,
  PLACEMENT_TYPE_FLOUR,
  PLACEMENT_TYPE_GOAL,
  PLACEMENT_TYPE_HERO,
  PLACEMENT_TYPE_WATER,
  PLACEMENT_TYPE_WATER_PICKUP,
} from "../helpers/consts";

const level = {
  theme: LEVEL_THEMES.GREEN,  // Theme of level sets background
  tilesWidth: 24,             // Size of Level
  tilesHeight: 100,           // Size of Level         
  RNG:[                       // Variable for the RNG to fill in our map
    {depthStart: 2, depthEnd: 16, odds: 20, type: PLACEMENT_TYPE_WATER},
  ],
  placements: [
    { x: 1, y: 1, type: PLACEMENT_TYPE_HERO },
    { x: 6, y: 4, type: PLACEMENT_TYPE_GOAL },
    { x: 3, y: 4, type: PLACEMENT_TYPE_WATER },
    { x: 4, y: 5, type: PLACEMENT_TYPE_WATER },
    { x: 3, y: 5, type: PLACEMENT_TYPE_WATER },
    { x: 2, y: 4, type: PLACEMENT_TYPE_WATER_PICKUP },
    { x: 3, y: 3, type: PLACEMENT_TYPE_FLOUR },
  ],
};

export default level;