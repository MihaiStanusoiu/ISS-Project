package database_domain;

import javax.persistence.*;
/**
 * Name:         SubmissionTopicTableItems
 * Effect:       Class for database_domain SubmissionTopic table composite PK
 * Date:         4/8/2017
 * Tested:       False
 *
 * @author      {Teodorescu Vlad}
 * @version     1.0
 */

@Embeddable
public class SubmissionTopicTableItems
{
    @Column(name = "id_submission")
    private Integer id_submission;

    @Column(name = "id_topic")
    private Integer id_topic;

    public SubmissionTopicTableItems() {
    }

    /**
     * Effect: Getter for the id of the topic.
     * @return Integer : returns id_submission.
     */
    public Integer getId_submission() {
        return id_submission;
    }

    /**
     * Effect: Sets the id_submission to the given value
     * @param id_submission: new value for id_submission
     */
    public void setId_submission(Integer id_submission) {
        this.id_submission = id_submission;
    }

    /**
     * Effect: Getter for the id of the topic.
     * @return Integer : returns id_topic.
     */
    public Integer getId_topic() {
        return id_topic;
    }

    /**
     * Effect: Sets the id_topic to the given value
     * @param id_topic: new value for id_topic
     */
    public void setId_topic(Integer id_topic) {
        this.id_topic = id_topic;
    }

}
