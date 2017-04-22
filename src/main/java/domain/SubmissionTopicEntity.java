package domain;

import javax.persistence.*;
/**
 * Name:         SubmissionTopicEntity
 * Effect:       Class for domain SubmissionTopicEntity table
 * Date:         4/8/2017
 * Tested:       False
 *
 * @author      {Teodorescu Vlad}
 * @version     1.0
 */

@Entity
@Table(name = "SUBMISSION_TOPIC")
@SuppressWarnings("unused")
public class SubmissionTopicEntity {

    @EmbeddedId
    private SubmissionTopicItems pkId;

    @ManyToOne
    @JoinColumn(name = "ID_TOPIC")
    private TopicEntity topicTable;

    @ManyToOne
    @JoinColumn(name = "ID_SUBMISSION")
    private SubmissionEntity submission;

    public SubmissionTopicEntity() { }

    /**
     * Effect: Getter for the primary key.
     * @return SubmissionTopicItems : returns pkId.
     */
    public SubmissionTopicItems getPkId() {
        return pkId;
    }

    /**
     * Effect: Sets the pkId to the given value
     * @param pkId: new value for pkId
     */
    public void setPkId(SubmissionTopicItems pkId) {
        this.pkId = pkId;
    }

    /**
     * Effect: Getter for the topic created by join.
     * @return TopicEntity : returns topicTable.
     */
    public TopicEntity getTopicTable() {
        return topicTable;
    }

    /**
     * Effect: Sets the topicTable to the given value
     * @param topicTable: new value for topicTable
     */
    public void setTopicTable(TopicEntity topicTable) {
        this.topicTable = topicTable;
    }


    /**
     * Effect: Getter for the submission created by join.
     * @return SubmissionEntity : returns submission.
     */
    public SubmissionEntity getSubmission() {
        return submission;
    }

    /**
     * Effect: Sets the submission to the given value
     * @param submission: new value for submission
     */
    public void setSubmission(SubmissionEntity submission) {
        this.submission = submission;
    }

}
