package database_domain;

import javax.persistence.*;
/**
 * Name:         SubmissionTopicTable
 * Effect:       Class for database_domain SubmissionTopic table
 * Date:         4/8/2017
 * Tested:       False
 *
 * @author      {Teodorescu Vlad}
 * @version     1.0
 */

@Entity
@Table(name = "SubmissionTopic")
public class SubmissionTopicTable
{

    @EmbeddedId
    SubmissionTopicTableItems PKid;

    @ManyToOne
    @JoinColumn(name = "id_topic")
    private TopicTable topicTable;

    @ManyToOne
    @JoinColumn(name = "id_submission")
    private SubmissionTable submissionTable;

    public SubmissionTopicTable() {
    }

    /**
     * Effect: Getter for the primary key.
     * @return SubmissionTopicTableItems : returns PKid.
     */
    public SubmissionTopicTableItems getPKid() {
        return PKid;
    }

    /**
     * Effect: Sets the PKid to the given value
     * @param PKid: new value for PKid
     */
    public void setPKid(SubmissionTopicTableItems PKid) {
        this.PKid = PKid;
    }

    /**
     * Effect: Getter for the topic created by join.
     * @return TopicTable : returns topicTable.
     */
    public TopicTable getTopicTable() {
        return topicTable;
    }

    /**
     * Effect: Sets the topicTable to the given value
     * @param topicTable: new value for topicTable
     */
    public void setTopicTable(TopicTable topicTable) {
        this.topicTable = topicTable;
    }


    /**
     * Effect: Getter for the submission created by join.
     * @return SubmissionTable : returns submissionTable.
     */
    public SubmissionTable getSubmissionTable() {
        return submissionTable;
    }

    /**
     * Effect: Sets the submissionTable to the given value
     * @param submissionTable: new value for submissionTable
     */
    public void setSubmissionTable(SubmissionTable submissionTable) {
        this.submissionTable = submissionTable;
    }
}
