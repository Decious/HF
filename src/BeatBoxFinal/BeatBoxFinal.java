package BeatBoxFinal;

import javax.sound.midi.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.*;

import static javax.xml.bind.DatatypeConverter.parseInt;

public class BeatBoxFinal {
    static String [] argz;
    private JFrame theFrame;
    private JList incomingList;
    private JTextField userMessage;
    private ArrayList<JCheckBox> checkboxList;
    private int nextNum;
    private Vector<String> listVector = new Vector<>();
    private String userName;
    private ObjectOutputStream out;
    ObjectInputStream in;
    private HashMap<String, boolean[]> otherSeqsMap = new HashMap<>();
    private Sequencer sequencer;
    private Sequence sequence;
    private Sequence mySequence = null;
    private Track track = null;
    static String ip;
    private String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat",
            "Acoustic Snare", "Crash Cymbal", "Hand Clap", "High Tom", "Hi Bongo",
            "Maracas", "Whistle", "Low Conga", "CowBell", "Vibraslap", "Low-mid Tom",
            "High Agogo", "Open Hi Conga"};
    private int[] instruments = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63};

    public static void main(String[] args) {
        System.out.println("enter name");
        Scanner scanner =new Scanner (System.in);
        String name = scanner.nextLine();
        System.out.println("enter ip");
         ip = scanner.nextLine();
         argz = ip.split(":");

        new BeatBoxFinal().startUp(name);
    }

    private void startUp(String name) {
        userName = name;

        try {
            Socket socket = new Socket(argz[0], 4242);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            Thread remote = new Thread(new RemoteReader());
            remote.start();

        } catch (IOException e) {
            System.out.println("could not connect");
            e.printStackTrace();
        }
        setUpMidi();
        buildGui();
    }

    private void buildGui() {
        theFrame = new JFrame("Cyber BeatB0x");
        theFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        JPanel background = new JPanel(layout);
        background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        checkboxList = new ArrayList<>();

        Box buttonBox = new Box(BoxLayout.Y_AXIS);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem loadMenuItem = new JMenuItem("Load");

        saveMenuItem.addActionListener(new SaveListener());
        loadMenuItem.addActionListener(new LoadListener());

        fileMenu.add(saveMenuItem);
        fileMenu.add(loadMenuItem);
        menuBar.add(fileMenu);
        theFrame.setJMenuBar(menuBar);

        JButton start = new JButton(" START ►");
        start.addActionListener(new MyStartListener());
        buttonBox.add(start);

        JButton stop = new JButton(" STOP ||");
        stop.addActionListener(new MyStopListener());
        buttonBox.add(stop);

        JButton upTempo = new JButton("▲Tempo▲");
        upTempo.addActionListener(new MyUpTempoListener());
        buttonBox.add(upTempo);

        JButton downTempo = new JButton("▼TEMPO▼");
        downTempo.addActionListener(new MyDownTempoListener());
        buttonBox.add(downTempo);

        JButton send = new JButton("Send");
        send.addActionListener(new MySendListener());
        buttonBox.add(send);

        userMessage = new JTextField();
        buttonBox.add(userMessage);

        incomingList = new JList();
        incomingList.addListSelectionListener(new MyListSelectionLister());
        incomingList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane theList = new JScrollPane(incomingList);
        buttonBox.add(theList);
        incomingList.setListData(listVector);
        Box nameBox = new Box(BoxLayout.Y_AXIS);
        for (int i = 0; i < 16; i++) {
            nameBox.add(new Label(instrumentNames[i]));
        }

        background.add(BorderLayout.EAST, buttonBox);
        background.add(BorderLayout.WEST, nameBox);

        theFrame.getContentPane().add(background);

        GridLayout grid = new GridLayout(16, 16);
        grid.setVgap(1);
        grid.setHgap(2);
        JPanel mainPanel = new JPanel(grid);
        background.add(BorderLayout.CENTER, mainPanel);
        for (int i = 0; i < 256; i++) {
            JCheckBox c = new JCheckBox();
            c.setSelected(false);
            checkboxList.add(c);
            mainPanel.add(c);
        }
        theFrame.setBounds(50, 50, 300, 300);
        theFrame.pack();
        theFrame.setVisible(true);
    }


    private void setUpMidi() {
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ, 4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(120);
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }


    }

    private void buildTrackAndStart() {
        ArrayList<Integer> trackList;
        sequence.deleteTrack(track);
        track = sequence.createTrack();

        for (int i = 0; i < 16; i++) {
            trackList = new ArrayList<>();

            for (int j = 0; j < 16; j++) {
                JCheckBox jc = checkboxList.get(j + (16 * i));
                if (jc.isSelected()) {
                    int key = instruments[i];
                    trackList.add(key);
                } else {
                    trackList.add(null);
                }
            }

            makeTracks(trackList);

        }

        track.add(Util.makeEvent(192, 9, 1, 0, 15));

        try {
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
            sequencer.start();
            sequencer.setTempoInBPM(120);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }

    }


    public class RemoteReader implements Runnable {
        boolean[] checkboxState = null;
        String nameToShow = null;
        Object obj = null;
        @Override
        public void run() {
            try {
                while ((obj = in.readObject()) != null) {
                    System.out.println("got an object from server");
                    System.out.println(obj.getClass());
                    String nameToShow = (String) obj;
                    checkboxState = (boolean[]) in.readObject();
                    otherSeqsMap.put(nameToShow, checkboxState);
                    listVector.add(nameToShow);
                    incomingList.setListData(listVector);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }


    }
    public class MyPlayMineListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (mySequence != null) {
                sequence = mySequence;
            }
        }

    }

    private class MyStopListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sequencer.stop();
        }
    }

    private class MyUpTempoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor((float) (tempoFactor * 1.03));
        }
    }


    private class MyDownTempoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor((float) (tempoFactor * 0.97));

        }
    }

    public class LoadListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(theFrame);
            loadFile(fileChooser.getSelectedFile());
        }
    }

    private void loadFile(File file) {
        boolean[] checkboxState = null;
        try {
            FileInputStream fileIn = new FileInputStream(new File(String.valueOf(file)));
            ObjectInputStream is = new ObjectInputStream(fileIn);
            checkboxState = (boolean[]) is.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 256; i++) {
            JCheckBox check = checkboxList.get(i);
            if (checkboxState[i]) {
                check.setSelected(true);
            } else {
                check.setSelected(false);
            }
        }
        sequencer.stop();
        buildTrackAndStart();
    }

    private class SaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(theFrame);
            saveFile(fileChooser.getSelectedFile());
        }
    }

    private void saveFile(File selectedFile) {
        boolean[] checkboxState = new boolean[256];

        for (int i = 0; i < 256; i++) {
            JCheckBox check = checkboxList.get(i);
            if (check.isSelected()) {
                checkboxState[i] = true;
            }
        }

        try {
            FileOutputStream fileStream = new FileOutputStream(new File(String.valueOf(selectedFile)));
            ObjectOutputStream os = new ObjectOutputStream(fileStream);
            os.writeObject(checkboxState);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private class MySendListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean[] checkboxState = new boolean[256];
            for (int i = 0; i < 256; i++) {
                JCheckBox check = (JCheckBox) checkboxList.get(i);
                if (check.isSelected()) {
                    checkboxState[i] = true;
                }
            }

            String messageToSend = null;
            try {
                out.writeObject(userName + nextNum++ + ": " + userMessage.getText());
                out.writeObject(checkboxState);
            } catch (IOException e1) {
                System.out.println("could not send msg to the server");
                e1.printStackTrace();
            }
            userMessage.setText("");
        }
    }


    private class MyListSelectionLister implements javax.swing.event.ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                String selected = (String) incomingList.getSelectedValue();
                if (selected != null) {
                    boolean[] selectedState = (boolean[]) otherSeqsMap.get(selected);
                    changeSequence(selectedState);
                    sequencer.stop();
                    buildTrackAndStart();
                }
            }
        }
    }


    private void changeSequence(boolean[] selectedState) {
        for (int i = 0; i < 256; i++) {
            JCheckBox check = (JCheckBox) checkboxList.get(i);
            if (selectedState[i]) {
                check.setSelected(true);
            } else {
                check.setSelected(false);
            }
        }
    }

    public void makeTracks(ArrayList list) {
        Iterator it = list.iterator();
        for (int i = 0; i < 16; i++) {
            Integer num = (Integer) it.next();
            if (num != null) {
                int numKey = num.intValue();
                track.add(Util.makeEvent(144, 9, numKey, 100, i));
                track.add(Util.makeEvent(128, 9, numKey, 100, i + 1));
            }
        }
    }

    private class MyStartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            buildTrackAndStart();
        }
    }
}
