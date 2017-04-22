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
public class SubmissionTagEntity  {

    @EmbeddedId
    SubmissionTagId id;

    @ManyToOne
    @JoinColumn(name = "ID_SUBMISSION")
    private SubmissionEntity idSubmission;

    @ManyToOne
    @JoinColumn(name = "ID_TAG")
    private TagEntity idTag;

    public SubmissionTagEntity() { }

    /**
     * Effect: Return the id of a submission-tag.
     * @return [SubmissionTableId] : returns the id of a submission-tag.
     */
    public SubmissionTagId getId() {
        return id;
    }

    /**
     * Effect: Sets the id of a submission-tag.
     * @param id [SubmissionTableId]: new value for id
     */
    public void setId(SubmissionTagId id) {
        this.id = id;
    }

    /**
     * Effect: Return the submission of a submission-tag.
     * @return [SubmissionEntity] : returns the  submission of a submission-tag.
     */
    public SubmissionEntity getIdSubmission() {
        return idSubmission;
    }

    /**
     * Effect: Sets the submission of a submission-tag.
     * @param idSubmission idSubmission: new value for submission
     */
    public void setIdSubmission(SubmissionEntity idSubmission) {
        this.idSubmission = idSubmission;
    }

    /**
     * Effect: Return the tag of a submission-tag.
     * @return [TagEntity] : returns the tag.
     */
    public TagEntity getIdTag() {
        return idTag;
    }

    /**
     * Effect: Sets the tag of a submission-tag.
     * @param idTag [TagEntity]: new value for tag
     */
    public void setIdTag(TagEntity idTag) {
        this.idTag = idTag;
    }
}
