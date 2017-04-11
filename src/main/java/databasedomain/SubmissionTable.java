package databasedomain;

import javax.persistence.*;
import java.util.ArrayList;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Name:    SubmissionTable
 * Effect:  Class for the database table Submission
 * Date:    9/4/2017
 * Tested:  False
 *
 * @author Simion George-Vlad
 * @version 1.0
 */

@Entity
@Table(name = "Submission")
@SuppressWarnings("unused")
public class SubmissionTable {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_submission")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_conference")
    private Integer idConference;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private String status;

    @Column(name = "abstract_url")
    private String abstractUrl;

    @Column(name = "full_paper_url")
    private String fullPaperUrl;

    @Column(name = "is_paid")
    private boolean isPaid;

    @OneToMany(mappedBy = "idSubmission")
    private ArrayList<SubmissionTagTable> submissionTags;

    @OneToMany(mappedBy = "idSubmission")
    private ArrayList<SubmissionTopicTable> submissionTopic;

    @OneToMany(mappedBy = "idSubmission")
    private ArrayList<AuthorSubmissionTable> submissionAuthors;

    @OneToMany(mappedBy = "idSubmission")
    private ArrayList<ReviewerTable> reviewers;

    /**
     * Empty Constructor
     */
    public SubmissionTable(){}

    /**
     * Effect: Return the id of this submission.
     * @return [Integer]: returns the id of Submission.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Effect: Sets the id of a submission.
     * @param id: new value for submission id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Effect: Return the id of this submission.
     * @return [Integer]: returns the id of Submission.
     */
    public Integer getIdConference() {
        return idConference;
    }

    /**
     * Effect: Sets the id of the submission's conference.
     * @param idConference: new value for conference id.
     */
    public void setIdConference(Integer idConference) {
        this.idConference = idConference;
    }

    /**
     * Effect: Return the name of this submission.
     * @return [String]: returns the name of Submission.
     */
    public String getName() {
        return name;
    }

    /**
     * Effect: Sets the name of a submission.
     * @param name: new value for submission name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Effect: Return the status of this submission.
     * @return [String]: returns the status of Submission.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Effect: Sets the status of a submission.
     * @param status: new value for submission status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Effect: Return the abstract url of this submission.
     * @return [String]: returns the abstractUrl of Submission.
     */
    public String getAbstractUrl() {
        return abstractUrl;
    }

    /**
     * Effect: Sets the abstract url of a submission.
     * @param abstractUrl: new value for submission abstractUrl.
     */
    public void setAbstractUrl(String abstractUrl) {
        this.abstractUrl = abstractUrl;
    }

    /**
     * Effect: Return the full paper url of this submission.
     * @return [String]: returns the fullPaperUrl of Submission.
     */
    public String getFullPaperUrl() {
        return fullPaperUrl;
    }

    /**
     * Effect: Sets the full paper url of a submission.
     * @param fullPaperUrl: new value for submission fullPaperUrl.
     */
    public void setFullPaperUrl(String fullPaperUrl) {
        this.fullPaperUrl = fullPaperUrl;
    }

    /**
     * Effect: Return the paid status of this submission.
     * @return [boolean]: returns the paid status of a Submission.
     */
    public boolean isPaid() {
        return isPaid;
    }

    /**
     * Effect: Sets the paid status of a submission.
     * @param paid: new value for submission isPaid.
     */
    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    /**
     * Effect: Return the tags of this submission.
     * @return [ArrayList<SubmissionTagTable>]: returns the tags of a Submission.
     */
    public ArrayList<SubmissionTagTable> getSubmissionTags() {
        return submissionTags;
    }

    /**
     * Effect: Sets the tags of a submission.
     * @param submissionTags: new value for submission tags.
     */
    public void setSubmissionTags(ArrayList<SubmissionTagTable> submissionTags) {
        this.submissionTags = submissionTags;
    }

    /**
     * Effect: Return the topics of this submission.
     * @return [ArrayList<SubmissionTopicTable>]: returns the topics of a Submission.
     */
    public ArrayList<SubmissionTopicTable> getSubmissionTopic() {
        return submissionTopic;
    }

    /**
     * Effect: Sets the topics of a submission.
     * @param submissionTopic: new value for submission topics.
     */
    public void setSubmissionTopic(ArrayList<SubmissionTopicTable> submissionTopic) {
        this.submissionTopic = submissionTopic;
    }

    /**
     * Effect: Return the authors of this submission.
     * @return [ArrayList<AuthorSubmissionTable>]: returns the authors of Submission.
     */
    public ArrayList<AuthorSubmissionTable> getSubmissionAuthors() {
        return submissionAuthors;
    }

    /**
     * Effect: Sets the authors of a submission.
     * @param submissionAuthors: new value for submission submissionAuthors.
     */
    public void setSubmissionAuthors(ArrayList<AuthorSubmissionTable> submissionAuthors) {
        this.submissionAuthors = submissionAuthors;
    }

    /**
     * Effect: Return the reviewers of this submission.
     * @return [ArrayList<ReviewerTable>]: returns the reviewers of a Submission.
     */
    public ArrayList<ReviewerTable> getReviewers() {
        return reviewers;
    }

    /**
     * Effect: Sets the reviewers of a submission.
     * @param reviewers: new value for submission reviewers.
     */
    public void setReviewers(ArrayList<ReviewerTable> reviewers) {
        this.reviewers = reviewers;
    }
}

