import { Placement } from "./Placement";
import { CELL_SIZE } from "../helpers/consts";

export class EndGamePlacement extends Placement {
    yOffset(input){
        //y offset in half cell size steps
        return (input * (CELL_SIZE / 2));
    }

    renderComponent() {
        return (
            <svg height="256" width="512" xmlns="http://www.w3.org/2000/svg">
            {/* Add Text */}

            <text x="0" y={this.yOffset(1)} fontFamily="monospace" fill="white" stroke="black" strokeWidth=".1">
               You WIN!!
            </text>
        </svg>
        )
    }

    zIndex() {
        return 10;
      }
}