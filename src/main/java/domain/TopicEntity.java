package domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Name:         TopicEntity
 * Effect:       Class for domain TopicEntity table
 * Date:         4/8/2017
 * Tested:       False
 *
 * @author      {Teodorescu Vlad}
 * @version     1.0
 */

@Entity
@Table(name = "TOPIC")
@SuppressWarnings("unused")
public class TopicEntity implements Idable<Integer>{

    @Id @GeneratedValue
    @Column(name = "ID_TOPIC")
    private Integer idTopic;

    @Column(name = "WORD")
    private String word;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "submission")
    private Set<SubmissionTopicEntity> topicSubmissionTopics = new HashSet<>();

    public TopicEntity() { }

    /**
     * Effect: Getter for the id_topic of the topic.
     * @return Integer : returns idTopic.
     */
    public Integer getId() {
        return idTopic;
    }

    /**
     * Effect: Sets the id_topic to the given value
     * @param idTopic: new value for idTopic
     */
    public void setId(Integer idTopic) {
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
     * @return ArrayList<SubmissionTopicEntity> : returns topicSubmissionTopics.
     */

    public Set<SubmissionTopicEntity> getTopicSubmissionTopics() {
        return topicSubmissionTopics;
    }

    /**
     * Effect: Sets the topicSubmissionTopics to the given value
     * @param topicSubmissionTopics: new value for topicSubmissionTopics
     */
    public void setTopicSubmissionTopics(Set<SubmissionTopicEntity> topicSubmissionTopics) {
        this.topicSubmissionTopics = topicSubmissionTopics;
    }

}
