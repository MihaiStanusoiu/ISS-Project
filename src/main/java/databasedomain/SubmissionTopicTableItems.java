package databasedomain;

import javax.persistence.*;
/**
 * Name:         SubmissionTopicTableItems
 * Effect:       Class for databasedomain SubmissionTopic table composite PK
 * Date:         4/8/2017
 * Tested:       False
 *
 * @author      {Teodorescu Vlad}
 * @version     1.0
 */

@Embeddable
@SuppressWarnings("unused")
public class SubmissionTopicTableItems {

    @Column(name = "id_submission")
    private Integer idSubmission;

    @Column(name = "id_topic")
    private Integer idTopic;

    public SubmissionTopicTableItems() { }

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
