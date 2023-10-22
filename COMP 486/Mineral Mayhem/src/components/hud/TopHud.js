import styles from "./TopHud.module.css";
import InventoryList from "./InventoryList";

export default function TopHud({ level }) {
  return (
    <div className={styles.topHud}>
      <div className={styles.topHudLeft}>
        <InventoryList level={level} />
      </div>
      <div className={styles.topHudRight}>
        {/*<span>Come back to me</span>*/}
      </div>
    </div>
  );
}