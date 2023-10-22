import { Placement } from "./Placement";
import Sprite from "../components/object-graphics/Sprite";
import { TILES } from "../helpers/tiles";
import { MINERAL_HEALTH } from "../helpers/consts";

export class MineralPlacement extends Placement {
  constructor(properties, level) {
    super(properties, level);
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

  isSolidForBody(_body) {
    return true;
  }

  renderComponent() {
    return <Sprite frameCoord={this.TileCoord} />;
  }
}