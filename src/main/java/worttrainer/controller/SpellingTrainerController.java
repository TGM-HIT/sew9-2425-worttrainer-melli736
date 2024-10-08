package worttrainer.controller;

import worttrainer.model.SpellingTrainer;
import worttrainer.view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Controller-Class, that represents the connection between view and model.
 * It handles user inputs, view updates, and the coordination between model and view.
 * @author Melissa Wallpach
 * @version 2024-10-08
 */
public class SpellingTrainerController implements ActionListener {
    private final Frame f;
    private Panel panel;
    private SpellingTrainer trainer;

    /**
     * Constructor that initializes the trainer, panel, and frame.
     */
    public SpellingTrainerController() {
        this.trainer = new SpellingTrainer();
        try {
            this.panel = new Panel(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.f = new Frame(this.panel);
    }

    /**
     * This method is called whenever an action event is triggered in the view,
     * such as saving, loading, or checking the spelling input.
     * @param e the event to be processed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            case "input":
                if (this.trainer.spellingCheck(this.panel.getInput())) {
                    trainer.setCorrectAttempts(getCorrect());
                }
                trainer.setTotalAttempts(getTotal());
                if(trainer.hasRemainingPairs()) {
                    this.trainer.selectRandomPair();
                    this.panel.nextWord(this.trainer.getCurrentImage());
                } else {
                    this.f.dispose();
                }
                break;
            case "save":
                this.trainer.store();
                break;
            case "load":
                this.trainer.load();
                this.panel.nextWord(getUrl());
                break;
            default:
                System.out.println("Unknown action command.");
        }
    }

    /**
     * Main method to run the SpellingTrainerController.
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        new SpellingTrainerController();
    }

    /**
     * Gets the current image URL from the trainer.
     * @return the current image URL.
     */
    public String getUrl() {
        return trainer.getCurrentImage();
    }

    /**
     * Gets the number of correct spelling attempts.
     * @return the number of correct attempts.
     */
    public int getCorrect() {
        return this.trainer.getCorrectAttempts();
    }

    /**
     * Gets the total number of spelling attempts.
     * @return the total number of attempts.
     */
    public int getTotal() {
        return this.trainer.getTotalAttempts();
    }
}
