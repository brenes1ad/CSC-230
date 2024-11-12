package BrenesMIDI;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static javax.sound.midi.ShortMessage.*;

/**
 * Mini MIDI music playing application. Plays random MIDI notes and draws a random rectangle for each note played
 */
public class MiniMusicPlayer {
    private MyDrawPanel panel;
    private Random random = new Random();

    /**
     * Main method to create instance of the player and call the primary action method, go().
     * @param args unused
     */
    public static void main(String[] args) {
        MiniMusicPlayer mini = new MiniMusicPlayer();
        mini.go();
    }

    /**
     * Simple class to set up graphic interface.
     * Creates frame and panel used for drawing
     */
    public void setUpGui(){
        JFrame frame = new JFrame("My First Music Video");
        panel = new MyDrawPanel();
        frame.setContentPane(panel);
        frame.setBounds(30, 30, 300, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Main running method for application.
     * Creates GUI and handles MIDI sequencer and note playing
     */
    public void go(){
        setUpGui();

        try{
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.addControllerEventListener(panel, new int[]{127});
            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();

            int note;
            for (int i = 0; i < 60; i += 4){
                note = random.nextInt(50) + 1;
                track.add(makeEvent(NOTE_ON, 1, note, 100, i));
                track.add(makeEvent(CONTROL_CHANGE, 1, 127, 0, i));
                track.add(makeEvent(NOTE_OFF, 1, note, 100, i + 2));
            }
            sequencer.setSequence(seq);
            sequencer.start();
            sequencer.setTempoInBPM(120);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * Creates a MIDI event with specified parameters
     * @param cmd command for the MIDI messages (ON, OFF, CHANGE)
     * @param chnl the channel the message is sent to
     * @param one the first data byte for the message
     * @param two the second data byte for the message
     * @param tick the timestamp for the event in tick speed
     * @return the created MidiEvent or null and throws exception if an exception occurs
     */
    public static MidiEvent makeEvent(int cmd, int chnl, int one, int two, int tick){
        MidiEvent event = null;
        try{
            ShortMessage msg = new ShortMessage();
            msg.setMessage(cmd, chnl, one, two);
            event = new MidiEvent(msg, tick);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return event;
    }

    /**
     * Class to handle drawing panel changes and updates
     */
    class MyDrawPanel extends JPanel implements ControllerEventListener{
        /** Used to act out changes iff the MIDI sequencer sends a message(plays a note) **/
        private boolean msg = false;

        /**
         *  Called when Sequencer sends a control-change event. Calls the repaint method to update the panel
         * @param event the control-change event that the sequencer encountered in
         *         the sequence it is processing
         */
        public void controlChange(ShortMessage event){
            msg = true;
            repaint();
        }

        /**
         * Simple method to draw a random rectangle iff the sequencer sends a message.
         * @param g the <code>Graphics</code> object to protect
         */
        public void paintComponent(Graphics g){
            if (msg){
                int r = random.nextInt(250);
                int gr = random.nextInt(250);
                int b = random.nextInt(250);

                g.setColor(new Color(r, gr, b));

                int height = random.nextInt(120) + 10;
                int width = random.nextInt(120) + 10;

                int xPos = random.nextInt(40) + 10;
                int yPos = random.nextInt(40) + 10;

                g.fillRect(xPos, yPos, width, height);
                msg = false;
            }
        }
    }
}
