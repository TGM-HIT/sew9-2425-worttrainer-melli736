package worttrainer.model;

/**
 * Interface for saving and loading the trainer's state.
 */
public interface Persistence {
    void save(SpellingTrainer trainer);
    void load(SpellingTrainer trainer);
}
