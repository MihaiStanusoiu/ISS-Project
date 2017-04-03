package domain;

import java.util.Date;

/**
 * Name:         Conference
 * Effect:       Main information about the conference.
 * Date:         02/04/2017
 * Tested:       True
 * @author       Tiron Andreea- Ecaterina
 * @version      1.0
 */
public class Conference extends Idable<Integer> {
    private String name;
    private String acronym;
    private Date startDate;
    private Date endDate;
    private String location;
    private String bio;
    private Date abstractDeadline;
    private Date paperDeadline;
    private Date biddingDeadline;
    private Date evaluationDeadline;

    public Conference(Integer id,String name, String acronym, Date startDate, Date endDate, String location, String bio, Date abstractDeadline, Date paperDeadline, Date biddingDeadline, Date evaluationDeadline) {

        this.id = id;
        this.name = name;
        this.acronym = acronym;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.bio = bio;
        this.abstractDeadline = abstractDeadline;
        this.paperDeadline = paperDeadline;
        this.biddingDeadline = biddingDeadline;
        this.evaluationDeadline = evaluationDeadline;
    }

    private Conference(String name, String acronym, Date startDate, Date endDate, String location, String bio, Date abstractDeadline, Date paperDeadline, Date biddingDeadline, Date evaluationDeadline){
        this(0,name,acronym,startDate,endDate,location,bio,abstractDeadline,paperDeadline,biddingDeadline,evaluationDeadline);
    }

    /**
     * Effect: Return the name of the conference.
     * @return [String] : returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Effect: Return the acronym for the conference.
     * @return [String] : returns the acronym.
     */
    public String getAcronym() {
        return acronym;
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
     * Effect: Return the location of the conference.
     * @return [String] : returns the location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Effect: Return the details of the conference.
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

}
