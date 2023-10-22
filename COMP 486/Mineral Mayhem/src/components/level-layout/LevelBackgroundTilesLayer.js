import MapCell from "./MapCell";
import { THEME_TILES_MAP, CELL_SIZE, } from "../../helpers/consts";

export default function LevelBackgroundTilesLayer({ level, cameraXY }) {
  const pixelSize = getComputedStyle(document.documentElement).getPropertyValue('--pixel-size');
  const widthWithWalls = level.tilesWidth + 1;
  const heightWithWalls = level.tilesHeight + 1;
  const cameraLefttile = Math.max(0, Math.floor((cameraXY[0] - (window.innerWidth / 2 / pixelSize)) / CELL_SIZE));
  const cameraRighttile = Math.min(widthWithWalls, Math.ceil((cameraXY[0] + (window.innerWidth / 2 / pixelSize)) / CELL_SIZE));
  const cameraTopttile = Math.max(0, Math.floor((cameraXY[1] - (window.innerHeight / 2 / pixelSize)) / CELL_SIZE));
  const cameraBottomttile = Math.min(heightWithWalls, Math.ceil((cameraXY[1] + (window.innerHeight / pixelSize)) / CELL_SIZE));
  const tiles = THEME_TILES_MAP[level.theme];

  function getBackgroundTile(x, y) {
    if (x === 0) {
      return tiles.LEFT;
    }
    if (x === widthWithWalls) {
      return tiles.RIGHT;
    }
    if (y === 0) {
      return tiles.SKY;
    }
    if (y === heightWithWalls) {
      return tiles.BOTTOM;
    }
    return tiles.FLOOR;
  }

  let canvases = [];
  for (let y = cameraTopttile; y <= cameraBottomttile; y++) {
    for (let x = cameraLefttile; x <= cameraRighttile; x++) {
      // Skip Bottom Left and Bottom Right for intentional blank tiles in those corners
      if (y === heightWithWalls) {
        if (x === 0 || x === widthWithWalls) {
          continue;
        }
      }

      // add a cell to the map
      canvases.push(
        <MapCell
          key={`${x}_${y}`}
          x={x}
          y={y}
          frameCoord={getBackgroundTile(x, y)}
        />
      );
    }
  }

  return <div>{canvases}</div>;
}