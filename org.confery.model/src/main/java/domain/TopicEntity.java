
package domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Tested: True
 * @author Teodorescu Vlad & Alexandru Stoica
 * @version 1.1
 */

@Entity
@Table(name = "TOPIC")
@SuppressWarnings("unused")
public class TopicEntity implements Idable<Integer>{

    @Id
    @GeneratedValue
    @Column(name = "ID_TOPIC")
    private Integer idTopic;

    @Column(name = "WORD")
    private String word;

    @OneToMany(targetEntity = SubmissionTopicEntity.class, fetch = FetchType.EAGER,
            mappedBy="topic", cascade = CascadeType.ALL)
    private Set<SubmissionTopicEntity> submissionTopics;

    private static final Integer DEFAULT_ID = 0;
    private static final String DEFAULT_WORD = "";

    /**
     * @apiNote Don't use this constructor [it's for testing only]
     */
    @Deprecated
    public TopicEntity() {
        this(DEFAULT_WORD);
    }

    public TopicEntity(String word) {
        this(DEFAULT_ID, word);
    }

    public TopicEntity(Integer id, String word) {
        this.idTopic = id;
        this.word = word;
        submissionTopics = new HashSet<>();
    }

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

    public Set<SubmissionTopicEntity> getSubmissionTopics() {
        return submissionTopics;
    }

    /**
     * Effect: Sets the topicSubmissionTopics to the given value
     * @param submissionTopics: new value for topicSubmissionTopics
     */
    public void setSubmissionTopics(Set<SubmissionTopicEntity> submissionTopics) {
        this.submissionTopics = submissionTopics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicEntity that = (TopicEntity) o;
        return idTopic.equals(that.idTopic) &&
                (word != null ? word.equals(that.word) : that.word == null);
    }

    @Override
    public int hashCode() {
        int result = idTopic.hashCode();
        result = 31 * result + (word != null ? word.hashCode() : 0);
        return result;
    }
}
