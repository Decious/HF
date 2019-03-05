package MusicStruff;

import MusicVisualizer.Util;

import javax.sound.midi.*;

public class MiniMusicPlayerV2 implements ControllerEventListener {

    public static void main(String[] args) {
        MiniMusicPlayerV2 mini = new MiniMusicPlayerV2();
        mini.go();
    }

    private void go() {
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();

            int[] eventsIWant = {127};
            sequencer.addControllerEventListener(this, eventsIWant);
            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();

            for (int i = 5; i < 61; i++) {
                track.add(Util.makeEvent(144, 1, i, 100, i));
                track.add(Util.makeEvent(176, 1, 127, 0, i));
                track.add(Util.makeEvent(128, 1, i, 100, i + 2));
            }

            sequencer.setSequence(seq);
            sequencer.setTempoInBPM(220);
            sequencer.start();
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void controlChange(ShortMessage event) {
        System.out.println("Ля");
    }
}
