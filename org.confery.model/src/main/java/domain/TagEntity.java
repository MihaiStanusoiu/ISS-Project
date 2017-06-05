
package domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Tested: True
 *
 * @author Tanasie Luiza Maria & Alexandru Stoica
 * @version 1.1
 */

@Entity
@Table(name = "TAG")
@SuppressWarnings("unused")
public class TagEntity implements Idable<Integer> {


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_TAG")
    private Integer id;

    @Column(name = "WORD")
    private String word;

    @OneToMany(targetEntity = SubmissionTagEntity.class, fetch = FetchType.EAGER,
            mappedBy = "tag", cascade = CascadeType.ALL)
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
     *
     * @return [Integer]: returns the id of tag.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Effect: Sets the id of tag.
     *
     * @param id [Integer]: new value for id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Effect: Return the word of the tag.
     *
     * @return [String]: returns the word of the tag.
     */
    public String getWord() {
        return word;
    }

    /**
     * Effect: Return the submission tags of the tag.
     *
     * @return [ArrayList<SubmissionTagEntity>]: returns the submission tags of the tag.
     */
    public Set<SubmissionTagEntity> getSubmissionTags() {
        return submissionTags;
    }

    /**
     * Return all the submissions in the system that have this tag.
     *
     * @return Returns all the submissions in the system that have this tag.
     */
    public List<SubmissionEntity> getSubmissions() {
        return submissionTags.stream()
                .map(SubmissionTagEntity::getSubmission)
                .collect(Collectors.toList());
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
