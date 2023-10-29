/*
The main function for our game

Writte with help from Ciabatta's revenge Tutorial by Drew Conley

Jackson Wiebe 
3519635
09/10/2023
*/

import { useEffect } from "react";
import { SPRITE_SHEET_SRC } from "./helpers/consts";
import RenderLevel from "./components/level-layout/RenderLevel";
import { useRecoilState } from "recoil";
import { spriteSheetImageAtom } from "./atoms/spriteSheetImageAtom";
import soundsManager from "./classes/Sounds";

function App() {
  const [spriteSheetImage, setSpriteSheetImage] = useRecoilState(spriteSheetImageAtom);

  useEffect(() => {
    soundsManager.init();
    const image = new Image();    //Create new image constant
    image.src = SPRITE_SHEET_SRC; //Get referecne from global constants
    image.onload = () => {        //When the image loads
      setSpriteSheetImage(image); //apply it to the variable
    };
  }, [setSpriteSheetImage]);

  if (!spriteSheetImage) {
    //If Sprite sheet failed return an error
    return (<p>Sprite Sheet Failed!</p>);
  }

  return <RenderLevel />;
}

export default App;