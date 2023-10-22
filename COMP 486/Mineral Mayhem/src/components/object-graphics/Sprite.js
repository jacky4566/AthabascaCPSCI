/*
This dedicated function allows us to grab a sprite from the sprite sheet and draw it to our canvas as defined in the parent HTML.
We use a memo function to reduce the number of re-draws to only when the input parameters change for changing sprites.
Code was written with the help of this tutorial https://www.coopmode.dev/series/ciabattas-revenge/

Jackson Wiebe 
3519635
09/10/2023
*/
import  React from "react";
import { useEffect, useRef } from "react";
import { useRecoilValue } from "recoil";
import { spriteSheetImageAtom } from "../../atoms/spriteSheetImageAtom";
import { CELL_SIZE } from "../../helpers/consts";

function Sprite({ frameCoord, size = 32 }) {
    const spriteSheetImage = useRecoilValue(spriteSheetImageAtom);

    const canvasRef = useRef();
    useEffect(() => {
        /** @type {HTMLCanvasElement} */
        const canvasEl = canvasRef.current;
        const ctx = canvasEl.getContext("2d");

        //Clear out anything in the canvas tag
        ctx.clearRect(0, 0, canvasEl.width, canvasEl.height);

        //Draw a graphic to the canvas tag
        const tileSheetX = Number(frameCoord.split("x")[0]); //Using a string frameCoord, here is easier than an array or fancier data block
        const tileSheetY = Number(frameCoord.split("x")[1]);

        ctx.drawImage(
            spriteSheetImage, // Image to pull from
            tileSheetX * CELL_SIZE, // Left X corner of frame
            tileSheetY * CELL_SIZE, // Top Y corner of frame
            size, //How much to crop from the sprite sheet (X)
            size, //How much to crop from the sprite sheet (Y)
            0, //Where to place this on canvas tag X (0)
            0, //Where to place this on canvas tag Y (0)
            size, //How large to scale it (X)
            size //How large to scale it (Y)
        );
    }, [spriteSheetImage, frameCoord, size]);

    return <canvas width={size} height={size} ref={canvasRef} />;
}

const MemoizedSprite = React.memo(Sprite); //Allows us to return a saved sprite unless the input parameters change
export default MemoizedSprite;