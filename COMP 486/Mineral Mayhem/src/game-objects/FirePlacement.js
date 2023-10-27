import { Placement } from "./Placement";

import Sprite from "../components/object-graphics/Sprite";

import {
    PLACEMENT_TYPE_HERO,
} from "../helpers/consts";

export class FirePlacement extends Placement {
    damagesBodyOnCollide(body) {
        if (
            body.type === PLACEMENT_TYPE_HERO
        ) {
            return this.type;
        }
        return null;
    }

    renderComponent() {
        const fireFrame = this.level.animatedFrames.fireFrame;
        return <Sprite frameCoord={fireFrame} />;
    }
}