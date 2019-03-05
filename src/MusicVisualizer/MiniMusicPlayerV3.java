package MusicVisualizer;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;

public class MiniMusicPlayerV3 implements ControllerEventListener {

    static JFrame frame = new JFrame("Music Visualizer");
    static MyDrawPanel ml;

    public void setUpGui(){
        ml = new MyDrawPanel();
        frame.setContentPane(ml);
        frame.setBounds(30,30,300,300);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        MiniMusicPlayerV3 mini = new MiniMusicPlayerV3();
        mini.go();
    }

    public void go() {

        setUpGui();

        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.addControllerEventListener(ml, new int[] {127});
            Sequence seq = new Sequence(Sequence.PPQ,4);
            Track track = seq.createTrack();

            int r= 0;

            for (int i = 0; i < 60; i+=4) {
                r= (int)((Math.random()*50)+1);

                track.add(Util.makeEvent(144, 1, r, 100, i));
                track.add(Util.makeEvent(176, 1, 127, 0, i));
                track.add(Util.makeEvent(128, 1, r, 100, i + 2));
            }

            sequencer.setSequence(seq);
            sequencer.start();
            sequencer.setTempoInBPM(220);

        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void controlChange(ShortMessage event) {
        System.out.println("Ля");
    }




     class MyDrawPanel extends JPanel implements ControllerEventListener {

        boolean msg = false;

        public void paintComponent(Graphics graphics) {
            if (msg) {
                Graphics2D g2 = (Graphics2D) graphics;

                int r = (int) (Math.random() * 250);
                int g = (int) (Math.random() * 250);
                int b = (int) (Math.random() * 250);
                graphics.setColor(new Color(r, g, b));

                int height = (int) ((Math.random() * 120) + 10);
                int width = (int) ((Math.random() * 120) + 10);
                int x = (int) ((Math.random() * 200) + 10);
                int y = (int) ((Math.random() * 200) + 10);

                graphics.fillRect(x, y, height, width);
                msg = false;

            }
        }

        @Override
        public void controlChange(ShortMessage event) {
            msg = true;
            repaint();
        }
    }
}
