package domain;

import javax.persistence.*;
import java.util.ArrayList;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Name:    Submission
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
public class Submission {

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
    private ArrayList<SubmissionTag> submissionTags;

    @OneToMany(mappedBy = "idSubmission")
    private ArrayList<SubmissionTopic> submissionTopic;

    @OneToMany(mappedBy = "idSubmission")
    private ArrayList<AuthorSubmission> submissionAuthors;

    @OneToMany(mappedBy = "idSubmission")
    private ArrayList<Reviewer> reviewers;

    /**
     * Empty Constructor
     */
    public Submission(){}

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
     * @return [ArrayList<SubmissionTag>]: returns the tags of a Submission.
     */
    public ArrayList<SubmissionTag> getSubmissionTags() {
        return submissionTags;
    }

    /**
     * Effect: Sets the tags of a submission.
     * @param submissionTags: new value for submission tags.
     */
    public void setSubmissionTags(ArrayList<SubmissionTag> submissionTags) {
        this.submissionTags = submissionTags;
    }

    /**
     * Effect: Return the topics of this submission.
     * @return [ArrayList<SubmissionTopic>]: returns the topics of a Submission.
     */
    public ArrayList<SubmissionTopic> getSubmissionTopic() {
        return submissionTopic;
    }

    /**
     * Effect: Sets the topics of a submission.
     * @param submissionTopic: new value for submission topics.
     */
    public void setSubmissionTopic(ArrayList<SubmissionTopic> submissionTopic) {
        this.submissionTopic = submissionTopic;
    }

    /**
     * Effect: Return the authors of this submission.
     * @return [ArrayList<AuthorSubmission>]: returns the authors of Submission.
     */
    public ArrayList<AuthorSubmission> getSubmissionAuthors() {
        return submissionAuthors;
    }

    /**
     * Effect: Sets the authors of a submission.
     * @param submissionAuthors: new value for submission submissionAuthors.
     */
    public void setSubmissionAuthors(ArrayList<AuthorSubmission> submissionAuthors) {
        this.submissionAuthors = submissionAuthors;
    }

    /**
     * Effect: Return the reviewers of this submission.
     * @return [ArrayList<Reviewer>]: returns the reviewers of a Submission.
     */
    public ArrayList<Reviewer> getReviewers() {
        return reviewers;
    }

    /**
     * Effect: Sets the reviewers of a submission.
     * @param reviewers: new value for submission reviewers.
     */
    public void setReviewers(ArrayList<Reviewer> reviewers) {
        this.reviewers = reviewers;
    }
}

