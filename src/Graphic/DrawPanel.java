package Graphic;

import java.awt.*;

import javax.swing.*;

public class DrawPanel extends JPanel {
//        public void paintComponent(Graphics graphics){
//            graphics.setColor(Color.blue);
//            graphics.fillRect(20, 50, 100, 100);
//        }


//    public void paintComponent(Graphics graphics) {
//
//        graphics.fillRect(0,0,this.getWidth(),this.getHeight());
//
//        int red= (int) (Math.random()*255);
//        int green= (int) (Math.random()*255);
//        int blue= (int) (Math.random()*255);
//
//        Color randColor = new Color(red, green, blue);
//        graphics.setColor(randColor);
//        graphics.fillOval(70,70,100,50);
//
//    }


        public void paintComponent (Graphics g){
            Graphics2D g2d = (Graphics2D) g;
            GradientPaint gradient = new GradientPaint(70, 70, Color.RED,
                    150, 150, Color.CYAN);
            g2d.setPaint(gradient);
            g2d.fillOval(70,70 ,100,100);

        }

}
