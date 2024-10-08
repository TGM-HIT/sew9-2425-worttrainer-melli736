package worttrainer.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Implements JSON format saving and loading for the SpellingTrainer.
 * This class is responsible for persisting the trainer's state to and from a JSON file.
 * @author Melissa Wallpach
 * @version 2024-10-08
 */
public class JsonPersistence implements Persistence {

    /**
     * Constructor for JsonPersistence.
     */
    public JsonPersistence() {}

    /**
     * Loads the state of the trainer from a JSON file.
     * @param trainer the SpellingTrainer instance where the data will be loaded into.
     */
    @Override
    public void load(SpellingTrainer trainer) {
        try {
            String content = new String(Files.readAllBytes(Paths.get("spellingTrainer.json")));
            JSONObject jsonObject = new JSONObject(content);

            int total = jsonObject.optInt("total", 0);
            trainer.setTotalAttempts(total);

            int correct = jsonObject.optInt("correct", 0);
            trainer.setCorrectAttempts(correct);

            JSONArray words = jsonObject.getJSONArray("words");
            ArrayList<WordImagePair> wordList = new ArrayList<>();
            for (Object o : words) {
                JSONObject wordObject = (JSONObject) o;
                String word = wordObject.getString("word");
                String url = wordObject.getString("url");
                wordList.add(new WordImagePair(word, url));
            }

            trainer.setWordImagePairs(wordList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the state of the trainer to a JSON file.
     * @param trainer the SpellingTrainer instance whose data will be saved.
     */
    @Override
    public void save(SpellingTrainer trainer) {
        try {
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("total", trainer.getTotalAttempts());
            jsonObject.put("correct", trainer.getCorrectAttempts());

            JSONArray words = new JSONArray();
            for (WordImagePair word : trainer.getWordImagePairs()) {
                JSONObject wordObject = new JSONObject();
                wordObject.put("word", word.getWord());
                wordObject.put("url", word.getImageUrl());
                words.put(wordObject);
            }

            jsonObject.put("words", words);
            Files.write(Paths.get("spellingTrainer.json"), jsonObject.toString(4).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
