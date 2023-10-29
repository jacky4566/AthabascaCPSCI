import styles from "./PopupMessage.module.css";
import LevelFailedSvg from "../object-graphics/LevelFailedSvg";
import Sprite from "../object-graphics/Sprite";
import { TILES } from "../../helpers/tiles";
import {
  DEATH_TYPE_FUEL,
  PLACEMENT_TYPE_WATER,
  PLACEMENT_TYPE_FIRE,
} from "../../helpers/consts";
import { useKeyPress } from "../../hooks/useKeyPress";

/*
Renders death message when user fails

Jackson Wiebe 
3519635
09/10/2023
*/

const showDeathType = (deathType) => {
  switch (deathType) {
    case PLACEMENT_TYPE_WATER:
      return <Sprite frameCoord={TILES.WATER1} />;
    case PLACEMENT_TYPE_FIRE:
      return <Sprite frameCoord={TILES.FIRE1} />;
    case DEATH_TYPE_FUEL:
      return <Sprite frameCoord={TILES.CLOCK} />;
    default:
      console.error("Unknown Death")
      return null;
  }
};

export default function DeathMessage({ level }) {
  const handleRestartLevel = () => {
    level.restart();
  };

  useKeyPress("Enter", () => {
    handleRestartLevel();
  });

  return (
    <div className={styles.outerContainer}>
      <div className={styles.popupContainer}>
        <button onClick={handleRestartLevel} className={styles.quietButton}>
          <LevelFailedSvg />
          <div className={styles.deathTypeContainer}>
            {showDeathType(level.deathOutcome)}
          </div>
        </button>
      </div>
    </div>
  );
}