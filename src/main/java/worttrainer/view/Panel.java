package worttrainer.view;

import worttrainer.controller.SpellingTrainerController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Panel class that handles the graphical interface of the SpellingTrainer application.
 * It provides input fields, buttons, and statistics for the user, as well as displaying
 * the image corresponding to the current word in the spelling trainer.
 * @version 2024-10-08
 */
public class Panel extends JPanel {

    private final SpellingTrainerController controller;
    private JLabel correct, total, picture;
    private JButton save, load;
    private JTextField input;
    private String url;

    /**
     * Constructor that sets up the panel with the required components, such as input fields,
     * buttons, and labels for displaying the current image and statistics.
     * @param controller the controller that handles events and updates the view.
     * @throws MalformedURLException if the provided URL for the image is invalid.
     */
    public Panel(SpellingTrainerController controller) throws MalformedURLException {
        this.setLayout(new BorderLayout()); // Setting the general layout.
        this.controller = controller;
        this.url = controller.getUrl();

        // TOP
        JPanel top = new JPanel();
        top.setLayout(new GridLayout(1, 1));
        JLabel question = new JLabel("Try to spell the word displayed below:");
        question.setHorizontalAlignment(JLabel.CENTER);
        top.add(question);
        this.add(top, BorderLayout.PAGE_START);

        // Image - CENTER
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(1, 1));
        ImageIcon icon = new ImageIcon(new URL(this.url));
        Image image = icon.getImage();
        image = image.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        this.picture = new JLabel(new ImageIcon(image));
        center.add(picture);
        this.add(center, BorderLayout.CENTER);

        // Text input, Buttons & Stats - BOTTOM
        // General
        JPanel bottom = new JPanel();
        bottom.setLayout(new GridLayout(3, 1));
        bottom.setBorder(new EmptyBorder(0, 20, 20, 20));

        // Text input
        this.input = new JTextField();
        bottom.add(input);

        // Buttons
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1, 2));
        this.save = new JButton("Save");
        this.load = new JButton("Load");
        buttons.add(save);
        buttons.add(load);
        bottom.add(buttons);

        // Stats: shows how many answers are correct and total attempts
        JPanel stats = new JPanel();
        stats.setLayout(new GridLayout(2, 2));
        JLabel textC = new JLabel("Correct:");
        JLabel textT = new JLabel("Total:");
        this.correct = new JLabel(String.valueOf(this.controller.getCorrect()));
        this.total = new JLabel(String.valueOf(this.controller.getTotal()));
        stats.add(textC);
        stats.add(textT);
        stats.add(correct);
        stats.add(total);
        bottom.add(stats);

        this.add(bottom, BorderLayout.PAGE_END);

        // ActionListener
        this.input.addActionListener(this.controller);
        this.input.setActionCommand("input");

        this.save.addActionListener(this.controller);
        this.save.setActionCommand("save");

        this.load.addActionListener(this.controller);
        this.load.setActionCommand("load");
    }

    /**
     * Retrieves the text entered by the user in the input field.
     * @return the text entered by the user.
     */
    public String getInput() {
        return this.input.getText();
    }

    /**
     * Updates the panel to display the next word's image and resets input and statistics.
     * @param url the URL of the image for the next word to be displayed.
     */
    public void nextWord(String url) {
        this.input.setText(""); // Clear the text field.
        this.correct.setText(String.valueOf(this.controller.getCorrect())); // Update statistics.
        this.total.setText(String.valueOf(this.controller.getTotal()));
        this.url = url;
        try {
            reloadImage();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reloads the image for the next word to be displayed in the panel.
     * @throws MalformedURLException if the provided URL for the image is invalid.
     */
    private void reloadImage() throws MalformedURLException {
        JPanel center = new JPanel();
        ImageIcon icon = new ImageIcon(new URL(this.url));
        Image image = icon.getImage();
        image = image.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        this.picture = new JLabel(new ImageIcon(image));
        center.add(picture);
        this.add(center, BorderLayout.CENTER);
    }
}
