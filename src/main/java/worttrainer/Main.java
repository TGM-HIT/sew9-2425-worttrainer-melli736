package worttrainer;

import worttrainer.model.*;
import worttrainer.view.SpellingTrainerView;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This Main class is used to test the functionality of the SpellingTrainer via the command line.
 * The application shows users the URL of an image and prompts them to type the corresponding word.
 * The inputs are checked, and statistics are displayed.
 */
public class Main {
    private static final String SAVED_TRAINER_PATH = "/Users/melli/Library/CloudStorage/OneDrive-tgm-DieSchulederTechnik/5BHIT/SEW/WorttrainerReloaded/resources/savedSpellingTrainer.json";

    public static void main(String[] args) {
        // Initialize persistence strategy (JSON in this case)
        JsonPersistence persistence = new JsonPersistence();

        SpellingTrainer trainer;

        // Check if a saved trainer exists
        File savedFile = new File(SAVED_TRAINER_PATH);
        if (savedFile.exists()) {
            trainer = persistence.loadTrainerState(SAVED_TRAINER_PATH);
            if (trainer == null) {
                trainer = createNewTrainer(); // Create a new trainer if loading failed
            }
        } else {
            trainer = createNewTrainer(); // Create a new trainer if no saved file exists
        }

        // Launch GUI on the Event Dispatch Thread (EDT)
        SpellingTrainer finalTrainer = trainer;
        SwingUtilities.invokeLater(() -> new SpellingTrainerView(finalTrainer, persistence).setVisible(true));

    }

    private static SpellingTrainer createNewTrainer() {
        List<WordImagePair> pairs = new ArrayList<>();
        pairs.add(new WordImagePair("Dog", "https://upload.wikimedia.org/wikipedia/commons/4/42/Harzer_Fuchs_Hündin_2.jpg"));
        pairs.add(new WordImagePair("Cat", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/600px-Hauskatze_langhaar.jpg"));
        pairs.add(new WordImagePair("Bird", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ee/Crested_Tern_Tasmania_%28edit%29.jpg/600px-Crested_Tern_Tasmania_%28edit%29.jpg"));
        pairs.add(new WordImagePair("Tree", "https://upload.wikimedia.org/wikipedia/commons/5/56/Sequoiadendron_giganteum_at_Kenilworth_Castle.jpg"));

        return new SpellingTrainer(pairs);
    }
}
