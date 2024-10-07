package worttrainer;

import worttrainer.model.*;
import worttrainer.view.SpellingTrainerView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This Main class is used to test the functionality of the SpellingTrainer via the command line.
 * The application shows users the URL of an image and prompts them to type the corresponding word.
 * The inputs are checked, and statistics are displayed.
 */
public class Main {
    public static void main(String[] args) {
        // Initialize persistence strategy (JSON in this case)
        JsonPersistence persistence = new JsonPersistence();
        Scanner scanner = new Scanner(System.in);

        List<WordImagePair> pairs = new ArrayList<>();
        pairs.add(new WordImagePair("Dog", "https://upload.wikimedia.org/wikipedia/commons/4/42/Harzer_Fuchs_Hündin_2.jpg"));
        pairs.add(new WordImagePair("Cat", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Hauskatze_langhaar.jpg/600px-Hauskatze_langhaar.jpg"));
        pairs.add(new WordImagePair("Bird", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ee/Crested_Tern_Tasmania_%28edit%29.jpg/600px-Crested_Tern_Tasmania_%28edit%29.jpg"));
        pairs.add(new WordImagePair("Tree", "https://upload.wikimedia.org/wikipedia/commons/5/56/Sequoiadendron_giganteum_at_Kenilworth_Castle.jpg"));

        SpellingTrainer trainer = new SpellingTrainer(pairs);

        // Launch GUI on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> new SpellingTrainerView(trainer, persistence).setVisible(true));

    }
}
