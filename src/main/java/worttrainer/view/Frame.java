package worttrainer.view;

import javax.swing.*;

/**
 * Frame class for displaying the main window of the SpellingTrainer application.
 * It sets the panel to be displayed and defines the basic window properties.
 * The window will be sized to 500x500 pixels and centered on the screen.
 * The default close operation is set to exit the application when the window is closed.
 * @version 2024-10-08
 */
public class Frame extends JFrame {

    /**
     * Constructor that initializes the main window with the given panel.
     * @param p the panel that will be displayed inside the frame.
     */
    public Frame(JPanel p) {
        super("SpellingTrainer Wallpach 5BHIT");
        this.add(p);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
