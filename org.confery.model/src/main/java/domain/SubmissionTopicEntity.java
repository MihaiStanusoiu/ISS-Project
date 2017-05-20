
package domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Tested: True
 * @author Teodorescu Vlad & Alexandru Stoica
 * @version 1.1
 */

@Entity
@Table(name = "SUBMISSION_TOPIC")
@SuppressWarnings("unused")
public class SubmissionTopicEntity implements Idable<Integer>{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_SUBMISSION_TOPIC")
    private Integer id;

    @ManyToOne(targetEntity = SubmissionEntity.class)
    @JoinColumn(name = "ID_SUBMISSION")
    private SubmissionEntity submission;

    @ManyToOne(targetEntity = TopicEntity.class)
    @JoinColumn(name = "ID_TOPIC")
    private TopicEntity topic;

    private static final Integer DEFAULT_ID = 0;

    /**
     * @apiNote Don't use this constructor [it's for testing only]
     */
    @Deprecated
    public SubmissionTopicEntity() {
        this(DEFAULT_ID, null, null);
    }

    public SubmissionTopicEntity(SubmissionEntity submission, TopicEntity topic) {
        this(DEFAULT_ID, submission, topic);
    }

    @SuppressWarnings("All")
    public SubmissionTopicEntity(Integer id, SubmissionEntity submission,
                                 TopicEntity topic) {
        this.id = id;
        this.submission = submission;
        this.topic = topic;
    }

    /**
     * Effect: Return the id of a submission-topic.
     * @return [Integer] : returns the id of a submission-topic.
     */
    @Override
    public Integer getId() { return id; }

    /**
     * Effect: Sets the id of a submission-topic.
     * @param id : new value for id.
     */
    @Override
    public void setId(Integer id) { this.id = id; }

    /**
     * Effect: Returns the submission of a SubmissionTopicEntity
     * @return [SubmissionEntity]: returns the submission.
     */
    public SubmissionEntity getSubmission() {
        return submission;
    }

    /**
     * Effect: Sets the submission of a SubmissionTopicEntity.
     * @param submission: new value for submission.
     */
    public void setSubmission(SubmissionEntity submission) {
        this.submission = submission;
    }

    /**
     * Effect: Returns the topic of a SubmissionTopicEntity.
     * @return [TopicEntity]: returns the TopicEntity.
     */
    public TopicEntity getTopic() {
        return topic;
    }

    /**
     * Effect: Sets the topic of a SubmissionTopicEntity.
     * @param topic: new value for topic.
     */
    public void setTopic(TopicEntity topic) {
        this.topic = topic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubmissionTopicEntity that = (SubmissionTopicEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
