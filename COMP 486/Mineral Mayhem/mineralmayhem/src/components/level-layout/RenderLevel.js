import styles from "./RenderLevel.module.css";  //Use these styles
import Sprite from "../object-graphics/Sprite"; //Grab our sprite sheet
import {
  CELL_SIZE,
  LEVEL_THEMES,
  THEME_BACKGROUNDS,
} from "../../helpers/consts";
import LevelBackgroundTilesLayer from "./LevelBackgroundTilesLayer";

export default function RenderLevel({ spriteSheetImage }) {
  const level = {
    theme: LEVEL_THEMES.YELLOW,
    tilesWidth: 8,
    tilesHeight: 8,

    placements: [
      //Level '2'
      { id: 0, x: 1, y: 1, frameCoord: "0x2" },
      { id: 1, x: 3, y: 1, frameCoord: "0x2" },
      { id: 2, x: 5, y: 1, frameCoord: "0x2" },
      { id: 3, x: 7, y: 1, frameCoord: "0x2" },
      { id: 4, x: 9, y: 1, frameCoord: "0x2" },
      { id: 5, x: 11, y: 1, frameCoord: "0x2" },
      { id: 6, x: 13, y: 1, frameCoord: "0x2" },
      { id: 7, x: 15, y: 1, frameCoord: "0x2" },
    ],
  };

  return (
    <div
      className={styles.fullScreenContainer}
      style={{
        background: THEME_BACKGROUNDS[level.theme],
      }}
    >
      <div className={styles.gameScreen}>
      <LevelBackgroundTilesLayer level={level} image={spriteSheetImage} />
        {level.placements.map((placement) => {
          // Wrap each Sprite in a positioned div
          const x = placement.x * CELL_SIZE + "px";
          const y = placement.y * CELL_SIZE + "px";
          const style = {//Create an inline style to apply to the div
            position: "absolute",
            transform: `translate3d(${x}, ${y}, 0)`,
          };

          return (
            <div key={placement.id} style={style}>
              <Sprite
                image={spriteSheetImage}
                frameCoord={placement.frameCoord}
              />
            </div>
          );
        })}
      </div>
    </div>
  );
}