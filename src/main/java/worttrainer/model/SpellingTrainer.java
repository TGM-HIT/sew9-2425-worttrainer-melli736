package worttrainer.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The SpellingTrainer class manages the main logic for the spelling trainer.
 * It maintains a list of word-image pairs and tracks statistics on how many times
 * words were attempted, how many were correct, and how many were incorrect.
 * It provides functions to select a word-image pair and to check user input.
 */
public class SpellingTrainer {
    private final List<WordImagePair> wordImagePairs;
    private WordImagePair currentPair;
    private int totalAttempts;
    private int correctAttempts;
    private int incorrectAttempts;
    private List<WordImagePair> remainingPairs;

    /**
     * Default constructor to initialize a new SpellingTrainer with an empty list.
     */
    public SpellingTrainer() {
        this.wordImagePairs = new ArrayList<>();
        this.remainingPairs = new ArrayList<>();
        this.currentPair = null;
        this.totalAttempts = 0;
        this.correctAttempts = 0;
        this.incorrectAttempts = 0;
    }

    /**
     * Constructor to initialize a new SpellingTrainer with a pre-filled list of word-image pairs.
     *
     * @param wordImagePairs The list of word-image pairs to be used in the trainer.
     */
    public SpellingTrainer(List<WordImagePair> wordImagePairs) {
        if (wordImagePairs == null || wordImagePairs.isEmpty()) {
            throw new IllegalArgumentException("The list of word-image pairs cannot be null or empty.");
        }
        this.wordImagePairs = new ArrayList<>(wordImagePairs);
        this.remainingPairs = new ArrayList<>(wordImagePairs); // Copy for the session
        this.currentPair = null;
        this.totalAttempts = 0;
        this.correctAttempts = 0;
        this.incorrectAttempts = 0;
    }

    // Getter and Setter methods for totalAttempts, correctAttempts, and incorrectAttempts
    public int getTotalAttempts() {
        return totalAttempts;
    }

    public void setTotalAttempts(int totalAttempts) {
        this.totalAttempts = totalAttempts;
    }

    public int getCorrectAttempts() {
        return correctAttempts;
    }

    public void setCorrectAttempts(int correctAttempts) {
        this.correctAttempts = correctAttempts;
    }

    public int getIncorrectAttempts() {
        return incorrectAttempts;
    }

    public void setIncorrectAttempts(int incorrectAttempts) {
        this.incorrectAttempts = incorrectAttempts;
    }

    public List<WordImagePair> getRemainingPairs() {
        return remainingPairs;
    }

    public void setRemainingPairs(List<WordImagePair> remainingPairs) {
        this.remainingPairs = remainingPairs;
    }

    public List<WordImagePair> getWordImagePairs() {
        return wordImagePairs;
    }

    public void selectRandomPair() {
        if (remainingPairs.isEmpty()) {
            remainingPairs = new ArrayList<>(wordImagePairs); // Reset the list when all pairs have been used
        }
        int index = (int) (Math.random() * remainingPairs.size());
        currentPair = remainingPairs.remove(index);
    }

    public String getCurrentImage() {
        if (currentPair == null) {
            throw new IllegalStateException("No word-image pair selected.");
        }
        return currentPair.getImageUrl();
    }

    public String getCurrentWord() {
        if (currentPair == null) {
            throw new IllegalStateException("No word-image pair selected.");
        }
        return currentPair.getWord();
    }

    public boolean spellingCheck(String input) {
        if (currentPair == null) {
            throw new IllegalStateException("No word-image pair selected.");
        }
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null.");
        }
        totalAttempts++;
        if (currentPair.getWord().equalsIgnoreCase(input.trim())) {
            correctAttempts++;
            currentPair = null;
            return true;
        } else {
            incorrectAttempts++;
            return false;
        }
    }

    public String getStatistics() {
        return "Total attempts: " + totalAttempts + ", correct attempts: " + correctAttempts + ", incorrect attempts: " + incorrectAttempts;
    }

    public boolean hasRemainingPairs() {
        return !remainingPairs.isEmpty();
    }
}
