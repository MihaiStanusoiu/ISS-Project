package domain;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Name:    EditionEntity
 * Effect:  A class for the database table EditionEntity
 * Date:    9/4/2017
 * Tested:  False
 *
 * @author Simion George-Vlad
 * @version 1.0
 */

@Entity
@Table(name = "EDITION")
@SuppressWarnings("unused")
public class EditionEntity implements Idable<Integer> {

    @Id@GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_CONFERENCE")
    private Integer id;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "BIO")
    private String bio;

    @Column(name = "ABSTRACT_DEADLINE")
    private Date abstractDeadline;

    @Column(name = "PAPER_DEADLINE")
    private Date paperDeadline;

    @Column(name = "EVALUATION_DEADLINE")
    private Date evaluationDeadline;

    @Column(name = "BIDDING_DEADLINE")
    private Date biddingDeadline;

    @ManyToOne
    @JoinColumn(name="CONFERENCE_ID")
    private ConferenceEntity conference;

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "edition",cascade = CascadeType.ALL)
//    private Set<EditionMemberEntity> editionMembers;
//
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "edition_session",cascade = CascadeType.ALL)
    private Set<SessionEntity> sessions;
//
//    @OneToMany( mappedBy = "edition_submission",cascade = CascadeType.ALL)
//    private Set<SubmissionEntity> submissions;

    /**
     * Empty constructor
     */
    public EditionEntity() { }

    public EditionEntity(Date startDate, Date endDate, String location, String bio, Date abstractDeadline, Date paperDeadline, Date evaluationDeadline, Date biddingDeadline) {
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
     * Effect: Return the id of this conference.
     * @return [Integer]: returns the id of conference.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Effect: Sets the id of a conference.
     * @param id: new value for conference id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Effect: Return the start date of this conference.
     * @return [Date]: returns the startDate of conference.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Effect: Sets the start date of a conference.
     * @param startDate: new value for conference startDate.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Effect: Return the end date of this conference.
     * @return [Date]: returns the endDate of conference.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Effect: Sets the end date of a conference.
     * @param endDate: new value for conference endDate.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Effect: Return the location of this conference.
     * @return [String]: returns the location of conference.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Effect: Sets the location of a conference.
     * @param location: new value for conference location.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Effect: Return the bio of this conference.
     * @return [String]: returns the bio of conference.
     */
    public String getBio() {
        return bio;
    }

    /**
     * Effect: Sets the bio of a conference.
     * @param bio: new value for conference bio.
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * Effect: Return the abstract deadline of this conference.
     * @return [Date]: returns the abstractDeadline of conference.
     */
    public Date getAbstractDeadline() {
        return abstractDeadline;
    }

    /**
     * Effect: Sets the abstract deadline of a conference.
     * @param abstractDeadline: new value for conference abstractDeadline.
     */
    public void setAbstractDeadline(Date abstractDeadline) {
        this.abstractDeadline = abstractDeadline;
    }

    /**
     * Effect: Return the paper deadline of this conference.
     * @return [Date]: returns the paperDeadline of conference.
     */
    public Date getPaperDeadline() {
        return paperDeadline;
    }

    /**
     * Effect: Sets the paper deadline of a conference.
     * @param paperDeadline: new value for conference paperDeadline.
     */
    public void setPaperDeadline(Date paperDeadline) {
        this.paperDeadline = paperDeadline;
    }

    /**
     * Effect: Return the evaluation deadline of this conference.
     * @return [Date]: returns the evaluationDeadline of conference.
     */
    public Date getEvaluationDeadline() {
        return evaluationDeadline;
    }

    /**
     * Effect: Sets the evaluation deadline of a conference.
     * @param evaluationDeadline: new value for conference evaluationDeadline.
     */
    public void setEvaluationDeadline(Date evaluationDeadline) {
        this.evaluationDeadline = evaluationDeadline;
    }

    /**
     * Effect: Return the bidding deadline of this conference.
     * @return [Date]: returns the biddingDeadline of conference.
     */
    public Date getBiddingDeadline() {
        return biddingDeadline;
    }

    /**
     * Effect: Sets the bidding deadline of a conference.
     * @param biddingDeadline: new value for conference biddingDeadline.
     */
    public void setBiddingDeadline(Date biddingDeadline) {
        this.biddingDeadline = biddingDeadline;
    }

    public ConferenceEntity getConference() {
        return conference;
    }

    public void setConference(ConferenceEntity conference) {
        this.conference = conference;
    }

//    public Set<EditionMemberEntity> getEditionMembers() {
//        return editionMembers;
//    }
//
//    public void setEditionMembers(Set<EditionMemberEntity> editionMembers) {
//        this.editionMembers = editionMembers;
//    }
//
    public Set<SessionEntity> getSessions() {
        return sessions;
    }

    public void setSessions(Set<SessionEntity> sessions) {
        this.sessions = sessions;
    }
//
//    public Set<SubmissionEntity> getSubmissions() {
//        return submissions;
//    }
//
//    public void setSubmissions(Set<SubmissionEntity> submissions) {
//        this.submissions = submissions;
//    }

}
