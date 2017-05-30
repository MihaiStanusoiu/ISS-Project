
package domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Tested: True
 *
 * @author Simion George-Vlad & Alexandru Stoica
 * @version 1.1
 */

@Entity
@Table(name = "SUBMISSION")
@SuppressWarnings("unused")
public class SubmissionEntity implements Idable<Integer> {


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_SUBMISSION")
    private Integer id;

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

    @ManyToOne(targetEntity = EditionEntity.class)
    @JoinColumn(name = "ID_EDITION")
    private EditionEntity edition;

    @OneToMany(targetEntity = SubmissionTagEntity.class, mappedBy = "submission",
            fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<SubmissionTagEntity> submissionTags;

    @OneToMany(targetEntity = SubmissionTopicEntity.class, mappedBy = "submission",
            fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<SubmissionTopicEntity> submissionTopics;

    @OneToMany(targetEntity = AuthorSubmissionEntity.class, mappedBy = "submission",
            fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<AuthorSubmissionEntity> submissionAuthors;

    @OneToMany(targetEntity = ReviewerEntity.class, mappedBy = "submission",
            fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ReviewerEntity> reviewers;

    private static final Integer DEFAULT_ID = 0;
    private static final String DEFAULT_URL = "";
    private static final Boolean DEFAULT_FLAG_PAID = Boolean.FALSE;

    public SubmissionEntity(String name, String status) {
        this(name, status, DEFAULT_URL, DEFAULT_URL);
    }

    /**
     * @apiNote Don't use this constructor [it's for testing only]
     */
    @Deprecated
    public SubmissionEntity() {
        this(DEFAULT_ID, DEFAULT_URL, DEFAULT_URL, DEFAULT_URL, DEFAULT_URL, DEFAULT_FLAG_PAID);
    }

    public SubmissionEntity(String name, String status, String abstractUrl, String fullPaperUrl) {
        this(DEFAULT_ID, name, status, abstractUrl, fullPaperUrl, DEFAULT_FLAG_PAID);
    }

    public SubmissionEntity(String name, String abstractUrl, String fullPaperUrl) {
        this(name, DEFAULT_URL, abstractUrl, fullPaperUrl);
    }

    public SubmissionEntity(Integer id, String name, String status,
                            String abstractUrl, String fullPaperUrl, Boolean isPaid) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.abstractUrl = abstractUrl;
        this.fullPaperUrl = fullPaperUrl;
        this.isPaid = isPaid;
        this.submissionAuthors = new HashSet<>();
        this.submissionTags = new HashSet<>();
        this.submissionTopics = new HashSet<>();
        this.reviewers = new HashSet<>();
    }

    /**
     * Effect: Return the id of this submission.
     *
     * @return [Integer]: returns the id of SubmissionEntity.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Effect: Sets the id of a submission.
     *
     * @param id: new value for submission id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Effect: Return the id of this submission.
     *
     * @return [Integer]: returns the id of SubmissionEntity.
     */
    public EditionEntity getEdition() {
        return edition;
    }

    /**
     * Effect: Sets the id of the submission's conference.
     *
     * @param edition: new value for conference id.
     */
    public void setEdition(EditionEntity edition) {
        this.edition = edition;
    }

    /**
     * Effect: Return the name of this submission.
     *
     * @return [String]: returns the name of SubmissionEntity.
     */
    public String getName() {
        return name;
    }

    /**
     * Effect: Sets the name of a submission.
     *
     * @param name: new value for submission name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Effect: Return the status of this submission.
     *
     * @return [String]: returns the status of SubmissionEntity.
     */
    public String getStatus() {
        return calculateStatus().toString();
    }

    /**
     * Effect: Return the abstract url of this submission.
     *
     * @return [String]: returns the abstractUrl of SubmissionEntity.
     */
    public String getAbstractUrl() {
        return abstractUrl;
    }

    /**
     * Effect: Sets the abstract url of a submission.
     *
     * @param abstractUrl: new value for submission abstractUrl.
     */
    public void setAbstractUrl(String abstractUrl) {
        this.abstractUrl = abstractUrl;
    }

    /**
     * Effect: Return the full paper url of this submission.
     *
     * @return [String]: returns the fullPaperUrl of SubmissionEntity.
     */
    public String getFullPaperUrl() {
        return fullPaperUrl;
    }

    /**
     * Effect: Sets the full paper url of a submission.
     *
     * @param fullPaperUrl: new value for submission fullPaperUrl.
     */
    public void setFullPaperUrl(String fullPaperUrl) {
        this.fullPaperUrl = fullPaperUrl;
    }

    /**
     * Effect: Return the paid status of this submission.
     *
     * @return [Boolean]: returns the paid status of a SubmissionEntity.
     */
    public Boolean isPaid() {
        return isPaid;
    }

    /**
     * Effect: Sets the paid status of a submission.
     *
     * @param paid: new value for submission isPaid.
     */
    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    /**
     * Effect: Return the tags of this submission.
     *
     * @return [ArrayList<SubmissionTagEntity>]: returns the tags of a SubmissionEntity.
     */
    public Set<SubmissionTagEntity> getSubmissionTags() {
        return submissionTags;
    }

    /**
     * Effect: Sets the tags of a submission.
     *
     * @param submissionTags: new value for submission tags.
     */
    public void setSubmissionTags(Set<SubmissionTagEntity> submissionTags) {
        this.submissionTags = submissionTags;
    }

    /**
     * Effect: Return the topics of this submission.
     *
     * @return [ArrayList<SubmissionTopicEntity>]: returns the topics of a SubmissionEntity.
     */
    public Set<SubmissionTopicEntity> getSubmissionTopic() {
        return submissionTopics;
    }

    /**
     * Effect: Sets the topics of a submission.
     *
     * @param submissionTopic: new value for submission topics.
     */
    public void setSubmissionTopic(Set<SubmissionTopicEntity> submissionTopic) {
        this.submissionTopics = submissionTopic;
    }

    /**
     * Effect: Return the authors of this submission.
     *
     * @return [ArrayList<AuthorSubmissionEntity>]: returns the authors of SubmissionEntity.
     */
    public Set<AuthorSubmissionEntity> getSubmissionAuthors() {
        return submissionAuthors;
    }

    /**
     * Effect: Sets the authors of a submission.
     *
     * @param submissionAuthors: new value for submission submissionAuthors.
     */
    public void setSubmissionAuthors(Set<AuthorSubmissionEntity> submissionAuthors) {
        this.submissionAuthors = submissionAuthors;
    }

    /**
     * Effect: Return the reviewers of this submission.
     *
     * @return [ArrayList<ReviewerEntity>]: returns the reviewers of a SubmissionEntity.
     */
    public Set<ReviewerEntity> getReviewerEntities() {
        return reviewers;
    }

    /**
     * Effect: Sets the reviewers of a submission.
     *
     * @param reviewers: new value for submission reviewers.
     */
    public void setReviewers(Set<ReviewerEntity> reviewers) {
        this.reviewers = reviewers;
    }

    /**
     * Returns all the reviews for this submission.
     *
     * @return All the reviews for this submission.
     * @implNote This function will return all the reviews
     * (even the ones who are not allowed by the chair to review the submission)
     */
    public List<UserEntity> getReviewers() {
        return reviewers.stream()
                .map(reviewer -> reviewer.getMember().getUser())
                .collect(Collectors.toList());
    }

    /**
     * Returns all the authors of the submission (including the owner)
     *
     * @return All the authors of the submission.
     */
    public List<UserEntity> getAuthors() {
        return submissionAuthors.stream()
                .map(AuthorSubmissionEntity::getAuthor)
                .collect(Collectors.toList());
    }

    /**
     * Returns the owner of the submission.
     *
     * @return The owner of the submission
     * @implNote If this function returns null,
     * check the logic of the application.
     */
    public UserEntity getOwner() {
        return submissionAuthors.stream()
                .filter(author -> author.getOwner().equals(Boolean.TRUE))
                .map(AuthorSubmissionEntity::getAuthor)
                .findFirst().orElse(null);
    }

    /**
     * Returns all the topics for this submission.
     *
     * @return All the topics for this submission
     */
    public List<TopicEntity> getTopics() {
        return submissionTopics.stream()
                .map(SubmissionTopicEntity::getTopic)
                .collect(Collectors.toList());
    }

    /**
     * Returns all the tags for this submission.
     *
     * @return All the tags for this submission
     */
    public List<TagEntity> getTags() {
        return submissionTags.stream()
                .map(SubmissionTagEntity::getTag)
                .collect(Collectors.toList());
    }

    /**
     * Returns all the binding reviewers.
     *
     * @return All the binding reviewers for this submission.
     * @apiNote A reviewer is in BINDING state when
     * it's not assigned to review the submission.
     */
    public List<UserEntity> getBindingReviewers() {
        return reviewers.stream()
                .filter(reviewer -> reviewer.getResponse()
                        .equals(ResponseEntityType.NOT_ASSIGNED.toString()))
                .map(reviewer -> reviewer.getMember().getUser())
                .collect(Collectors.toList());
    }

    /**
     * Returns all the reviewers that are allowed to review the submission.
     *
     * @return All the reviewers that are allowed to review the submission
     */
    public List<UserEntity> getAllowedReviewers() {
        return reviewers.stream()
                .filter(reviewer -> reviewer.getResponse()
                        .equals(ResponseEntityType.ALLOWED_TO_REVIEW.toString()))
                .map(reviewer -> reviewer.getMember().getUser())
                .collect(Collectors.toList());
    }

    /**
     * Returns all the reviewers that are rejected by the chair
     * and by definition are not allowed to review the submission
     *
     * @return The reviewers that are not allowed to review the submission.
     */
    public List<UserEntity> getRejectedReviewers() {
        return reviewers.stream()
                .filter(reviewer -> reviewer.getResponse()
                        .equals(ResponseEntityType.NOT_ALLOWED_TO_REVIEW.toString()))
                .map(reviewer -> reviewer.getMember().getUser())
                .collect(Collectors.toList());
    }

    /**
     * Returns the current status of the submission
     *
     * @return The current status of the submission.
     */
    private StatusEntityType calculateStatus() {
        return reviewers.stream()
                .anyMatch(reviewerEntity -> reviewerEntity.getStatus()
                        .equals(StatusEntityType.NOT_REVIEWED.toString())) ?
                StatusEntityType.NOT_REVIEWED : StatusEntityType.REVIEWED;
    }

    /**
     * Returns the result of the submission,
     * by checking all the qualifiers from the allowed reviewers.
     *
     * @return The result of the submission.
     */
    private ResultTypeEntity calculateResult() {
        return getAllowedReviewers().size() == 0 ? ResultTypeEntity.CONTRADICTORY : reviewers.stream()
                .filter(reviewer -> reviewer.getResponse().equals(ResponseEntityType.ALLOWED_TO_REVIEW.toString()))
                .map(reviewer -> QualifierTypeEntity.fromString(reviewer.getQualifier()).getValue())
                .reduce(0, (accumulator, item) -> accumulator + item) / getAllowedReviewers().size() == 0 ?
                ResultTypeEntity.CONTRADICTORY : ResultTypeEntity.ACCEPTED;
    }

    /**
     * Returns the result of the submission, aka this function will tell you
     * if the submission is ACCEPTED or REJECTED or CONTRADICTORY. [based on the allowed reviewers qualifiers]
     *
     * @return The result of the submission.
     */
    public ResultTypeEntity getResult() {
        return reviewers.stream().noneMatch(reviewerEntity ->
                reviewerEntity.getQualifier().equals(QualifierTypeEntity.AGREE.toString()) ||
                        reviewerEntity.getQualifier().equals(QualifierTypeEntity.STRONG_AGREE.toString())) ?
                ResultTypeEntity.REJECTED : calculateResult();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubmissionEntity that = (SubmissionEntity) o;
        return id.equals(that.id) && (name != null ? name.equals(that.name) : that.name == null) &&
                (status != null ? status.equals(that.status) : that.status == null) &&
                (abstractUrl != null ? abstractUrl.equals(that.abstractUrl) : that.abstractUrl == null) &&
                (fullPaperUrl != null ? fullPaperUrl.equals(that.fullPaperUrl) : that.fullPaperUrl == null) &&
                (isPaid != null ? isPaid.equals(that.isPaid) : that.isPaid == null);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (abstractUrl != null ? abstractUrl.hashCode() : 0);
        result = 31 * result + (fullPaperUrl != null ? fullPaperUrl.hashCode() : 0);
        result = 31 * result + (isPaid != null ? isPaid.hashCode() : 0);
        return result;
    }
}

