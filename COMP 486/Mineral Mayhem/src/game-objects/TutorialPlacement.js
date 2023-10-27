import { Placement } from "./Placement";
import { CELL_SIZE } from "../helpers/consts";

export class TutorialPlacement extends Placement {
    cellOffset(input) {
        //y offset in half cell size steps
        return (input * (CELL_SIZE / 2));
    }

    renderComponent() {
        return (
            <svg height="256" width="1024" xmlns="http://www.w3.org/2000/svg">
                {/* Add Text */}

                <text x="0" y={this.cellOffset(1)} fontFamily="monospace" fill="white" stroke="black" strokeWidth=".1">
                    WELCOME TO MINERAL MAYHEM, New cars need rare earth metals, Lets go get em
                </text>

                <text x="0" y={this.cellOffset(2)} fontFamily="monospace" fill="white" stroke="black" strokeWidth=".1">
                    Collect Minerals to win
                </text>

                <text x="0" y={this.cellOffset(3)} fontFamily="monospace" fill="white" stroke="black" strokeWidth=".1">
                    Use Arrows, WASD, or Touch to move
                </text>

                <text x={this.cellOffset(22)} y={this.cellOffset(3)} fontFamily="monospace" fill="white" stroke="black" strokeWidth=".1">
                    \/ Portal to finish
                </text>

                <text x="0" y={this.cellOffset(5)} fontFamily="monospace" fill="white" stroke="black" strokeWidth=".1">
                    DIGGER{" >>>"}
                </text>

                <text x="0" y={this.cellOffset(9)} fontFamily="monospace" fill="white" stroke="black" strokeWidth=".1">
                    Minerals{" >>>"}
                </text>

                <text x="0" y={this.cellOffset(13)} fontFamily="monospace" fill="white" stroke="black" strokeWidth=".1">
                    Bad Things{" >>>"}
                </text>
            </svg>
        )
    }

    zIndex() {
        return 10;
    }
}