import styles from "./InventoryList.module.css";
import Sprite from "../object-graphics/Sprite";
import PixelNumber from "../object-graphics/PixelNumber";
import FuelTank from "./FuelTank";
import { useRecoilState } from "recoil";
import { currentLevelIdAtom } from "../../atoms/currentLevelIdAtom";

/*
List out our inventory

Writte with help from Ciabatta's revenge Tutorial by Drew Conley

Jackson Wiebe 
3519635
09/10/2023
*/

export default function InventoryList({ level }) {
  const [currentId, setCurrentId] = useRecoilState(currentLevelIdAtom);
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

  inventoryEntries.push(

    <div key={0} className={styles.inventoryEntry}>
      <svg height="64" width="64" xmlns="http://www.w3.org/2000/svg">
        <text x="50%" y="50%" textAnchor="middle" fill="#00ff00"  fontFamily="monospace">
          <tspan x="50%">{currentId}</tspan>
        </text>
      </svg>
    </div >

  );

  return (
    <div className={styles.inventory}>
      {inventoryEntries}
    </div>
  );
}