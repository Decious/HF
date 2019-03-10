package Graphic;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class SimpleGui implements ActionListener {
    private JButton button;
    private JFrame frame;

    public static void main(String[] args) {
        SimpleGui gui = new SimpleGui();
        gui.go();
    }

    private void go() {
        frame = new JFrame();

        button = new JButton("Click me!");
        button.setSize(50,10);
        button.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.CENTER, button);
        frame.setSize(300, 300);
        frame.setVisible(true);

    }


    public void actionPerformed(ActionEvent e) {
        button.setText("I've been clicked!");

    }

}
