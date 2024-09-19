import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse SpellingTrainer stellt die Hauptlogik für den Rechtschreibtrainer bereit.
 * Sie verwaltet eine Liste von Wort-Bild-Paaren und führt eine Statistik darüber, wie oft insgesamt,
 * korrekt oder falsch geraten wurde. Sie bietet Funktionen zum Auswählen eines Paares und zum
 * Überprüfen von Benutzereingaben.
 */
public class SpellingTrainer {
    private List<WordImagePair> wordImagePairs;
    private WordImagePair currentPair;
    private int totalAttempts;
    private int correctAttempts;
    private int incorrectAttempts;
    private List<WordImagePair> remainingPairs;

    /**
     * Standard-Konstruktor zur Initialisierung eines neuen SpellingTrainer mit einer leeren Liste.
     */
    public SpellingTrainer() {
        this.wordImagePairs = new ArrayList<>();
        this.remainingPairs = new ArrayList<>();
        this.currentPair = null;
        this.totalAttempts = 0;
        this.correctAttempts = 0;
        this.incorrectAttempts = 0;
    }

    /**
     * Konstruktor zur Initialisierung eines neuen SpellingTrainer mit einer bereits gefüllten Liste.
     *
     * @param wordImagePairs Die Liste von Wort-Bild-Paaren, die im Trainer verwendet wird.
     */
    public SpellingTrainer(List<WordImagePair> wordImagePairs) {
        if (wordImagePairs == null || wordImagePairs.isEmpty()) {
            throw new IllegalArgumentException("Die Liste der Wort-Bild-Paare darf nicht null oder leer sein.");
        }
        this.wordImagePairs = new ArrayList<>(wordImagePairs);
        this.remainingPairs = new ArrayList<>(wordImagePairs); // Kopie für die Session
        this.currentPair = null;
        this.totalAttempts = 0;
        this.correctAttempts = 0;
        this.incorrectAttempts = 0;
    }

    /**
     * Wählt ein zufälliges Wort-Bild-Paar aus der verbleibenden Liste der Paare aus.
     * Wenn alle Paare ausgewählt wurden, wird die Liste zurückgesetzt.
     *
     * @throws IllegalStateException wenn keine Wort-Bild-Paare verfügbar sind.
     */
    public void selectRandomPair() {
        if (remainingPairs.isEmpty()) {
            remainingPairs = new ArrayList<>(wordImagePairs); // Liste zurücksetzen, wenn alle Paare verwendet wurden
        }
        int index = (int) (Math.random() * remainingPairs.size());
        currentPair = remainingPairs.remove(index);
    }

    /**
     * Gibt die URL des aktuellen ausgewählten Bildes zurück.
     *
     * @return Die Bild-URL des aktuellen Paares.
     * @throws IllegalStateException wenn kein Paar ausgewählt wurde.
     */
    public String getCurrentImageUrl() {
        if (currentPair == null) {
            throw new IllegalStateException("Kein Wort-Bild-Paar ausgewählt.");
        }
        return currentPair.getImageUrl();
    }

    /**
     * Überprüft die Eingabe des Nutzers und aktualisiert die Statistik entsprechend.
     *
     * @param input Die Eingabe des Nutzers.
     * @return true, wenn die Eingabe korrekt ist; false, wenn sie falsch ist.
     * @throws IllegalStateException wenn kein Paar ausgewählt ist.
     * @throws IllegalArgumentException wenn die Eingabe null ist.
     */
    public boolean spellingCheck(String input) {
        if (currentPair == null) {
            throw new IllegalStateException("Kein Wort-Bild-Paar ausgewählt.");
        }
        if (input == null) {
            throw new IllegalArgumentException("Eingabe darf nicht null sein.");
        }
        totalAttempts++;
        if (currentPair.getWord().equalsIgnoreCase(input.trim())) {
            correctAttempts++;
            currentPair = null;
            return true;
        } else {
            incorrectAttempts++;
            return false;
        }
    }

    /**
     * Berechnet und gibt die aktuelle Statistik der Versuche als String zurück.
     *
     * @return Die Statistik als String.
     */
    public String getStatistics() {
        return "Gesamtversuche: " + totalAttempts + ", korrekte Versuche: " + correctAttempts + ", kalsche Versuche: " + incorrectAttempts;
    }

    /**
     * Gibt die Anzahl der verbleibenden Paare zurück.
     *
     * @return Die Anzahl der verbleibenden Wort-Bild-Paare.
     */
    public boolean hasRemainingPairs() {
        return !remainingPairs.isEmpty();
    }
}
