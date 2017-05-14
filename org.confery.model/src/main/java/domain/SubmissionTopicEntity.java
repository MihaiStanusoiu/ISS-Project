package domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Name:         SubmissionTopicEntity
 * Effect:       Class for domain SubmissionTopicEntity table
 * Date:         4/8/2017
 * Tested:       True
 * @author      {Teodorescu Vlad}
 * @version     1.0
 */

@Entity
@Table(name = "SUBMISSION_TOPIC")
@SuppressWarnings("unused")
public class SubmissionTopicEntity implements Idable<Integer>{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_SUBMISSION_TOPIC")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_SUBMISSION")
    private SubmissionEntity submissionTopic;

    @ManyToOne
    @JoinColumn(name = "ID_TOPIC")
    private TopicEntity topic;

    public SubmissionTopicEntity() { }

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
     * @return [SubmissionEntity]: returns the submissionTopic.
     */
    public SubmissionEntity getSubmissionTopic() {
        return submissionTopic;
    }

    /**
     * Effect: Sets the submission of a SubmissionTopicEntity.
     * @param submissionTopic: new value for submissionTopic.
     */
    public void setSubmissionTopic(SubmissionEntity submissionTopic) {
        this.submissionTopic = submissionTopic;
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

}
