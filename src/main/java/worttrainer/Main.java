package worttrainer;

import worttrainer.model.*;

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
        Persistence persistence = new JsonPersistence();
        Scanner scanner = new Scanner(System.in);

        // Ask the user if they want to start a new game or load a saved session
        System.out.println("Do you want to start a new session or load an existing one? (new/load): ");
        String sessionChoice = scanner.nextLine().trim().toLowerCase();

        SpellingTrainer trainer;
        if ("load".equals(sessionChoice)) {
            System.out.println("Enter the file path to load the session: ");
            String filePath = scanner.nextLine();
            trainer = persistence.loadTrainerState(filePath);

            if (trainer == null) {
                System.out.println("Failed to load session. Starting a new one.");
                trainer = createNewTrainer();
            }
        } else {
            trainer = createNewTrainer();
        }

        boolean continuePlaying = true;

        while (continuePlaying) {
            while (trainer.hasRemainingPairs()) {
                trainer.selectRandomPair();
                String imageUrl = trainer.getCurrentImageUrl();
                System.out.println("Spell the word for the image: " + imageUrl);
                System.out.print("Your guess (or type 'save' to save the session): ");
                String guess = scanner.nextLine().trim().toLowerCase();

                if ("save".equals(guess)) {
                    System.out.println("Enter the file path to save the session: ");
                    String filePath = scanner.nextLine();
                    persistence.saveTrainerState(trainer, filePath);
                    System.out.println("Session saved successfully.");
                } else {
                    boolean correct = trainer.spellingCheck(guess);
                    if (correct) {
                        System.out.println("Correct!");
                    } else {
                        System.out.println("Incorrect. The correct word was: " + trainer.getCurrentWord());
                    }
                }
            }

            // All images have been shown
            System.out.println("You have spelled all the images. Here's your score:");
            System.out.println(trainer.getStatistics());

            // Ask user if they want to play again
            System.out.print("Want to give it another try? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();

            if ("yes".equals(response)) {
                trainer = createNewTrainer();
            } else {
                continuePlaying = false;
            }
        }

        scanner.close();
        System.out.println("Thank you for playing!");
    }

    // Helper method to create a new trainer session with default pairs
    private static SpellingTrainer createNewTrainer() {
        List<WordImagePair> pairs = new ArrayList<>();
        pairs.add(new WordImagePair("Dog", "https://www.zooroyal.at/magazin/wp-content/uploads/2019/10/hund-im-herbst-760x570.jpg"));
        pairs.add(new WordImagePair("Cat", "https://images.pexels.com/photos/45201/kitty-cat-kitten-pet-45201.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"));
        pairs.add(new WordImagePair("Bird", "https://images.unsplash.com/photo-1486365227551-f3f90034a57c?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8YmlyZHxlbnwwfHwwfHx8MA%3D%3D"));
        pairs.add(new WordImagePair("Tree", "https://images.immediate.co.uk/production/volatile/sites/10/2023/06/2048x1365-Oak-trees-SEO-GettyImages-90590330-b6bfe8b.jpg?quality=90&webp=true&resize=1880,1254"));
        return new SpellingTrainer(pairs);
    }
}
