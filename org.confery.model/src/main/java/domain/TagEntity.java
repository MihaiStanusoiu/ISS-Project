package domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Name:         TagEntity
 * Effect:       Corresponding class for the paper tags table in the database.
 * Date:         08.04.2017
 * Tested:       True
 * @author       Tanasie Luiza Maria
 * @version      1.0
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy="tag", cascade = CascadeType.ALL)
    private Set<SubmissionTagEntity> submissionTags;

    public TagEntity() { }

    public TagEntity(String word) {
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

}