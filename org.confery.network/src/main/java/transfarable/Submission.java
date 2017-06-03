package transfarable;

import java.io.Serializable;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */
public class Submission implements Serializable, IdableTransfer<Integer> {

    private Integer id;
    private String name;
    private String status;
    private String abstractUrl;
    private String fullPaperUrl;
    private Boolean isPaid;

    public Submission(Integer id, String name, String status, String abstractUrl, String fullPaperUrl, Boolean isPaid) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.abstractUrl = abstractUrl;
        this.fullPaperUrl = fullPaperUrl;
        this.isPaid = isPaid;
    }

    public Submission() {
        this(0, "", "", "", "", Boolean.FALSE);
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
     * Getter for property 'status'.
     *
     * @return Value for property 'status'.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Getter for property 'abstractUrl'.
     *
     * @return Value for property 'abstractUrl'.
     */
    public String getAbstractUrl() {
        return abstractUrl;
    }

    /**
     * Getter for property 'fullPaperUrl'.
     *
     * @return Value for property 'fullPaperUrl'.
     */
    public String getFullPaperUrl() {
        return fullPaperUrl;
    }

    /**
     * Getter for property 'paid'.
     *
     * @return Value for property 'paid'.
     */
    public Boolean isPaid() {
        return isPaid;
    }

}
