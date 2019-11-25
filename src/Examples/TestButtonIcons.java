package Examples;

import javax.swing.*;

public class TestButtonIcons extends JFrame {
    public static void main(String[] args) {
        // Create a frame and set its properties
        JFrame frame = new TestButtonIcons();
        frame.setTitle("ButtonIcons");
        frame.setSize(200, 100);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public TestButtonIcons() {
        ImageIcon ukIcon = new ImageIcon("image/uk5.gif");
        ImageIcon grIcon = new ImageIcon("image/gr.png");
        ImageIcon frIcon = new ImageIcon("image/fr.gif");

        JButton jbt = new JButton("Click it", ukIcon);
        jbt.setPressedIcon(grIcon);
        jbt.setRolloverIcon(frIcon);

        add(jbt);
    }
}
