package transfarable;

import java.io.Serializable;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */
public class Tag implements Serializable {

    private Integer id;
    private String word;

    public Tag(Integer id, String word) {
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
