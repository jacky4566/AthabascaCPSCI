import DemoLevel from "./DemoLevel";
import Level1 from "./Level1";
import Level2 from "./Level2";
import Level3 from "./Level3";
import EndGame from "./EndGame"

/*
Level mapping, game will progress through this list

Jackson Wiebe 
3519635
09/10/2023
*/

const Levels = {
  DemoLevel: DemoLevel,
  Level1: Level1,
  Level2: Level2,
  Level3: Level3,
  EndGame: EndGame,
};

export default Levels;