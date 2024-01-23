package game;


import javax.swing.JFrame;

/*
 * Jackson Wiebe
 * 3519635
 * 21/01/2024
 * 
 * Main Class to start game
 * 
 */

public class AntFarm extends JFrame {

	public AntFarm() {
		add(new Model());
	}

	public static void main(String[] args) {
		AntFarm window = new AntFarm();

		window.setTitle("Ant Farm");
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setSize(CONST.CONSOLE_AREA_X + CONST.GAME_AREA_X + 16, CONST.CONSOLE_AREA_Y + 39); //No idea where this offset is coming from
		window.setVisible(true);
	}

}
