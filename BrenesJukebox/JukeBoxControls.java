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

import java.util.Random;
import java.util.Scanner;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

/**
 * Mini Jukebox Application. Has the Ability to pause, play, and rewind songs; displays album covers;
 * and display audio visualizer
 * @author thall
 *
 */

public class JukeBoxControls extends JPanel {

	private JComboBox<String> musicCombo;
	private JButton stopButton, playButton, rewindButton, lyricsButton;
	private File[] musicFile;
	private File current;
	private AudioInputStream audioStream;
	private Clip audioClip;
	private Image image;
	private VisualizerPanel visualizer;
	private File lyricsFile;

    private static String localDir = "C:/Users/adbre/IdeaProjects/CSC-230/BrenesJukebox/";

	/**
	 * Default Constructor for Jukebox controls. Initializes audio files, buttons, and main window
	 */
	public JukeBoxControls() {

		visualizer = new VisualizerPanel();
		add(visualizer);

		File f1, f2, f3, f4, f5;
		f1 = f2 = f3 = f4 = f5 = null;
		// get the audio clips if we can!
		
		try {
			f1 = new File(localDir + "Media/Graves Into Gardens.wav");
			f2 = new File(localDir + "Media/Separate Ways.wav");
			f3 = new File(localDir + "Media/Love Like This.wav");
			f4 = new File(localDir + "Media/Ripple.wav");
			f5 = new File(localDir + "Media/If This Is It.wav");
		} catch (Exception e) {
			System.err.println("Houston, we have a problem.");
		}
		musicFile = new File [] { null, f1, f2, f3, f4, f5};

		String[] musicNames = { "Pick some jams!", "Graves Into Gardens - Elevation Worship", "Separate Ways - Journey",
				"Love Like This - Ben Rector",
				"Ripple - Spirited", "If This Is It - Huey Lewis & The News" };

		musicCombo = new JComboBox<String>(musicNames);
		musicCombo.setBackground(Color.CYAN);

		// Need to use getResource method to load image into Icon
		ImageIcon playIcon = new ImageIcon(getClass().getResource("play25x25.png"));
		ImageIcon stopIcon = new ImageIcon(getClass().getResource("pause.png"));
		ImageIcon rewindIcon = new ImageIcon(getClass().getResource("rewindImage.png"));
		playButton = new JButton(null, playIcon);
		playButton.setBackground(Color.CYAN);
		stopButton = new JButton(null, stopIcon);
		stopButton.setBackground(Color.CYAN);
		rewindButton = new JButton(null, rewindIcon);
		rewindButton.setBackground(Color.CYAN);

		lyricsButton = new JButton("LYRICS");
		lyricsButton.setBackground(Color.CYAN);

		setPreferredSize(new Dimension(300, 200));
		setBackground(Color.CYAN);
		add(musicCombo);
		add(rewindButton);
		add(playButton);
		add(stopButton);
		add(lyricsButton);

		musicCombo.addActionListener(new ComboListener());
		stopButton.addActionListener(event -> {if (current != null){
			audioClip.stop();
			visualizer.stop();
		}});
		playButton.addActionListener(event -> {if (current != null){
			audioClip.start();
			visualizer.start();
		}});
		rewindButton.addActionListener(event -> {if (current != null) audioClip.setFramePosition(0);});

		lyricsButton.addActionListener(event -> {
			if (current != null) {

				String selectedSong = (String) musicCombo.getSelectedItem();
				if (selectedSong != null && !selectedSong.equals("Pick some jams!")) {
					String lyrics = getLyrics(selectedSong.split(" - ")[0]);

					JFrame lyricsFrame = new JFrame("Lyrics - " + selectedSong);
					JTextArea lyricsArea = new JTextArea(lyrics);
					lyricsArea.setEditable(false);
					lyricsArea.setLineWrap(true);
					lyricsArea.setWrapStyleWord(true);

					lyricsFrame.add(new JScrollPane(lyricsArea));
					lyricsFrame.setSize(400, 300);
					lyricsFrame.setLocationRelativeTo(null);
					lyricsFrame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(this, "Please select a song first!", "No Song Selected", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Please select a song first!", "No Song Selected", JOptionPane.WARNING_MESSAGE);
			}
		});



		current = null;
	}



	/**
	 * Action Listener Class for Combo Selector to choose what song you want to play.
	 * Handles the audio playing.
	 */
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

					switch (musicCombo.getSelectedIndex()) {
						case 1:
							image = new ImageIcon(getClass(). getResource("Covers/gravesIntoGardensCover.png")).getImage();
							lyricsFile = new File(localDir + "Lyrics/Graves Into Gardens.txt");
							break;
						case 2:
							image = new ImageIcon(getClass(). getResource("Covers/SeparateWaysCover.png")).getImage();
							lyricsFile = new File(localDir + "Lyrics/Separate Ways.txt");
							break;
						case 3:
							image = new ImageIcon(getClass(). getResource("Covers/LoveLikeThisCover.png")).getImage();
							lyricsFile = new File(localDir + "Lyrics/Love Like This.txt");
							break;
						case 4:
							image = new ImageIcon(getClass(). getResource("Covers/SpiritedCover.png")).getImage();
							lyricsFile = new File(localDir + "Lyrics/Ripple.txt");
							break;
						case 5:
							image = new ImageIcon(getClass(). getResource("Covers/sportsCover.png")).getImage();
							lyricsFile = new File(localDir + "Lyrics/If This Is It.txt");
							break;
						default:
							image = null;
							break;
					}
					repaint();
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

	/**
	 * Override paintComponent class to draw the album covers on the Jukebox
	 * @param g the <code>Graphics</code> object to protect
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 125, 140, this);

	}

	/***
	 * JPanel used to draw the random shapes as an audio visualizer for the main Jukebox Application
	 */
	class VisualizerPanel extends JPanel {
		private Random random = new Random();
		private boolean active = false;

		/**
		 * Sets size and color of frame to overlay on main JPanel
		 */
		public VisualizerPanel() {
			setPreferredSize(new Dimension(300, 50));
			setBackground(Color.WHITE);
		}

		/**
		 * Starts the visualizer, changes drawing every half second to avoid cpu overload
		 */
		public void start() {
			active = true;
			new Thread(() -> {
				while (active) {
					repaint();
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}

		/**
		 * stops the visualizer
		 */
		public void stop() {
			active = false;
		}

		/**
		 * Draws random amount of random colored random shapes.
		 * @param g the <code>Graphics</code> object to protect
		 */
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (!active) return;

			Graphics2D g2d = (Graphics2D) g;
			int shapeCount = random.nextInt(5) + 5;

			for (int i = 0; i < shapeCount; i++) {
				g2d.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
				int x = random.nextInt(getWidth());
				int y = random.nextInt(getHeight());
				int w = random.nextInt(20) + 20;
				int h = random.nextInt(20) + 20;

				switch(random.nextInt(3)){
					case 0 -> g2d.fillRect(x, y, w, h);
					case 1 -> g2d.fillOval(x, y, w, h);
					case 2 -> g2d.fillRoundRect(x, y, w, h, 10, 10);
				}
			}
		}
	}

	/**
	 * Creates a String of the song lyrics pulled from the selected file for the selected song.
	 * @param songName used to choose which song to chose to open file and pull lyrics from
	 * @return String of the song lyrics, formatted the same as the file it was pulled from
	 */
	private String getLyrics(String songName){
		StringBuilder lyrics = new StringBuilder();
		try{
			if(lyricsFile.exists()){
				Scanner scanner = new Scanner(lyricsFile);
				while (scanner.hasNextLine()){
					lyrics.append(scanner.nextLine()).append("\n");
				}
				scanner.close();
			} else{
				lyrics.append("Lyrics Not Available");
			}
		} catch (IOException e){
			lyrics.append("Error reading Lyrics:").append(e.getMessage());
		}
		return lyrics.toString();
	}
}
