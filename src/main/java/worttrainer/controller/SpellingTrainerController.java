package worttrainer.controller;

import worttrainer.model.SpellingTrainer;
import worttrainer.model.JsonPersistence;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * The controller for the spelling trainer game, handling button actions and game progress.
 */
public class SpellingTrainerController {
    private final SpellingTrainer trainer;
    private final JsonPersistence persistence;
    private final JTextField inputField;
    private final JLabel imageLabel;
    private final JLabel progressLabel;
    private final JLabel statsLabel;
    private final JButton nextButton;
    private final JFrame view;

    public SpellingTrainerController(SpellingTrainer trainer, JsonPersistence persistence, JTextField inputField,
                                     JLabel imageLabel, JLabel progressLabel, JLabel statsLabel, JButton nextButton, JFrame view) {
        this.trainer = trainer;
        this.persistence = persistence;
        this.inputField = inputField;
        this.imageLabel = imageLabel;
        this.progressLabel = progressLabel;
        this.statsLabel = statsLabel;
        this.nextButton = nextButton;
        this.view = view;

        trainer.selectRandomPair();
        nextButton.addActionListener(new NextButtonListener());
        updateView();
    }

    private class NextButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleNextButton();
        }
    }

    private void handleNextButton() {
        String userInput = inputField.getText().trim();
        boolean correct = trainer.spellingCheck(userInput);

        if (correct) {
            JOptionPane.showMessageDialog(view, "Correct!");
        } else {
            JOptionPane.showMessageDialog(view, "Incorrect! The word was: " + trainer.getCurrentWord());
        }

        if (trainer.hasRemainingPairs()) {
            trainer.selectRandomPair();
            updateView();
        } else {
            showFinalStats();
        }
    }

    private void updateView() {
        SwingUtilities.invokeLater(() -> {
            try {
                String imageUrl = trainer.getCurrentImage();
                BufferedImage image = ImageIO.read(new URL(imageUrl));
                imageLabel.setIcon(new ImageIcon(image));
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(view, "Error loading image: " + e.getMessage());
            }

            progressLabel.setText("Progress: " + trainer.getTotalAttempts() + "/" + trainer.getWordImagePairs().size());
            statsLabel.setText("Correct: " + trainer.getCorrectAttempts() + " Incorrect: " + trainer.getIncorrectAttempts());
            inputField.setText("");
        });
    }

    private void showFinalStats() {
        String statistics = trainer.getStatistics();
        Object[] options = {"Quit", "Restart"};
        int choice = JOptionPane.showOptionDialog(view, statistics, "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == JOptionPane.YES_OPTION) {
            saveGameAndExit();
        } else if (choice == JOptionPane.NO_OPTION) {
            restartGame();
        }
    }

    private void saveGameAndExit() {
        // Hier kann der Speicherpfad festgelegt werden oder eine Dialogaufforderung zur Eingabe des Pfads hinzugefügt werden
        String filePath = "saved_game.json"; // Beispielpfad
        saveGame(filePath);
        System.exit(0);
    }

    private void restartGame() {
        // Trainer zurücksetzen und ein neues Spiel starten
        trainer.resetTrainer();  // Eine Methode zum Zurücksetzen des Trainers
        updateView();
        nextButton.setEnabled(true);  // Den "Next" Button wieder aktivieren
    }

    public void saveGame(String filePath) {
        persistence.saveTrainerState(trainer, "/Users/melli/Library/CloudStorage/OneDrive-tgm-DieSchulederTechnik/5BHIT/SEW/WorttrainerReloaded/resources/savedSpellingTrainer.json");
        JOptionPane.showMessageDialog(view, "Game saved!");
    }
}
