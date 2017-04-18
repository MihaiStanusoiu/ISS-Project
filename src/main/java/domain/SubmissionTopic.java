package domain;

import javax.persistence.*;
/**
 * Name:         SubmissionTopic
 * Effect:       Class for domain SubmissionTopic table
 * Date:         4/8/2017
 * Tested:       False
 *
 * @author      {Teodorescu Vlad}
 * @version     1.0
 */

@Entity
@Table(name = "SubmissionTopic")
@SuppressWarnings("unused")
public class SubmissionTopic {

    @EmbeddedId
    private SubmissionTopicItems pkId;

    @ManyToOne
    @JoinColumn(name = "id_topic")
    private Topic topicTable;

    @ManyToOne
    @JoinColumn(name = "id_submission")
    private Submission submission;

    public SubmissionTopic() { }

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
     * @return Topic : returns topicTable.
     */
    public Topic getTopicTable() {
        return topicTable;
    }

    /**
     * Effect: Sets the topicTable to the given value
     * @param topicTable: new value for topicTable
     */
    public void setTopicTable(Topic topicTable) {
        this.topicTable = topicTable;
    }


    /**
     * Effect: Getter for the submission created by join.
     * @return Submission : returns submission.
     */
    public Submission getSubmission() {
        return submission;
    }

    /**
     * Effect: Sets the submission to the given value
     * @param submission: new value for submission
     */
    public void setSubmission(Submission submission) {
        this.submission = submission;
    }

}
