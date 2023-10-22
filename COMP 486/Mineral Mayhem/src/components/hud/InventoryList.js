import styles from "./InventoryList.module.css";
import { TILES } from "../../helpers/tiles";
import Sprite from "../object-graphics/Sprite";
import PixelNumber from "../object-graphics/PixelNumber";

export default function InventoryList({ level }) {
  return (
    <div className={styles.inventory}>
      {level.finishGoals.map(goal => {
        if (goal.amount) {
          const amountCollected =  level.inventory.inventoryMap.get(goal.key) ?? 0;
          const count = Math.max(goal.amount - amountCollected,0);
          return (
            <div key={goal.key} className={styles.inventoryEntry}>
              <Sprite frameCoord={goal.tile} />
              <PixelNumber number={count} />
            </div>
          );
        } else {
          return null; // or any fallback content
        }
      })}
    </div>
  );
}