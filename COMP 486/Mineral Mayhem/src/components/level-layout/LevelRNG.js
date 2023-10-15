import {
    LEVEL_THEMES,
    PLACEMENT_TYPE_FLOUR,
    PLACEMENT_TYPE_GOAL,
    PLACEMENT_TYPE_HERO,
    PLACEMENT_TYPE_WATER,
    PLACEMENT_TYPE_WATER_PICKUP,
  } from "../../helpers/consts";

export default function LevelRNG(level) {
    console.log(level.placements);

    const newPlacement = { x: 7, y: 2, type: PLACEMENT_TYPE_WATER };
    level.placements.push( newPlacement);

    return level.placements;
}