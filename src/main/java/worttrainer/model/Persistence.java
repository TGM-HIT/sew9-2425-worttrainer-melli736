package worttrainer.model;

/**
 * Interface for saving and loading the trainer's state.
 */
public interface Persistence {
    void saveTrainerState(SpellingTrainer trainer, String filePath);
    SpellingTrainer loadTrainerState(String filePath);
}
