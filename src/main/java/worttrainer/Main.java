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
        pairs.add(new WordImagePair("Dog", "../../resources/hund-im-herbst-760x570.jpg"));
        pairs.add(new WordImagePair("Cat", "../../resources/kitty-cat-kitten-pet-45201.jpeg"));
        pairs.add(new WordImagePair("Bird", "../../resources/photo-1486365227551-f3f90034a57c.jpeg"));
        pairs.add(new WordImagePair("Tree", "../../resources/2048x1365-Oak-trees-SEO-GettyImages-90590330-b6bfe8b.jpg.webp"));

        SpellingTrainer trainer = new SpellingTrainer(pairs);
        // Ask the user if they want to start a new game or load a saved session

        // Launch GUI on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> new SpellingTrainerView(trainer, persistence).setVisible(true));

    }
}
