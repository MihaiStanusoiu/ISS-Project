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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edition edition = (Edition) o;
        return (id != null ? id.equals(edition.id) : edition.id == null) &&
                (startDate != null ? startDate.equals(edition.startDate) : edition.startDate == null) &&
                (endDate != null ? endDate.equals(edition.endDate) : edition.endDate == null) &&
                (location != null ? location.equals(edition.location) : edition.location == null) &&
                (bio != null ? bio.equals(edition.bio) : edition.bio == null) &&
                (abstractDeadline != null ? abstractDeadline.equals(edition.abstractDeadline) :
                        edition.abstractDeadline == null) &&
                (paperDeadline != null ? paperDeadline.equals(edition.paperDeadline) :
                        edition.paperDeadline == null) &&
                (evaluationDeadline != null ? evaluationDeadline.equals(edition.evaluationDeadline) :
                        edition.evaluationDeadline == null) &&
                (biddingDeadline != null ? biddingDeadline.equals(edition.biddingDeadline) :
                        edition.biddingDeadline == null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (bio != null ? bio.hashCode() : 0);
        result = 31 * result + (abstractDeadline != null ? abstractDeadline.hashCode() : 0);
        result = 31 * result + (paperDeadline != null ? paperDeadline.hashCode() : 0);
        result = 31 * result + (evaluationDeadline != null ? evaluationDeadline.hashCode() : 0);
        result = 31 * result + (biddingDeadline != null ? biddingDeadline.hashCode() : 0);
        return result;
    }
}
