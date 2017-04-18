package database_domain;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Name:         TopicTable
 * Effect:       Class for database_domain Topic table
 * Date:         4/8/2017
 * Tested:       False
 *
 * @author      {Teodorescu Vlad}
 * @version     1.0
 */

@Entity
@Table(name = "Topic")
@SuppressWarnings("unused")
public class TopicTable {

    @Id @GeneratedValue
    @Column(name = "id_topic")
    private Integer idTopic;

    @Column(name = "word")
    private String word;

    private ArrayList<SubmissionTopicTable> topicSubmissionTopics = new ArrayList<>();

    public TopicTable() { }

    /**
     * Effect: Getter for the id_topic of the topic.
     * @return Integer : returns idTopic.
     */
    public Integer getIdTopic() {
        return idTopic;
    }

    /**
     * Effect: Sets the id_topic to the given value
     * @param idTopic: new value for idTopic
     */
    public void setIdTopic(Integer idTopic) {
        this.idTopic = idTopic;
    }

    /**
     * Effect: Getter for the content of the topic.
     * @return String : returns word.
     */
    public String getWord() {
        return word;
    }

    /**
     * Effect: Sets the word to the given value
     * @param word: new value for word
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * Effect: Getter for the elements of the relationship of the topic.
     * @return ArrayList<SubmissionTopicTable> : returns topicSubmissionTopics.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id_topic")
    public ArrayList<SubmissionTopicTable> getTopicSubmissionTopics() {
        return topicSubmissionTopics;
    }

    /**
     * Effect: Sets the topicSubmissionTopics to the given value
     * @param topicSubmissionTopics: new value for topicSubmissionTopics
     */
    public void setTopicSubmissionTopics(ArrayList<SubmissionTopicTable> topicSubmissionTopics) {
        this.topicSubmissionTopics = topicSubmissionTopics;
    }

}
