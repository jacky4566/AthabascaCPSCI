import Sprite from "./Sprite";
import { TILES } from "../../helpers/tiles";
import styles from "./Hero.module.css";

const heroOptions = [
  {
    key: "DRILL_1_RIGHT",
    tile: TILES.DRILL_1_RIGHT,
  },
  {
    key: "DRILL_1_DOWN",
    tile: TILES.DRILL_1_DOWN,
  },
];

export default function Hero({ frameCoord, xMirror, drill, engine }) {
  return (
    <div className={styles.hero}>
      <div
        className={styles.heroBody}
        style={{
          transform: `scaleX(${xMirror})`,
        }}
      >
        <Sprite frameCoord={frameCoord} size={64} />
      </div>
      {drill ? (
        <div
          className={styles.drillBody}
          style={{
            transform: `scaleX(${xMirror})`,
          }}
        >
          <Sprite frameCoord={heroOptions.find(option => option.key === drill).tile} size={64} />
        </div>) : (null)}
    </div>
  );
}

