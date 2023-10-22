import styles from "./PopupMessage.module.css";
import LevelFailedSvg from "../object-graphics/LevelFailedSvg";
import Sprite from "../object-graphics/Sprite";
import { TILES } from "../../helpers/tiles";
import {
  DEATH_TYPE_CLOCK,
  PLACEMENT_TYPE_WATER,
} from "../../helpers/consts";
import { useKeyPress } from "../../hooks/useKeyPress";

const showDeathType = (deathType) => {
  switch (deathType) {
    case PLACEMENT_TYPE_WATER:
      return <Sprite frameCoord={TILES.WATER1} />;
    case DEATH_TYPE_CLOCK:
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