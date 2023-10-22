import { Placement } from "./Placement";
import soundsManager, { SFX } from "../classes/Sounds";
import Sprite from "../components/object-graphics/Sprite";
import { TILES } from "../helpers/tiles";
import { MINERAL_HEALTH,
  PLACEMENT_TYPE_CELEBRATION, } from "../helpers/consts";

export class MineralPlacement extends Placement {
  constructor(properties, level) {
    super(properties, level);
    this.damageNextTick = 0;
    switch (properties.type) {
      case "MINERAL_DIRT":
        this.health = MINERAL_HEALTH.DIRT;
        this.TileCoord = TILES.MINERAL_DIRT;
        break;
      case "MINERAL_COPPER":
        this.health = MINERAL_HEALTH.COPPER;
        this.TileCoord = TILES.MINERAL_COPPER;
        break;
      case "MINERAL_ZINC":
        this.health = MINERAL_HEALTH.ZINC;
        this.TileCoord = TILES.MINERAL_ZINC;
        break;
      case "MINERAL_GOLD":
        this.health = MINERAL_HEALTH.GOLD;
        this.TileCoord = TILES.MINERAL_GOLD;
        break;
      case "MINERAL_LITHIUM":
        this.health = MINERAL_HEALTH.LITHIUM;
        this.TileCoord = TILES.MINERAL_LITHIUM;
        break;
      case "MINERAL_NEODYMIUM":
        this.health = MINERAL_HEALTH.NEODYMIUM;
        this.TileCoord = TILES.MINERAL_NEODYMIUM;
        break;
      case "MINERAL_YTTRIUM":
        this.health = MINERAL_HEALTH.YTTRIUM;
        this.TileCoord = TILES.MINERAL_YTTRIUM;
        break;
    }
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
    return null;
  }
  
  tick() {
    if (this.hasBeenCollected)
      return;
    if (this.damageNextTick) {
      this.health = this.health - this.damageNextTick;
      this.damageNextTick = 0;
      this.level.addPlacement({
        type: PLACEMENT_TYPE_CELEBRATION,
        x: this.x,
        y: this.y,
      });
      if (this.health <= 0) {
        soundsManager.playSfx(SFX.COLLECT);
        this.level.inventory.add(this.type);
        this.hasBeenCollected = true;
        this.health = 0;
      }
    }
  }

  renderComponent() {
    return <Sprite frameCoord={this.TileCoord} />;
  }
}