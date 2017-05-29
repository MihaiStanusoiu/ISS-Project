package transfarable;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */
public class Edition implements Serializable {

    private Integer id;
    private Date startDate;
    private Date endDate;
    private String location;
    private String bio;
    private Date abstractDeadline;
    private Date paperDeadline;
    private Date evaluationDeadline;
    private Date biddingDeadline;

    public Edition(Integer id,
                   Date startDate,
                   Date endDate,
                   String location,
                   String bio,
                   Date abstractDeadline,
                   Date paperDeadline,
                   Date evaluationDeadline,
                   Date biddingDeadline) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.bio = bio;
        this.abstractDeadline = abstractDeadline;
        this.paperDeadline = paperDeadline;
        this.evaluationDeadline = evaluationDeadline;
        this.biddingDeadline = biddingDeadline;
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
     * Getter for property 'startDate'.
     *
     * @return Value for property 'startDate'.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Getter for property 'endDate'.
     *
     * @return Value for property 'endDate'.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Getter for property 'location'.
     *
     * @return Value for property 'location'.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Getter for property 'bio'.
     *
     * @return Value for property 'bio'.
     */
    public String getBio() {
        return bio;
    }

    /**
     * Getter for property 'abstractDeadline'.
     *
     * @return Value for property 'abstractDeadline'.
     */
    public Date getAbstractDeadline() {
        return abstractDeadline;
    }

    /**
     * Setter for property 'abstractDeadline'.
     *
     * @param abstractDeadline Value to set for property 'abstractDeadline'.
     */
    public void setAbstractDeadline(Date abstractDeadline) {
        this.abstractDeadline = abstractDeadline;
    }

    /**
     * Getter for property 'paperDeadline'.
     *
     * @return Value for property 'paperDeadline'.
     */
    public Date getPaperDeadline() {
        return paperDeadline;
    }

    /**
     * Getter for property 'evaluationDeadline'.
     *
     * @return Value for property 'evaluationDeadline'.
     */
    public Date getEvaluationDeadline() {
        return evaluationDeadline;
    }

    /**
     * Getter for property 'biddingDeadline'.
     *
     * @return Value for property 'biddingDeadline'.
     */
    public Date getBiddingDeadline() {
        return biddingDeadline;
    }

}