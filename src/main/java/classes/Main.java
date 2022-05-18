package classes;

import javax.swing.*;

public class Main {
    Main() {
        JFrame frame = new JFrame("Редактор");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        //frame.setResizable(false);
        frame.add(new GlobalPanel(frame));
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
