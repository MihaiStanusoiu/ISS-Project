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
public class TopicTable
{
    @Id @GeneratedValue
    @Column(name = "id_topic")
    private Integer id_topic;

    @Column(name = "word")
    private String word;

    private ArrayList<SubmissionTopicTable> topicSubmissionTopics = new ArrayList<>();

    public TopicTable() {}

    /**
     * Effect: Getter for the id_topic of the topic.
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
