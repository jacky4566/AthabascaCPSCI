import {
  LEVEL_THEMES,
  PLACEMENT_TYPE_FLOUR,
  PLACEMENT_TYPE_GOAL,
  PLACEMENT_TYPE_HERO,
  PLACEMENT_TYPE_WATER,
  PLACEMENT_TYPE_WATER_PICKUP,
} from "../helpers/consts";

const level = {
  theme: LEVEL_THEMES.GREEN,
  tilesWidth: 16,
  tilesHeight: 16,
  depthStart: 0,
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