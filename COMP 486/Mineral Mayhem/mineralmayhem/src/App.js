/*
The main function for our game.

Jackson Wiebe 
3519635
09/10/2023
*/

import { useEffect, useState } from "react";
import { SPRITE_SHEET_SRC } from "./helpers/consts";
import RenderLevel from "./components/level-layout/RenderLevel";

function App() {
  const [spriteSheetImage, setSpriteSheetImage] = useState(null);

  useEffect(() => {
    const image = new Image();    //Create new image constant
    image.src = SPRITE_SHEET_SRC; //Get referecne from global constants
    image.onload = () => {        //When the image loads
      setSpriteSheetImage(image); //apply it to the variable
    };
  }, []);

  if (!spriteSheetImage) {
    //If Sprite sheet failed return an error
    return (<p>Sprite Sheet Failed!</p>);
  }

  return <RenderLevel spriteSheetImage={spriteSheetImage} />;
}

export default App;