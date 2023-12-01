import Sprite from "./Sprite";
import { TILES } from "../../helpers/tiles";
import styles from "./Hero.module.css";

export default function Body({ frameCoord, xMirror }) {
  return (
    <div className={styles.hero}>
      <div
        className={styles.heroBody}
        style={{
          transform: `scaleX(${xMirror})`,
        }}
      >
      </div>
    </div>
  );
}

