package A3_GAME;

import Examples.HandleEvent;

import javax.swing.*;
import java.awt.event.*;

public class Gameover extends JFrame implements ActionListener {
    // Create two buttons
    JButton jbtOK = new JButton("Play Again");

    public Gameover() {

        // Create a panel to hold buttons
        JPanel panel = new JPanel();
        panel.add(jbtOK);

        add(panel); // Add panel to the frame

        // Register listeners
        jbtOK.addActionListener(this);
    }

    public static void main(String[] args) {
        JFrame frame = new HandleEvent();
        frame.setTitle("Handle Event");
        frame.setSize(200, 150);
        frame.setLocation(200, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        if (b == jbtOK) {
            System.out.println("PLAY button clicked");
        }
    }
}
