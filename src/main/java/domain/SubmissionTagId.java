package domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Name:         SubmissionTagId
 * Effect:       Corresponding class for the multi-column id of the table SubmissionTagEntity
 * Date:         08.04.2017
 * Tested:       False
 * @author       Tanasie Luiza Maria
 * @version      1.0
 */

@Embeddable
@SuppressWarnings("unused")
public class SubmissionTagId {

    @Column(name = "ID_TAG")
    private Integer idTag;

    @Column(name = "ID_SUBMISSION")
    private Integer idSubmission;

    public SubmissionTagId() { }

    /**
     * Effect: Return the id of a tag if a submission-tag.
     * @return [Integer] : returns the id of a tag.
     */
    public Integer getIdTag() {
        return idTag;
    }

    /**
     * Effect: Sets the id of the tag of the submission-tag.
     * @param idTag [Integer]: new value for tag id
     */
    public void setIdTag(Integer idTag) {
        this.idTag = idTag;
    }

    /**
     * Effect: Return the id of a submission if a submission-tag.
     * @return [Integer]: returns the id of a submission.
     */
    public Integer getIdSubmission() {
        return idSubmission;
    }

    /**
     * Effect: Sets the id of the submission of the submission-tag.
     * @param idSubmission [Integer]: new value for submission id
     */
    public void setIdSubmission(Integer idSubmission) {
        this.idSubmission = idSubmission;
    }
}
