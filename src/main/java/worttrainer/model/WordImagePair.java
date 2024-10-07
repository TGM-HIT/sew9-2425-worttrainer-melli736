package worttrainer.model;

/**
 * The WordImagePair class represents a pair consisting of a word and an image URL.
 * It is used to manage the word-image pairs in the spelling trainer.
 * <p>
 * A WordImagePair must always be in a valid state. The word and image URL cannot be null or empty,
 * and the URL must be in a valid URL format.
 */
public class WordImagePair {
    private final String word;
    private final String imageUrl;

    /**
     * Constructor
     * @param word The word associated with the image.
     * @param imageUrl The URL of the image associated with the word.
     * @throws IllegalArgumentException if the word or URL is invalid.
     */
    public WordImagePair(String word, String imageUrl) {
        this.word = word;
        this.imageUrl = imageUrl;
        validate();
    }

    /**
     * Returns the word associated with this WordImagePair.
     * @return The word.
     */
    public String getWord() {
        return word;
    }

    /**
     * Returns the image URL associated with this WordImagePair.
     * @return The image URL.
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Validates the WordImagePair. Ensures that the word and image URL are valid.
     * @throws IllegalArgumentException if the word or image URL is null, empty, or invalid.
     */
    private void validate() {
        if (word == null || word.trim().isEmpty()) {
            throw new IllegalArgumentException("The word cannot be null or empty.");
        }
        if (imageUrl == null || imageUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("The image URL cannot be null or empty.");
        }
        if (!imageUrl.matches("^\\.\\./\\.\\./resources/.*\\.(jpg|jpeg|img|webp)$")) {
            throw new IllegalArgumentException("Invalid relative path format.");
        }
    }
}
