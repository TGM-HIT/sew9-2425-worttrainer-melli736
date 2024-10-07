package worttrainer.controller;

import worttrainer.model.SpellingTrainer;
import worttrainer.model.JsonPersistence;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public SpellingTrainerController(SpellingTrainer trainer, JsonPersistence persistence, JTextField inputField,
                                     JLabel imageLabel, JLabel progressLabel, JLabel statsLabel, JButton nextButton) {
        this.trainer = trainer;
        this.persistence = persistence;
        this.inputField = inputField;
        this.imageLabel = imageLabel;
        this.progressLabel = progressLabel;
        this.statsLabel = statsLabel;
        this.nextButton = nextButton;

        // Select the first word-image pair right away to avoid IllegalStateException
        trainer.selectRandomPair();

        nextButton.addActionListener(new NextButtonListener());
        updateView(); // Update view with the initial word-image pair
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
            JOptionPane.showMessageDialog(null, "Correct!");
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect! The word was: " + trainer.getCurrentWord());
        }

        if (trainer.hasRemainingPairs()) {
            trainer.selectRandomPair();
            updateView();
        } else {
            showFinalStats();
        }
    }

    private void updateView() {
        imageLabel.setIcon(new ImageIcon(trainer.getCurrentImage()));
        progressLabel.setText("Progress: " + trainer.getTotalAttempts() + "/" + trainer.getWordImagePairs().size());
        statsLabel.setText("Correct: " + trainer.getCorrectAttempts() + " Incorrect: " + trainer.getIncorrectAttempts());
        inputField.setText("");
    }

    private void showFinalStats() {
        JOptionPane.showMessageDialog(null, trainer.getStatistics());
        nextButton.setEnabled(false);  // Disable "Next" button after the game is finished
    }

    public void saveGame(String filePath) {
        persistence.saveTrainerState(trainer, filePath);
        JOptionPane.showMessageDialog(null, "Game saved!");
    }
}
