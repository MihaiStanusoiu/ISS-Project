package domain;

import javax.persistence.*;

/**
 * Name:         SubmissionTag
 * Effect:       Corresponding class for the paper tags and submission many-to-many relationship.
 * Date:         08.04.2017
 * Tested:       False
 * @author       Tanasie Luiza Maria
 * @version      1.0
 */

@Entity
@Table(name = "SubmissionTag")
@SuppressWarnings("unused")
public class SubmissionTag {

    @EmbeddedId
    SubmissionTagId id;

    @ManyToOne
    @JoinColumn(name = "id_submission")
    private Submission idSubmission;

    @ManyToOne
    @JoinColumn(name = "id_tag")
    private Tag idTag;

    public SubmissionTag() { }

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
     * @return [Submission] : returns the  submission of a submission-tag.
     */
    public Submission getIdSubmission() {
        return idSubmission;
    }

    /**
     * Effect: Sets the submission of a submission-tag.
     * @param idSubmission idSubmission: new value for submission
     */
    public void setIdSubmission(Submission idSubmission) {
        this.idSubmission = idSubmission;
    }

    /**
     * Effect: Return the tag of a submission-tag.
     * @return [Tag] : returns the tag.
     */
    public Tag getIdTag() {
        return idTag;
    }

    /**
     * Effect: Sets the tag of a submission-tag.
     * @param idTag [Tag]: new value for tag
     */
    public void setIdTag(Tag idTag) {
        this.idTag = idTag;
    }
}
