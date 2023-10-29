import { atom } from "recoil";

/*
Jackson Wiebe
3519635

Small Atom accessiable global to fetch the level
*/
 
export const currentLevelIdAtom = atom({
  key: "currentLevelIdAtom",
  default: "DemoLevel",
});