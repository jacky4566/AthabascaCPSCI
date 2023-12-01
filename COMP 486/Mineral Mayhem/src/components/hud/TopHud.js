import styles from "./TopHud.module.css";
import InventoryList from "./InventoryList";

/*
Just used to export the HUD types. We may want more

Jackson Wiebe 
3519635
09/10/2023
*/

export default function TopHud({ level }) {
  return (
    <div className={styles.topHud}>
      <div className={styles.topHudLeft}>
        <InventoryList level={level} />
      </div>
    </div>
  );
}