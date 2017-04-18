package database_domain;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Name:    ConferenceTable
 * Effect:  A class for the database table Conference
 * Date:    9/4/2017
 * Tested:  False
 *
 * @author Simion George-Vlad
 * @version 1.0
 */

@Entity
@Table(name = "Conference")
@SuppressWarnings("unused")
public class ConferenceTable {

    @Id@GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_conference")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "acronym")
    private String acronym;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "location")
    private String location;

    @Column(name = "bio")
    private String bio;

    @Column(name = "abstract_deadline")
    private Date abstractDeadline;

    @Column(name = "paper_deadline")
    private Date paperDeadline;

    @Column(name = "evaluation_deadline")
    private Date evaluationDeadline;

    @Column(name = "bidding_deadline")
    private Date biddingDeadline;

    @OneToMany(mappedBy = "id_conference")
    private ArrayList<SectionTable> sections;

    @OneToMany(mappedBy = "id_conference")
    private ArrayList<SubmissionTable> submissions;

    @OneToMany(mappedBy = "id_conference")
    private ArrayList<ConferenceMemberTable> members;

    /**
     * Empty constructor
     */
    public ConferenceTable() { }

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
     * Effect: Return the name of this conference.
     * @return [String]: returns the name of conference.
     */
    public String getName() {
        return name;
    }

    /**
     * Effect: Sets the name of a conference.
     * @param name: new value for conference name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Effect: Return the acronym of this conference.
     * @return [String]: returns the acronym of conference.
     */
    public String getAcronym() {
        return acronym;
    }

    /**
     * Effect: Sets the acronym of a conference.
     * @param acronym: new value for conference acronym.
     */
    public void setAcronym(String acronym) {
        this.acronym = acronym;
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

    /**
     * Effect: Return the sections of this conference.
     * @return [ArrayList<SectionTable>]: returns the sections of conference.
     */
    public ArrayList<SectionTable> getSections() {
        return sections;
    }

    /**
     * Effect: Sets the sections of a conference.
     * @param sections: new value for conference sections.
     */
    public void setSections(ArrayList<SectionTable> sections) {
        this.sections = sections;
    }

    /**
     * Effect: Return the submissions of this conference.
     * @return [ArrayList<SubmissionTable>]: returns the submissions of conference.
     */
    public ArrayList<SubmissionTable> getSubmissions() {
        return submissions;
    }

    /**
     * Effect: Sets the submissions of a conference.
     * @param submissions: new value for conference Submissions.
     */
    public void setSubmissions(ArrayList<SubmissionTable> submissions) {
        this.submissions = submissions;
    }

    /**
     * Effect: Return the members of this conference.
     * @return [ArrayList<ConferenceMemberTable>]: returns the members of conference.
     */
    public ArrayList<ConferenceMemberTable> getMembers() {
        return members;
    }

    /**
     * Effect: Sets the members of a conference.
     * @param members: new value for conference members.
     */
    public void setMembers(ArrayList<ConferenceMemberTable> members) {
        this.members = members;
    }
}
