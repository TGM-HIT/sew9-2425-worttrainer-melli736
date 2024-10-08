package worttrainer.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The SpellingTrainer class manages the core logic of the spelling trainer.
 * It holds the list of word-image pairs and tracks the total attempts,
 * correct attempts, and incorrect attempts made by the user.
 * @author Melissa Wallpach
 * @version 2024-10-08
 */
public class SpellingTrainer {
    private List<WordImagePair> wordImagePairs;
    private WordImagePair currentPair;
    private int totalAttempts;
    private int correctAttempts;
    private int incorrectAttempts;
    private List<WordImagePair> remainingPairs;
    private final Persistence storage = new JsonPersistence();

    /**
     * Default constructor that initializes the SpellingTrainer and loads the word pairs.
     */
    public SpellingTrainer() {
        this.storage.load(this);
        setRemainingPairs(this.getWordImagePairs());
        selectRandomPair();
    }

    /**
     * Selects a random word-image pair from the remaining list of pairs.
     */
    public void selectRandomPair() {
        if (remainingPairs.isEmpty()) {
            remainingPairs = new ArrayList<>(wordImagePairs);
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

    public void store() {
        this.storage.save(this);
    }

    public void load() {
        this.storage.load(this);
        selectRandomPair();
    }

    // GETTER & SETTER METHODS

    public List<WordImagePair> getWordImagePairs() {
        return Collections.unmodifiableList(wordImagePairs);
    }

    public WordImagePair getCurrentPair() {
        return currentPair;
    }

    public int getTotalAttempts() {
        return totalAttempts;
    }

    public int getCorrectAttempts() {
        return correctAttempts;
    }

    public int getIncorrectAttempts() {
        return incorrectAttempts;
    }

    public List<WordImagePair> getRemainingPairs() {
        return remainingPairs;
    }

    public void setRemainingPairs(List<WordImagePair> remainingPairs) {
        this.remainingPairs = new ArrayList<>(remainingPairs);
    }

    public void setTotalAttempts(int totalAttempts) {
        this.totalAttempts = totalAttempts;
    }

    public void setCorrectAttempts(int correctAttempts) {
        this.correctAttempts = correctAttempts;
    }

    public void setIncorrectAttempts(int incorrectAttempts) {
        this.incorrectAttempts = incorrectAttempts;
    }

    public void setWordImagePairs(List<WordImagePair> wordlist) {
        this.wordImagePairs = new ArrayList<>(wordlist);
    }
}
