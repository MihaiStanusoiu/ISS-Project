package data;

import java.util.Date;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@SuppressWarnings("all")
public class Edition extends Idable<Integer> {

    /**
     * The starting date for the conference's events.
     */
    private Date startDate;

    /**
     * The ending date for the conference's events.
     */
    private Date endDate;

    /**
     * The conferences location [city/country].
     */
    private String location;

    /**
     * Additinal information about the conference.
     */
    private String bio;

    /**
     * The abstract's submission deadline.
     */
    private Date abstractDeadline;

    /**
     * The paper's submission deadline.
     */
    private Date paperDeadline;

    /**
     * The evaluation's process deadline.
     */
    private Date evaluationDateline;

    /**
     * The bidding's process deadline.
     */
    private Date biddingDeadline;

    /**
     * @param id The edition's id [Integer] -- from database
     * @param startDate The starting date for the conference's events. [Date]
     * @param endDate The ending date for the conference's events. [Date]
     * @param location The conferences location [city/country]. [String]
     * @param bio Additinal information about the conference. [String]
     * @param abstractDeadline The abstract's submission deadline. [Date]
     * @param paperDeadline The paper's submission deadline. [Date]
     * @param evaluationDateline The evaluation's process deadline. [Date]
     * @param biddingDeadline The bidding's process deadline. [Date]
     */
    public Edition(Integer id,
                   Date startDate,
                   Date endDate,
                   String location,
                   String bio,
                   Date abstractDeadline,
                   Date paperDeadline,
                   Date evaluationDateline,
                   Date biddingDeadline) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.bio = bio;
        this.abstractDeadline = abstractDeadline;
        this.paperDeadline = paperDeadline;
        this.evaluationDateline = evaluationDateline;
        this.biddingDeadline = biddingDeadline;
    }

    /**
     * @param startDate The starting date for the conference's events. [Date]
     * @param endDate The ending date for the conference's events. [Date]
     * @param location The conferences location [city/country]. [String]
     * @param bio Additinal information about the conference. [String]
     * @param abstractDeadline The abstract's submission deadline. [Date]
     * @param paperDeadline The paper's submission deadline. [Date]
     * @param evaluationDateline The evaluation's process deadline. [Date]
     * @param biddingDeadline The bidding's process deadline. [Date]
     */
    public Edition(Date startDate,
                   Date endDate,
                   String location,
                   String bio,
                   Date abstractDeadline,
                   Date paperDeadline,
                   Date evaluationDateline,
                   Date biddingDeadline) {
        this(0, startDate, endDate, location, bio,
                abstractDeadline, paperDeadline, evaluationDateline, biddingDeadline);
    }

    /**
     * @param startDate The starting date for the conference's events. [Date]
     * @param endDate The ending date for the conference's events. [Date]
     * @param location The conferences location [city/country]. [String]
     */
    public Edition(Date startDate, Date endDate, String location) {
        this(0, startDate, endDate, location, "", null, null, null, null);
    }

    /**
     * @return The starting date for the conference's events. [Date]
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @return The ending date for the conference's events. [Date]
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @return The conferences location [city/country]. [String]
     */
    public String getLocation() {
        return location;
    }

    /**
     * @return Additinal information about the conference. [String]
     */
    public String getBio() {
        return bio;
    }

    /**
     * @return The abstract's submission deadline. [Date]
     */
    public Date getAbstractDeadline() {
        return abstractDeadline;
    }

    /**
     * @return The paper's submission deadline. [Date]
     */
    public Date getPaperDeadline() {
        return paperDeadline;
    }

    /**
     * @return The evaluation's process deadline. [Date]
     */
    public Date getEvaluationDateline() {
        return evaluationDateline;
    }

    /**
     * @return The bidding's process deadline. [Date]
     */
    public Date getBiddingDeadline() {
        return biddingDeadline;
    }
}
