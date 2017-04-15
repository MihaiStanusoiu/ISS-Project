package domain;

import java.util.Date;

/**
 * Name:         Edition
 * Effect:       Main information about the edition.
 * Date:         02/04/2017
 * Tested:       True
 * @author       Tiron Andreea-Ecaterina
 * @version      1.0
 */

public class Edition extends Idable<Integer> {

    private Date startDate;
    private Date endDate;
    private String location;
    private String bio;
    private Date abstractDeadline;
    private Date paperDeadline;
    private Date biddingDeadline;
    private Date evaluationDeadline;
    private Integer idConference;

    public Edition(Integer id,
                      Date startDate,
                      Date endDate,
                      String location,
                      String bio,
                      Date abstractDeadline,
                      Date paperDeadline,
                      Date biddingDeadline,
                      Date evaluationDeadline,
                      Integer idConference) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.bio = bio;
        this.abstractDeadline = abstractDeadline;
        this.paperDeadline = paperDeadline;
        this.biddingDeadline = biddingDeadline;
        this.evaluationDeadline = evaluationDeadline;
        this.idConference = idConference;
    }

    private Edition(String name,
                       String acronym,
                       Date startDate,
                       Date endDate,
                       String location,
                       String bio,
                       Date abstractDeadline,
                       Date paperDeadline,
                       Date biddingDeadline,
                       Date evaluationDeadline,
                       Integer idConference){
        this(0, startDate, endDate, location, bio,
                abstractDeadline, paperDeadline, biddingDeadline, evaluationDeadline, idConference);
    }

    /**
     * Effect: Return the start date.
     * @return [Date] : returns the date.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Effect: Return the end date.
     * @return [Date] : returns the date.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Effect: Return the location of the edition.
     * @return [String] : returns the location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Effect: Return the details of the edition.
     * @return [String] : returns the bio.
     */
    public String getBio() {
        return bio;
    }

    /**
     * Effect: Return the deadline for abstract.
     * @return [Date] : returns the date.
     */
    public Date getAbstractDeadline() {
        return abstractDeadline;
    }

    /**
     * Effect: Return the deadline for papers.
     * @return [Date] : returns the date.
     */
    public Date getPaperDeadline() {
        return paperDeadline;
    }

    /**
     * Effect: Return the deadline for bidding.
     * @return [Date] : returns the date.
     */
    public Date getBiddingDeadline() {
        return biddingDeadline;
    }

    /**
     * Effect: Return the deadline for evaluation.
     * @return [Date] : returns the date.
     */
    public Date getEvaluationDeadline() {
        return evaluationDeadline;
    }

    /**
     * Effect: Return the id of the conference
     * @return [Integer] : returns the id
     */
    public Integer getIdConference() {
        return idConference;
    }
}
