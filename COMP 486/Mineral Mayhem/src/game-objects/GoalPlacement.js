import { Placement } from "./Placement";
import Sprite from "../components/object-graphics/Sprite";
import { TILES } from "../helpers/tiles";
import soundsManager, { SFX } from "../classes/Sounds";

export class GoalPlacement extends Placement {
  get isDisabled() {
    for (const goal of this.level.levelData.finishGoals) {
      if (goal.amount) {
        const amountCollected = this.level.inventory.inventoryMap.get(goal.key) ?? 0;
        const count = Math.max(goal.amount - amountCollected, 0);
        if (count > 0)
          return true;
      }
    }
    //Goal is enabled
    return false;
  }

  completesLevelOnCollide() {
    if (!this.isDisabled)
      soundsManager.playSfx(SFX.WIN);
    return !this.isDisabled;
  }

  renderComponent() {
    return (
      <Sprite
        frameCoord={this.isDisabled ? TILES.GOAL_DISABLED : TILES.GOAL_ENABLED}
      />
    );
  }
}