package BrenesJukebox2;

/**
 * JukeBoxControls.java -- example from Listing 6.13 of Lewis et al, 4th Ed.
 * A. Thall
 * CSC 121 W18
 * 
 *
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import java.util.ArrayList;
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
 * and display audio visualizer. Added in 2.0 is the ability to choose one's own directory for media
 * and the ability to save to a config file so you won't need to re-input the new directory
 *
 * CSC 230 F24
 * 12/2
 *
 * Skeleton Code by A. Thall
 *
 * @author Tony Brenes
 *
 */

public class JukeBoxControls extends JPanel implements Serializable{

	private JComboBox<String> musicCombo;
	private JButton stopButton, playButton, rewindButton, chooserButton;
	private File[] musicFile;
	private File current;
	private AudioInputStream audioStream;
	private Clip audioClip;
	private Image image;
	private VisualizerPanel visualizer;
	private ArrayList<String> songList;
	private ArrayList<String> coverList;

	private JukeBoxConfig config;
	private static final String CONFIG_FILE = "JBconfig.ser";



    private static String localDir;

	/**
	 * Default Constructor for Jukebox controls. Initializes audio files, buttons, and main window
	 */
	public JukeBoxControls() {
		config = loadConfig();
		localDir = config.getMediaFolder();

		visualizer = new VisualizerPanel();
		add(visualizer);

		File f1, f2, f3, f4, f5;
		f1 = f2 = f3 = f4 = f5 = null;
		// get the audio clips if we can!

		songList = new ArrayList<String>();
		coverList = new ArrayList<String>();
		File[] files = new File(localDir).listFiles();
		for(File file: files){
			if(file.getName().endsWith(".wav")){
				songList.add(file.getName());
			}
			if(file.getName().endsWith(".png")){
				coverList.add(file.getName());
			}
		}
		
		try {
			f1 = new File(localDir + "/" + songList.get(0));
			f2 = new File(localDir + "/" + songList.get(1));
			f3 = new File(localDir + "/" + songList.get(2));
			f4 = new File(localDir + "/" + songList.get(3));
			f5 = new File(localDir + "/" + songList.get(4));
		} catch (Exception e) {
			System.err.println("Houston, we have a problem.");
		}
		musicFile = new File [] { null, f1, f2, f3, f4, f5};

		String[] musicNames = { "Pick some jams!", songList.get(0), songList.get(1), songList.get(2), songList.get(3),
		songList.get(4)};

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
		chooserButton = new JButton("File Chooser");
		chooserButton.setBackground(Color.CYAN);

		setPreferredSize(new Dimension(400, 250));
		setBackground(Color.CYAN);
		add(musicCombo);
		add(rewindButton);
		add(playButton);
		add(stopButton);
		add(chooserButton);

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

		chooserButton.addActionListener(event -> openFileChooserFrame());


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
							image = new ImageIcon((localDir + "/" + coverList.get(0))).getImage();
							break;
						case 2:
							image = new ImageIcon((localDir + "/" + coverList.get(1))).getImage();
							break;
						case 3:
							image = new ImageIcon((localDir + "/" + coverList.get(2))).getImage();
							break;
						case 4:
							image = new ImageIcon((localDir + "/" + coverList.get(3))).getImage();
							break;
						case 5:
							image = new ImageIcon((localDir + "/" + coverList.get(4))).getImage();
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
		g.drawImage(image, 150, 140, this);

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
			setPreferredSize(new Dimension(400, 50));
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
	 * Controls the actions of the fileChooser button. Opens a new frame with the directory chooser and saves
	 * the new config if a new directory is chosen.
	 */
	private void openFileChooserFrame() {

		JFrame chooserFrame = new JFrame("Select Directory");
		chooserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		chooserFrame.setSize(500, 400);


		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		fileChooser.addActionListener(e -> {
			if (e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
				File selectedFile = fileChooser.getSelectedFile();
				System.out.println("Selected Directory: " + selectedFile.getAbsolutePath());
				localDir = selectedFile.getAbsolutePath();
				System.out.println(localDir);
				saveConfig();
				chooserFrame.dispose();
			} else if (e.getActionCommand().equals(JFileChooser.CANCEL_SELECTION)) {
				chooserFrame.dispose();
			}
		});

		chooserFrame.add(fileChooser);
		chooserFrame.setVisible(true);
	}

	/**
	 * Used to attempt to load an existing config file(if one even exists) on project load up or creates a new one
	 * if one doesn't already exist.
	 * @return either the existing config object or creates a new config with the default media directory.
	 */
	private JukeBoxConfig loadConfig(){
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CONFIG_FILE))){
			return (JukeBoxConfig) ois.readObject();
		} catch (Exception e){
			return new JukeBoxConfig("C:/Users/adbre/IdeaProjects/CSC-230/BrenesJukebox2/Media");
		}
	}

	/**
	 * Used to save the config object to given file name. Config is saved whenever the user selects a new
	 * directory as the working directory for media.
	 */
	private void saveConfig(){
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CONFIG_FILE))){
			oos.writeObject(config);
			System.out.println("Saved Config");
		} catch(IOException e){
			System.err.println(e);
		}
	}
}
