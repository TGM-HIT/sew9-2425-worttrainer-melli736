package worttrainer.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Implements JSON format saving and loading for the SpellingTrainer.
 */
public class JsonPersistence implements Persistence{
    private final Gson gson;

    public JsonPersistence() {
        this.gson = new Gson();
    }

    public void saveTrainerState(SpellingTrainer trainer, String filePath) {
        try (Writer writer = new FileWriter(filePath)) {
            GameState state = new GameState(trainer);
            gson.toJson(state, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SpellingTrainer loadTrainerState(String filePath) {
        try (Reader reader = new FileReader(filePath)) {
            GameState state = gson.fromJson(reader, GameState.class);
            return new SpellingTrainer(state.wordImagePairs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // A class to represent the game state for saving/loading.
    private static class GameState {
        List<WordImagePair> wordImagePairs;
        int totalAttempts;
        int correctAttempts;
        int incorrectAttempts;

        public GameState(SpellingTrainer trainer) {
            this.wordImagePairs = trainer.getWordImagePairs();
            this.totalAttempts = trainer.getTotalAttempts();
            this.correctAttempts = trainer.getCorrectAttempts();
            this.incorrectAttempts = trainer.getIncorrectAttempts();
        }
    }
}
