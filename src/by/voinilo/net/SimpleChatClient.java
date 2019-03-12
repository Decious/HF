package net;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SimpleChatClient {
    private JTextArea incoming;
    private JTextField outgoing;
    private PrintWriter writer;
    private BufferedReader reader;
     private String name;


    private void setName(String name1) {
        name = name1;
    }

    public void go() {

        JFrame frame = new JFrame("Very Simple Chat Client");
        JPanel mainPanel = new JPanel();
        incoming = new JTextArea(15, 30);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(false);
        JScrollPane qScroller = new JScrollPane(incoming);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        outgoing = new JTextField(25);
        JButton sendButton = new JButton("SendIt");
        sendButton.addActionListener(new SendButtonListener());
        mainPanel.add(qScroller);
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);
        setUpNetWorking();
        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();

        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(390, 320);
        frame.setVisible(true);
    }

    private void setUpNetWorking() {
        try {
            Socket socket = new Socket("127.0.0.1", 5000);
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(isr);

            writer = new PrintWriter(socket.getOutputStream());
            System.out.println("networking established!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class SendButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                writer.println(outgoing.getText());
                writer.flush();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            outgoing.setText("");
            outgoing.requestFocus();
        }
    }

    public static void main(String[] args) {
        SimpleChatClient client = new SimpleChatClient();
        System.out.println("Pickup the nickname");
        Scanner scanner = new Scanner(System.in);

        client.setName(scanner.nextLine());
        client.go();
    }

    private class IncomingReader implements Runnable {
        @Override
        public void run() {
            String msg;
            try {
                while ((msg = reader.readLine()) != null) {
                    System.out.println("read " + msg);
                    incoming.append(name+": "+msg + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
