import {
    LEVEL_THEMES,
    PLACEMENT_TYPE_FLOUR,
    PLACEMENT_TYPE_GOAL,
    PLACEMENT_TYPE_HERO,
    PLACEMENT_TYPE_WATER,
    PLACEMENT_TYPE_WATER_PICKUP,
    PLACEMENT_TYPE_TOPSOIL,
    PLACEMENT_TYPE_MINERAL,
} from "../../helpers/consts";

function getRandom(odds) {
    let probability = 1 / odds;
    return Math.random() < probability;
}

function isItemExistsAtPosition(placements, x, y) {
    return placements.some(item => item.x === x && item.y === y);
}

export default function LevelGenerator(level) {
    const placeItems = [];

    /* Add static items */
    level.placements.forEach((item) => {
        placeItems.push(item);
    })

    /* Add Top Soil */
    for (let x = 1; x <= level.tilesWidth; x++) {
        const newPlacement = { x: x, y: 1, type: PLACEMENT_TYPE_TOPSOIL };
        placeItems.push(newPlacement);
    };

    /* Add RNG items to level */
    level.RNG.forEach((item) => {
        for (let y = item.depthStart; y <= item.depthEnd; y++) {
            for (let x = 1; x <= level.tilesWidth; x++) {
                /* Check if space is already taken and the odds say we can place */
                if (!isItemExistsAtPosition(placeItems, x, y) && getRandom(item.odds)) {
                    /* Do random number fill */
                    const newPlacement = { x: x, y: y, type: item.type };
                    placeItems.push(newPlacement);
                }
            }
        }
    });


    return placeItems;
}