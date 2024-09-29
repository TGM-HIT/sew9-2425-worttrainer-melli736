package worttrainer.model;

import com.google.gson.Gson;
import java.io.*;

/**
 * Implements Persistence using JSON format for saving and loading.
 */
public class JsonPersistence implements Persistence {
    private final Gson gson;

    public JsonPersistence() {
        this.gson = new Gson();
    }

    @Override
    public void saveTrainerState(SpellingTrainer trainer, String filePath) {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(trainer, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SpellingTrainer loadTrainerState(String filePath) {
        try (Reader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, SpellingTrainer.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
