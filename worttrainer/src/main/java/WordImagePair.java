/**
 * Die Klasse WordImagePair repräsentiert ein Paar aus einem Wort und einer Bild-URL, das Bild zeigt den Schülern, welches
 * Wor sie eintippen sollen.
 * Sie wird verwendet, um die Wort-Bild-Zuordnungen im Rechtschreibtrainer zu verwalten.
 *
 * Ein WordImagePair muss immer in einem gültigen Zustand sein. Das Wort und die Bild-URL
 * dürfen nicht null oder leer sein, und die URL muss ein gültiges URL-Format haben.
 */
public class WordImagePair {
    private String word;
    private String imageUrl;

    /**
     * Konstruktor
     * @param word Das Wort, das dem Bild zugeordnet wird.
     * @param imageUrl Die URL des Bildes, die mit dem Wort verknüpft ist.
     * @throws IllegalArgumentException wenn das Wort oder die URL ungültig sind.
     */
    public WordImagePair(String word, String imageUrl) {
        this.word = word;
        this.imageUrl = imageUrl;
        validate();
    }

    /**
     * Gibt das Wort zurück, das mit diesem WordImagePair verbunden ist.
     * @return Das Wort.
     */
    public String getWord() {
        return word;
    }

    /**
     * Gibt die Bild-URL zurück, die zu diesem WordImagePair gehört.
     * @return Die Bild-URL.
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Validiert das WordImagePair. Überprüft, ob das Wort und die Bild-URL gültig sind.
     * @throws IllegalArgumentException wenn das Wort oder die Bild-URL null, leer oder ungültig sind.
     */
    private void validate() {
        if (word == null || word.trim().isEmpty()) {
            throw new IllegalArgumentException("Das Wort darf nicht null oder leer sein.");
        }
        if (imageUrl == null || imageUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("Die Bild-URL darf nicht null oder leer sein.");
        }
        if (!imageUrl.matches("^(http|https)://.*$")) {
            throw new IllegalArgumentException("Ungültiges URL-Format.");
        }
    }
}
