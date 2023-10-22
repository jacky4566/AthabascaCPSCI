import styles from "./InventoryList.module.css";
import { TILES } from "../../helpers/tiles";
import Sprite from "../object-graphics/Sprite";
import PixelNumber from "../object-graphics/PixelNumber";

const showInventory = [
  {
    key: "MINERAL_COPPER",
    tile: TILES.MINERAL_COPPER,
  },
  {
    key: "MINERAL_ZINC",
    tile: TILES.MINERAL_ZINC,
  },
  {
    key: "MINERAL_GOLD",
    tile: TILES.MINERAL_GOLD,
  },
  {
    key: "MINERAL_LITHIUM",
    tile: TILES.MINERAL_LITHIUM,
  },
  {
    key: "MINERAL_NEODYMIUM",
    tile: TILES.MINERAL_NEODYMIUM,
  },
  {
    key: "MINERAL_YTTRIUM",
    tile: TILES.MINERAL_YTTRIUM,
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