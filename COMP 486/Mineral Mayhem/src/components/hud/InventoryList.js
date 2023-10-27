import styles from "./InventoryList.module.css";
import Sprite from "../object-graphics/Sprite";
import PixelNumber from "../object-graphics/PixelNumber";
import FuelTank from "./FuelTank";

export default function InventoryList({ level }) {
  const inventoryEntries = [];

  inventoryEntries.push(
    <div key={1} className={styles.inventoryEntry}>
      <FuelTank fuel={level.fuel} />
    </div >
  );

  level.finishGoals.forEach(goal => {
    if (goal.amount) {
      const amountCollected = level.inventory.inventoryMap.get(goal.key) ?? 0;
      const count = Math.max(goal.amount - amountCollected, 0);

      inventoryEntries.push(
        <div key={goal.key} className={styles.inventoryEntry}>
          <Sprite frameCoord={goal.tile} />
          <PixelNumber number={count} />
        </div>
      );
    }
  });

  return (
    <div className={styles.inventory}>
      {inventoryEntries}
    </div>
  );
}