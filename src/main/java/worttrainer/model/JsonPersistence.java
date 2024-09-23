package worttrainer.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

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
            List<WordImagePair> pairs = trainer.getWordImagePairs();
            gson.toJson(pairs, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SpellingTrainer loadTrainerState(String filePath) {
        try (Reader reader = new FileReader(filePath)) {
            Type pairListType = new TypeToken<List<WordImagePair>>() {}.getType();
            List<WordImagePair> pairs = gson.fromJson(reader, pairListType);
            return new SpellingTrainer(pairs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
