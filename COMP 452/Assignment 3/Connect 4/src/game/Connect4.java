package game;

import javax.swing.JFrame;

/*
 * Jackson Wiebe
 * 3519635
 * 06/02/2024
 * 
 * Main Class to start game
 * 
 */

public class Connect4 extends JFrame {

	public Connect4() {
		add(new Model());
	}

	public static void main(String[] args) {
		Connect4 window = new Connect4();

		window.setVisible(true);
		window.setTitle("Connect 4");
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.pack();
	}
}
