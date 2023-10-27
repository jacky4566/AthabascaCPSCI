import {
  LEVEL_THEMES,
  PLACEMENT_TYPE_HERO,
  PLACEMENT_TYPE_ENDGAME,
  PLACEMENT_TYPE_GOAL
} from "../helpers/consts";
import { TILES } from "../helpers/tiles";

const level = {
  theme: LEVEL_THEMES.EARTH,  // Theme of level sets background
  tilesWidth: 5,             // Size of Level
  tilesHeight: 5,           // Size of Level
  RNG: [                       // Variable for the RNG to fill in our map

  ],
  //Fix level placements
  placements: [
    { x: 1, y: 0, type: PLACEMENT_TYPE_HERO },
    { x: 2, y: -1, type: PLACEMENT_TYPE_ENDGAME },
    { x: 5, y: 0, type: PLACEMENT_TYPE_GOAL },
  ],
  //What we need to collect to finish the level
  finishGoals: [
    {
      key: "MINERAL_COPPER",
      tile: TILES.MINERAL_COPPER,
      amount: 0,
    },
    {
      key: "MINERAL_ZINC",
      tile: TILES.MINERAL_ZINC,
      amount: 0,
    },
    {
      key: "MINERAL_GOLD",
      tile: TILES.MINERAL_GOLD,
      amount: 0,
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