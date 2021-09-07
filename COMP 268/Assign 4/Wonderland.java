public class Wonderland {
        public static void main(String[] args) {
                gameStatus gameState = gameStatus.NORMAL;

                Character myPlayer = new Character("Alice");

                Control.printFile(Control.WELCOMEFILE);

                while (gameState == gameStatus.NORMAL) {
                        gameState = process();
                }
                switch (gameState) {
                        case DEAD:
                                break;
                        case POISON:
                                break;
                        case QUIT:
                                System.out.println("Thanks for Playing!");
                                break;
                        case WIN:
                                System.out.println("You win!");
                                break;
                        case NORMAL: //This case should never be expected here
                        default:
                                System.out.println("ERROR: Unexpected Case");
                                break;
                }

        }
}