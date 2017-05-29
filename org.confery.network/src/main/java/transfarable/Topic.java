package transfarable;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */
public class Topic {

    private Integer id;
    private String word;

    public Topic(Integer id, String word) {
        this.id = id;
        this.word = word;
    }

    /**
     * Getter for property 'id'.
     *
     * @return Value for property 'id'.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Getter for property 'word'.
     *
     * @return Value for property 'word'.
     */
    public String getWord() {
        return word;
    }
}
