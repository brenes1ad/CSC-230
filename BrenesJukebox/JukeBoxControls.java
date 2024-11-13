package BrenesJukebox;

/**
 * JukeBoxControls.java -- example from Listing 6.13 of Lewis et al, 4th Ed.
 * A. Thall
 * CSC 121 W18
 * 
 * Example using AudioClips with local files and JComboBox objects
 */

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

/**
 * @author thall
 *
 */

public class JukeBoxControls extends JPanel {

	private JComboBox<String> musicCombo;
	private JButton stopButton, playButton;
	private File[] musicFile;
	private File current;
	private AudioInputStream audioStream;
	private Clip audioClip;

    private static String localDir = "C:/Users/adbre/IdeaProjects/CSC-230/BrenesJukebox/";

	public JukeBoxControls() {

		File f1, f2, f3, f4, f5;
		f1 = f2 = f3 = f4 = f5 = null;
		// get the audio clips if we can!
		
		try {
			f1 = new File(localDir + "classical.wav");
			f2 = new File(localDir + "eightiesJam.wav");
			f3 = new File(localDir + "hitchcock.wav");
			f4 = new File(localDir + "newAgeRythm.wav");
			f5 = new File(localDir + "westernBeat.wav");
		} catch (Exception e) {
			System.err.println("Houston, we have a problem.");
		}
		musicFile = new File [] { null, f1, f2, f3, f4, f5};

		String[] musicNames = { "Pick some jams!", "Classical Melody", "80s Jam", "Alfred Hitchcock Theme",
				"New Age Rhythm", "Western Beat" };

		musicCombo = new JComboBox<String>(musicNames);
		musicCombo.setBackground(Color.CYAN);

		// Need to use getResource method to load image into Icon
		ImageIcon playIcon = new ImageIcon(getClass().getResource("play25x25.png"));
		ImageIcon stopIcon = new ImageIcon(getClass().getResource("pause.png"));
		playButton = new JButton(null, playIcon);
		playButton.setBackground(Color.CYAN);
		stopButton = new JButton(null, stopIcon);
		stopButton.setBackground(Color.CYAN);

		setPreferredSize(new Dimension(300, 100));
		setBackground(Color.CYAN);
		add(musicCombo);
		add(playButton);
		add(stopButton);

		musicCombo.addActionListener(new ComboListener());
		stopButton.addActionListener(event -> {if (current != null) audioClip.stop();});
		playButton.addActionListener(event -> {if (current != null) audioClip.start();});

		current = null;
	}
	
	private class ComboListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			try {
				if (current != null) {
					audioClip.close();
					audioStream.close();
				}
				current = musicFile[musicCombo.getSelectedIndex()];
				if (current != null) {
					audioStream = AudioSystem.getAudioInputStream(current);
					AudioFormat format = audioStream.getFormat();
					DataLine.Info info = new DataLine.Info(Clip.class, format);
					audioClip = (Clip) AudioSystem.getLine(info);
					audioClip.open(audioStream);
				}
			} catch (IOException exception) {
				System.err.println(exception);
			} catch (UnsupportedAudioFileException exception) {
				System.err.println(exception);
			} catch (LineUnavailableException exception) {
				System.err.println(exception);
			}
		}
	}
}
