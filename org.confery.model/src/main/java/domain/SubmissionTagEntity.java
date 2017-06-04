
package domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Tested: True
 *
 * @author Tanasie Luiza Maria & Alexandru Stoica
 * @version 1.1
 */

@Entity
@Table(name = "SUBMISSION_TAG")
@SuppressWarnings("unused")
public class SubmissionTagEntity implements Idable<Integer> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_SUBMISSION_TAG")
    private Integer id;

    @ManyToOne(targetEntity = SubmissionEntity.class)
    @JoinColumn(name = "ID_SUBMISSION", updatable = false)
    private SubmissionEntity submission;

    @ManyToOne(targetEntity = TagEntity.class)
    @JoinColumn(name = "ID_TAG", updatable = false)
    private TagEntity tag;

    private static final Integer DEFAULT_ID = 0;

    /**
     * @apiNote Don't use this constructor [it's for testing only]
     */
    @Deprecated
    public SubmissionTagEntity() {
        this(DEFAULT_ID, null, null);
    }

    public SubmissionTagEntity(SubmissionEntity submission, TagEntity tag) {
        this(DEFAULT_ID, submission, tag);
    }

    @SuppressWarnings("all")
    public SubmissionTagEntity(Integer id, SubmissionEntity submission, TagEntity tag) {
        this.id = id;
        this.submission = submission;
        this.tag = tag;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Effect: Returns the submission of a SubmissionTagEntity.
     *
     * @return [SubmissionEntity]: returns the submission.
     */
    public SubmissionEntity getSubmission() {
        return submission;
    }

    /**
     * Effect: Returns the tag of a SubmissionTagEntity.
     *
     * @return [TagEntity]: returns the tag.
     */
    public TagEntity getTag() {
        return tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubmissionTagEntity that = (SubmissionTagEntity) o;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
