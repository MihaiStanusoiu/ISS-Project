
package domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Tested: True
 * @author Tanasie Luiza Maria & Alexandru Stoica
 * @version 1.1
 */

@Entity
@Table(name = "TAG")
@SuppressWarnings("unused")
public class TagEntity implements Idable<Integer> {

    @Id
    @GeneratedValue
    @Column(name = "ID_TAG")
    private Integer id;

    @Column(name = "WORD")
    private String word;

    @OneToMany(targetEntity = SubmissionTagEntity.class, fetch = FetchType.EAGER,
            mappedBy="tag", cascade = CascadeType.ALL)
    private Set<SubmissionTagEntity> submissionTags;

    private static final Integer DEFAULT_ID = 0;
    private static final String DEFAULT_WORD = "";

    /**
     * @apiNote Don't use this constructor [it's for testing only]
     */
    @Deprecated
    public TagEntity() {
        this(DEFAULT_WORD);
    }

    public TagEntity(String word) {
        this(DEFAULT_ID, word);
    }

    public TagEntity(Integer id, String word) {
        this.id = id;
        this.word = word;
        submissionTags = new HashSet<>();
    }

    /**
     * Effect: Return the id of tag.
     * @return [Integer]: returns the id of tag.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Effect: Sets the id of tag.
     * @param id [Integer]: new value for id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Effect: Return the word of the tag.
     * @return [String]: returns the word of the tag.
     */
    public String getWord() {
        return word;
    }

    /**
     * Effect: Sets the word of the tag.
     * @param word [Integer]: new value for the word
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * Effect: Return the submission tags of the tag.
     * @return [ArrayList<SubmissionTagEntity>]: returns the submission tags of the tag.
     */
    public Set<SubmissionTagEntity> getSubmissionTags() {
        return submissionTags;
    }

    /**
     * Effect: Sets the submission tags of the tag.
     * @param submissionTags [ArrayList<SubmissionTagEntity>]: new value for the submission tags
     */
    public void setSubmissionTags(Set<SubmissionTagEntity> submissionTags) {
        this.submissionTags = submissionTags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagEntity tagEntity = (TagEntity) o;
        return id.equals(tagEntity.id) && (word != null ? word.equals(tagEntity.word) :
                tagEntity.word == null);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (word != null ? word.hashCode() : 0);
        return result;
    }
}
