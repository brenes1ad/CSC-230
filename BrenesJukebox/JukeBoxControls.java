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
	private JButton stopButton, playButton, rewindButton;
	private JRadioButton rectButton, ovalButton, roundRectButton;
	private File[] musicFile;
	private File current;
	private AudioInputStream audioStream;
	private Clip audioClip;
	private Image image;
	private VisualizerPanel visualizer;

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
			f1 = new File(localDir + "Graves Into Gardens.wav");
			f2 = new File(localDir + "Separate Ways.wav");
			f3 = new File(localDir + "Love Like This.wav");
			f4 = new File(localDir + "Ripple.wav");
			f5 = new File(localDir + "If This Is It.wav");
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




		setPreferredSize(new Dimension(300, 200));
		setBackground(Color.CYAN);
		add(musicCombo);
		add(rewindButton);
		add(playButton);
		add(stopButton);

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
							image = new ImageIcon(getClass(). getResource("gravesIntoGardensCover.png")).getImage();
							break;
						case 2:
							image = new ImageIcon(getClass(). getResource("SeparateWaysCover.png")).getImage();
							break;
						case 3:
							image = new ImageIcon(getClass(). getResource("LoveLikeThisCover.png")).getImage();
							break;
						case 4:
							image = new ImageIcon(getClass(). getResource("SpiritedCover.png")).getImage();
							break;
						case 5:
							image = new ImageIcon(getClass(). getResource("sportsCover.png")).getImage();
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

	class VisualizerPanel extends JPanel {
		private Random random = new Random();
		private boolean active = false;

		public VisualizerPanel() {
			setPreferredSize(new Dimension(300, 50));
			setBackground(Color.WHITE);
		}

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

		public void stop() {
			active = false;
		}

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
}
