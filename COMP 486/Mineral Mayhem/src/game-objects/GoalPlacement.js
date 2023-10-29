import { Placement } from "./Placement";
import Sprite from "../components/object-graphics/Sprite";
import { TILES } from "../helpers/tiles";
import soundsManager, { SFX } from "../classes/Sounds";

/*
Level goal object for our game uses base placmenet and extends features to make it collide and work.

Writte with help from Ciabatta's revenge Tutorial by Drew Conley

Jackson Wiebe 
3519635
09/10/2023
*/

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