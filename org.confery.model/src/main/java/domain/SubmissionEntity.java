package domain;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Name:    SubmissionEntity
 * Effect:  Class for the database table SubmissionEntity
 * Date:    9/4/2017
 * Tested:  True
 * @author  Simion George-Vlad
 * @version 1.0
 */

@Entity
@Table(name = "SUBMISSION")
@SuppressWarnings("unused")
public class SubmissionEntity implements Idable<Integer> {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_SUBMISSION")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_EDITION")
    private EditionEntity editionSubmission;

    @Column(name = "NAME")
    private String name;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "ABSTRACT_URL")
    private String abstractUrl;

    @Column(name = "FULL_PAPER_URL")
    private String fullPaperUrl;

    @Column(name = "IS_PAID")
    private Boolean isPaid;

    @OneToMany(mappedBy = "submissionTag", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<SubmissionTagEntity> submissionTags;

    @OneToMany(mappedBy = "submissionTopic", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<SubmissionTopicEntity> submissionTopics;

    @OneToMany(mappedBy = "submissionAuthor", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<AuthorSubmissionEntity> submissionAuthors;

    @OneToMany(mappedBy = "submissionReview", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ReviewerEntity> reviewers;

    /**
     * Empty Constructor
     */
    public SubmissionEntity(){}

    public SubmissionEntity(String name, String status, String abstractUrl, String fullPaperUrl) {
        this.name = name;
        this.status = status;
        this.abstractUrl = abstractUrl;
        this.fullPaperUrl = fullPaperUrl;
        this.isPaid = false;
    }

    /**
     * Effect: Return the id of this submission.
     * @return [Integer]: returns the id of SubmissionEntity.
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
     * @return [Integer]: returns the id of SubmissionEntity.
     */
    public EditionEntity getEdition() {
        return editionSubmission;
    }

    /**
     * Effect: Sets the id of the submission's conference.
     * @param edition: new value for conference id.
     */
    public void setEdition(EditionEntity edition) {
        this.editionSubmission = edition;
    }

    /**
     * Effect: Return the name of this submission.
     * @return [String]: returns the name of SubmissionEntity.
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
     * @return [String]: returns the status of SubmissionEntity.
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
     * @return [String]: returns the abstractUrl of SubmissionEntity.
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
     * @return [String]: returns the fullPaperUrl of SubmissionEntity.
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
     * @return [Boolean]: returns the paid status of a SubmissionEntity.
     */
    public Boolean isPaid() {
        return isPaid;
    }

    /**
     * Effect: Sets the paid status of a submission.
     * @param paid: new value for submission isPaid.
     */
    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    /**
     * Effect: Return the tags of this submission.
     * @return [ArrayList<SubmissionTagEntity>]: returns the tags of a SubmissionEntity.
     */
    public Set<SubmissionTagEntity> getSubmissionTags() {
        return submissionTags;
    }

    /**
     * Effect: Sets the tags of a submission.
     * @param submissionTags: new value for submission tags.
     */
    public void setSubmissionTags(Set<SubmissionTagEntity> submissionTags) {
        this.submissionTags = submissionTags;
    }

    /**
     * Effect: Return the topics of this submission.
     * @return [ArrayList<SubmissionTopicEntity>]: returns the topics of a SubmissionEntity.
     */
    public Set<SubmissionTopicEntity> getSubmissionTopic() {
        return submissionTopics;
    }

    /**
     * Effect: Sets the topics of a submission.
     * @param submissionTopic: new value for submission topics.
     */
    public void setSubmissionTopic(Set<SubmissionTopicEntity> submissionTopic) {
        this.submissionTopics = submissionTopic;
    }

    /**
     * Effect: Return the authors of this submission.
     * @return [ArrayList<AuthorSubmissionEntity>]: returns the authors of SubmissionEntity.
     */
    public Set<AuthorSubmissionEntity> getSubmissionAuthors() {
        return submissionAuthors;
    }

    /**
     * Effect: Sets the authors of a submission.
     * @param submissionAuthors: new value for submission submissionAuthors.
     */
    public void setSubmissionAuthors(Set<AuthorSubmissionEntity> submissionAuthors) {
        this.submissionAuthors = submissionAuthors;
    }

    /**
     * Effect: Return the reviewers of this submission.
     * @return [ArrayList<ReviewerEntity>]: returns the reviewers of a SubmissionEntity.
     */
    public Set<ReviewerEntity> getReviewers() {
        return reviewers;
    }

    /**
     * Effect: Sets the reviewers of a submission.
     * @param reviewers: new value for submission reviewers.
     */
    public void setReviewers(Set<ReviewerEntity> reviewers) {
        this.reviewers = reviewers;
    }
}

