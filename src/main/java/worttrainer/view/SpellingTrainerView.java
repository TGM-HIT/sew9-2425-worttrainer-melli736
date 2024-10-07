package worttrainer.view;

import worttrainer.controller.SpellingTrainerController;
import worttrainer.model.JsonPersistence;
import worttrainer.model.SpellingTrainer;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * The view for the spelling trainer game, displaying the GUI components.
 */
public class SpellingTrainerView extends JFrame {
    private final JTextField inputField;
    private final JLabel imageLabel;
    private final JLabel progressLabel;
    private final JLabel statsLabel;
    private final JButton nextButton;
    private final JButton saveButton;

    public SpellingTrainerView(SpellingTrainer trainer, JsonPersistence persistence) {
        setTitle("Spelling Trainer");
        setSize(800, 600); // Größe erhöhen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Zentriere das Fenster
        setLocationRelativeTo(null);

        // Create input field and buttons
        inputField = new JTextField(20);
        nextButton = new JButton("Next");
        saveButton = new JButton("Save");

        // Create labels
        imageLabel = new JLabel();
        progressLabel = new JLabel("Progress: 0/0");
        statsLabel = new JLabel("Correct: 0 Incorrect: 0");

        // Layout components
        JPanel centerPanel = new JPanel(new GridBagLayout()); // Verwende GridBagLayout für zentrierte Anordnung
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Zentriere das Bild
        centerPanel.add(imageLabel, gbc);

        JPanel southPanel = new JPanel();
        southPanel.add(inputField);
        southPanel.add(nextButton);
        southPanel.add(saveButton);

        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(statsLabel, BorderLayout.NORTH);
        northPanel.add(progressLabel, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
        add(northPanel, BorderLayout.NORTH);

        // Initialize controller
        SpellingTrainerController controller = new SpellingTrainerController(trainer, persistence, inputField, imageLabel, progressLabel, statsLabel, nextButton);

        saveButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                controller.saveGame(file.getAbsolutePath());
            }
        });
    }
}
