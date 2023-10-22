import styles from "./InventoryList.module.css";
import { TILES } from "../../helpers/tiles";
import Sprite from "../object-graphics/Sprite";
import PixelNumber from "../object-graphics/PixelNumber";

const showInventory = [
  {
    key: "TOPSOIL",
    tile: TILES.MINERAL_DIRT,
  },
  {
    key: "KEY_BLUE",
    tile: TILES.BLUE_KEY,
  },
  {
    key: "KEY_GREEN",
    tile: TILES.GREEN_KEY,
  },
];

export default function InventoryList({ level }) {
  return (
    <div className={styles.inventory}>
      {showInventory
        .filter((i) => {
          return level.inventory.has(i.key);
        })
        .map((i) => {
          const count = level.inventory.inventoryMap.get(i.key);
          return (
            
            <div key={i.key} className={styles.inventoryEntry}>
              <Sprite frameCoord={i.tile} />
              <PixelNumber number={count} />
            </div>
          );
        })}
    </div>
  );
}