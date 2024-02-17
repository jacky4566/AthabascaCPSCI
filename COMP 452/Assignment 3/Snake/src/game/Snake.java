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

public class Snake extends JFrame {

	public Snake() {
		add(new Model());
	}

	public static void main(String[] args) {
		Snake window = new Snake();

		window.setVisible(true);
		window.setTitle("Snake");
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.pack();
	}
}
