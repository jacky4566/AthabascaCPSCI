package game;

import javax.swing.JFrame;

/*
 * Jackson Wiebe
 * 3519635
 * 06/01/2024
 * 
 * Main Class to start game
 * 
 */

public class Pacman extends JFrame{

	public Pacman() {
		add(new Model());
	}
	
	
	public static void main(String[] args) {
		Pacman pac = new Pacman();
		pac.setVisible(true);
		pac.setTitle("Pacman");
		pac.setSize(380,420);
		pac.setDefaultCloseOperation(EXIT_ON_CLOSE);
		pac.setLocationRelativeTo(null);
		
	}

}
