/*
Constants for our game

Writte with help from Ciabatta's revenge Tutorial by Drew Conley

Jackson Wiebe 
3519635
09/10/2023
*/

export const CELL_SIZE = 32;
export const Z_INDEX_LAYER_SIZE = 10;
export const SPRITE_SHEET_SRC = "/mineral-mayhem-sprites.png"

export const PLACEMENT_TYPE_HERO = "HERO";
export const PLACEMENT_TYPE_GOAL = "GOAL";
export const PLACEMENT_TYPE_WALL = "WALL";
export const PLACEMENT_TYPE_CELEBRATION = "CELEBRATION";
export const PLACEMENT_TYPE_ENGINE_SMOKE = "ENGINES_SMOKE";
export const PLACEMENT_TYPE_WATER = "WATER";
export const PLACEMENT_TYPE_FIRE = "FIRE";
export const PLACEMENT_TYPE_TOPSOIL = "TOPSOIL";
export const PLACEMENT_TYPE_MINERAL_DIRT = "MINERAL_DIRT";
export const PLACEMENT_TYPE_MINERAL_COPPER = "MINERAL_COPPER";
export const PLACEMENT_TYPE_MINERAL_ZINC = "MINERAL_ZINC";
export const PLACEMENT_TYPE_MINERAL_GOLD = "MINERAL_GOLD";
export const PLACEMENT_TYPE_MINERAL_LITHIUM = "MINERAL_LITHIUM";
export const PLACEMENT_TYPE_MINERAL_NEODYMIUM = "MINERAL_NEODYMIUM";
export const PLACEMENT_TYPE_MINERAL_YTTRIUM = "MINERAL_YTTRIUM";
export const PLACEMENT_TYPE_TUTORIAL = "TUTORIAL";
export const PLACEMENT_TYPE_ENDGAME = "ENDGAME";

export const DIRECTION_LEFT = "LEFT";
export const DIRECTION_RIGHT = "RIGHT";
export const DIRECTION_UP = "UP";
export const DIRECTION_DOWN = "DOWN";
export const DIRECTION_IDLE = "IDLE";

export const DEATH_TYPE_FUEL = "FUEL";

export const directionUpdateMap = {
  [DIRECTION_LEFT]: { x: -1, y: 0 },
  [DIRECTION_RIGHT]: { x: 1, y: 0 },
  [DIRECTION_UP]: { x: 0, y: -1 },
  [DIRECTION_DOWN]: { x: 0, y: 1 },
};

export const BODY_SKINS = {
  NORMAL: "NORMAL",
  DEATH: "DEATH",
};

export const HERO_RUN_1 = "HERO_RUN_1";
export const HERO_RUN_2 = "HERO_RUN_2";

export const LOCK_KEY_COLORS = {
  BLUE: "BLUE",
  GREEN: "GREEN",
};

export const MINERAL_HEALTH = {
  TOPSOIL: 1,
  DIRT: 5,
  COPPER: 5,
  ZINC: 10,
  GOLD: 20,
  LITHIUM: 50,
  NEODYMIUM: 100,
  YTTRIUM: 200,
}

export const MINERAL_VALUE = {
  TOPSOIL: 0,
  DIRT: 0,
  COPPER: 5,
  ZINC: 10,
  GOLD: 20,
  LITHIUM: 50,
  NEODYMIUM: 100,
  YTTRIUM: 200,
}

export const LEVEL_THEMES = {
  UNDERGROUND: "EARTH",
  ICECAVE: "ICECAVE",
  EARTH: "UNDERGROUND",
};

export const THEME_BACKGROUNDS = {
  [LEVEL_THEMES.UNDERGROUND]: "#2f2808",
  [LEVEL_THEMES.ICECAVE]: "#3d4c67",
  [LEVEL_THEMES.EARTH]: "#2f2808",
};

export const THEME_TILES_MAP = {
  [LEVEL_THEMES.UNDERGROUND]: {
    FLOOR: "1x1",
    TOP: "1x0",
    SKY: "1x0",
    LEFT: "0x1",
    RIGHT: "2x1",
    BOTTOM: "1x2",
    WALL: "0x2",
  },
  [LEVEL_THEMES.ICECAVE]: {
    FLOOR: "4x1",
    TOP: "4x0",
    SKY: "4x0",
    LEFT: "3x1",
    RIGHT: "5x1",
    BOTTOM: "4x2",
    WALL: "3x2",
  },
  [LEVEL_THEMES.EARTH]: {
    FLOOR: "7x1",
    TOP: "7x0",
    SKY: "6x0",
    LEFT: "6x1",
    RIGHT: "8x1",
    BOTTOM: "7x2",
    WALL: "6x2",
  },
};