package transferable;

import java.io.Serializable;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */
public class Conference implements Serializable {

    private Integer id;
    private String name;
    private String acronym;

    public Conference(Integer id, String name, String acronym) {
        this.id = id;
        this.name = name;
        this.acronym = acronym;
    }

    public Conference(String name, String acronym) {
        this(0, name, acronym);
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
     * Getter for property 'name'.
     *
     * @return Value for property 'name'.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for property 'acronym'.
     *
     * @return Value for property 'acronym'.
     */
    public String getAcronym() {
        return acronym;
    }

}
