package domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Name:         SubmissionTagEntity
 * Effect:       Corresponding class for the paper tags and submission many-to-many relationship.
 * Date:         08.04.2017
 * Tested:       True
 * @author       Tanasie Luiza Maria
 * @version      1.0
 */

@Entity
@Table(name = "SUBMISSION_TAG")
@SuppressWarnings("unused")
public class SubmissionTagEntity implements Idable<Integer>  {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_SUBMISSION_TAG")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_SUBMISSION")
    private SubmissionEntity submissionTag;

    @ManyToOne
    @JoinColumn(name = "ID_TAG")
    private TagEntity tag;

    public SubmissionTagEntity() { }

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
     * @return [SubmissionEntity]: returns the submissionTag.
     */
    public SubmissionEntity getSubmissionTag() {
        return submissionTag;
    }

    /**
     * Effect: Sets the submission of a SubmissionTagEntity.
     * @param submissionTag: new value for submissionTag.
     */
    public void setSubmissionTag(SubmissionEntity submissionTag) {
        this.submissionTag = submissionTag;
    }

    /**
     * Effect: Returns the tag of a SubmissionTagEntity.
     * @return [TagEntity]: returns the tag.
     */
    public TagEntity getTag() {
        return tag;
    }

    /**
     * Effect: Sets the tag of a SubmissionTagEntity.
     * @param tag: new value for tag.
     */
    public void setTag(TagEntity tag) {
        this.tag = tag;
    }
}
