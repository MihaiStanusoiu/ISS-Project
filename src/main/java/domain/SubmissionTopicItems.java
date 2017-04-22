package domain;

import javax.persistence.*;
/**
 * Name:         SubmissionTopicItems
 * Effect:       Class for domain SubmissionTopicEntity table composite PK
 * Date:         4/8/2017
 * Tested:       False
 *
 * @author      {Teodorescu Vlad}
 * @version     1.0
 */

@Embeddable
@SuppressWarnings("unused")
public class SubmissionTopicItems {

    @Column(name = "ID_SUBMISSION")
    private Integer idSubmission;

    @Column(name = "ID_TOPIC")
    private Integer idTopic;

    public SubmissionTopicItems() { }

    /**
     * Effect: Getter for the id of the topic.
     * @return Integer : returns id_submission.
     */
    public Integer getIdSubmission() {
        return idSubmission;
    }

    /**
     * Effect: Sets the id_submission to the given value
     * @param idSubmission: new value for id_submission
     */
    public void setIdSubmission(Integer idSubmission) {
        this.idSubmission = idSubmission;
    }

    /**
     * Effect: Getter for the id of the topic.
     * @return Integer : returns id_topic.
     */
    public Integer getIdTopic() {
        return idTopic;
    }

    /**
     * Effect: Sets the id_topic to the given value
     * @param idTopic: new value for id_topic
     */
    public void setIdTopic(Integer idTopic) {
        this.idTopic = idTopic;
    }

}
