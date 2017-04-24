package domain;

import javax.persistence.*;

/**
 * Name:         SubmissionTagEntity
 * Effect:       Corresponding class for the paper tags and submission many-to-many relationship.
 * Date:         08.04.2017
 * Tested:       False
 * @author       Tanasie Luiza Maria
 * @version      1.0
 */

@Entity
@Table(name = "SUBMISSION_TAG")
@SuppressWarnings("unused")
public class SubmissionTagEntity implements Idable<SubmissionTagId>  {


    SubmissionTagId id = new SubmissionTagId();

    private SubmissionEntity submission;

    private TagEntity tag;

    public SubmissionTagEntity() { }

    public SubmissionTagEntity(SubmissionEntity idSubmission, TagEntity idTag) {
        this.submission = idSubmission;
        this.tag = idTag;
    }

    /**
     * Effect: Return the id of a submission-tag.
     * @return [SubmissionTableId] : returns the id of a submission-tag.
     */
    @Override
    @EmbeddedId
    public SubmissionTagId getId() { return id; }


    /**
     * Effect: Sets the id of a submission-tag.
     * @param id [SubmissionTableId]: new value for id
     */
    @Override
    public void setId(SubmissionTagId id) { this.id = id; }


    /**
     * Effect: Return the submission of a submission-tag.
     * @return [SubmissionEntity] : returns the  submission of a submission-tag.
     */
    @ManyToOne
    @JoinColumn(name = "ID_SUBMISSION")
    public SubmissionEntity getSubmission() {
        return submission;
    }

    /**
     * Effect: Sets the submission of a submission-tag.
     * @param submission submission: new value for submission
     */
    public void setSubmission(SubmissionEntity submission) {
        this.submission = submission;
    }

    /**
     * Effect: Return the tag of a submission-tag.
     * @return [TagEntity] : returns the tag.
     */
    @ManyToOne
    @JoinColumn(name = "ID_TAG")
    public TagEntity getTag() {
        return tag;
    }

    /**
     * Effect: Sets the tag of a submission-tag.
     * @param tag [TagEntity]: new value for tag
     */
    public void setTag(TagEntity tag) {
        this.tag = tag;
    }

}
