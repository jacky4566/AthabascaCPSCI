import { Placement } from "./Placement";
import { Z_INDEX_LAYER_SIZE } from "../helpers/consts";
import Sprite from "../components/object-graphics/Sprite";
import { TILES } from "../helpers/tiles";

export class EngineSmokePlacement extends Placement {
  constructor(properties, level) {
    super(properties, level);
    this.frame = 1;
  }

  tick() {
    if (this.frame <= 4) {
      this.frame += 0.5;
      return;
    }
    this.level.deletePlacement(this);
  }

  zIndex() {
    return this.y * Z_INDEX_LAYER_SIZE + 2;
  }

  renderComponent() {
    const frameCoord = `SMOKE_PARTICLE_${Math.ceil(this.frame)}`;

    return (
      <div
        style={{
          transform: `translateY(-20px)`,
        }}
      >
        <Sprite frameCoord={TILES[frameCoord]} />
      </div>
    )
  }
}