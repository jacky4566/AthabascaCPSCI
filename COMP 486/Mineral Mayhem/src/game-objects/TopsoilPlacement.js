import { Placement } from "./Placement";
import soundsManager, { SFX } from "../classes/Sounds";
import Sprite from "../components/object-graphics/Sprite";
import { THEME_TILES_MAP, MINERAL_HEALTH } from "../helpers/consts";

export class TopsoilPlacement extends Placement {
  constructor(properties, level) {
    super(properties, level);
    this.health = MINERAL_HEALTH.TOPSOIL;
    this.damageNextTick = 0;
  }

  addsItemToInventoryOnCollide() {
    return this.type;
  }

  isSolidForBody(_body) {
    return !!(this.health);
  }

  isMinable() {
    return !!(this.health);
  }

  mine(_body) {
    this.damageNextTick = 1;
    console.log("mining")
    return null;
  }

  tick() {
    if (this.hasBeenCollected)
      return;
    if (this.damageNextTick) {
      this.health = this.health - this.damageNextTick;
      this.damageNextTick = 0;
      if (this.health <= 0) {
        soundsManager.playSfx(SFX.COLLECT);
        console.log(this.health)
        this.level.inventory.add(this);
        this.hasBeenCollected = true;
        this.health = 0;
      }
    }
  }

  renderComponent() {
    const TopsoilTileCoord = THEME_TILES_MAP[this.level.theme].TOP;
    return <Sprite frameCoord={TopsoilTileCoord} />;
  }
}