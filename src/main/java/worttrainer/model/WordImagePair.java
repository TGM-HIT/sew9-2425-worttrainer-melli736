package worttrainer.model;

/**
 * The WordImagePair class represents a pair consisting of a word and an image URL.
 * This pair is used in the spelling trainer to display an image and check if the
 * corresponding word has been correctly entered by the user.
 * @author Melissa Wallpach
 * @version 2024-10-08
 */
public class WordImagePair {
    private final String word;
    private final String imageUrl;

    /**
     * Constructor to create a WordImagePair object.
     * @param word the word to be associated with the image.
     * @param imageUrl the URL of the image.
     */
    public WordImagePair(String word, String imageUrl) {
        this.word = word;
        this.imageUrl = imageUrl;
    }

    public String getWord() {
        return word;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
