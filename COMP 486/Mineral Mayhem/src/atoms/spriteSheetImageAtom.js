import { atom } from "recoil";

//Small Atom accessiable global to fetch the sprite sheet
 
export const spriteSheetImageAtom = atom({
  key: "spriteSheetImageAtom",
  default: null,
});