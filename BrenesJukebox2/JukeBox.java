package BrenesJukebox2;
/**
 * JukeBox.java -- example from Listing 6.13 of Lewis et al, 4th Ed.
 * A. Thall
 * CSC 121 W18
 * 
 * Example using AudioClips with local files and JComboBox objects
 */

import javax.swing.*;

/**
 * Skeleton Code by thall
 *
 * @author Tony Brenes
 */
public class JukeBox {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		JFrame frame = new JFrame("Java Jukebox 2.0");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new JukeBoxControls());
		
		frame.pack();
		frame.setVisible(true);
	}
}
