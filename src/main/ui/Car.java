package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

// represents a picture of a car in main panel
public class Car extends JPanel {

    public Car() {
        setPreferredSize(new Dimension(100,100));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(430, 0, 100, 50);
        g.setColor(Color.BLACK);
        g.fillOval(510, 35, 25, 25);
        g.fillOval(425, 35, 25, 25);
        g.setColor(Color.RED);
        g.fillRect(220, 0, 100, 50);
        g.setColor(Color.BLACK);
        g.fillOval(300, 35, 25, 25);
        g.fillOval(215, 35, 25, 25);
    }
}
