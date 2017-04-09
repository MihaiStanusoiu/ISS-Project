package database_domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Name:         SubmissionTagTableId
 * Effect:       Corresponding class for the multi-column id of the table SubmissionTag
 * Date:         08.04.2017
 * Tested:       False
 * @author       Tanasie Luiza Maria
 * @version      1.0
 */

@Embeddable
public class SubmissionTagTableId {

    @Column(name = "id_tag")
    private Integer idTag;

    @Column(name = "id_submission")
    private Integer idSubmission;

    public SubmissionTagTableId() {}

    /**
     * Effect: Return the id of a tag if a submission-tag.
     * @return [Integer] : returns the id of a tag.
     */
    public Integer getIdTag() {
        return idTag;
    }

    /**
     * Effect: Sets the id of the tag of the submission-tag.
     * @param[Integer] id_tag: new value for tag id
     */
    public void setIdTag(Integer idTag) {
        this.idTag = idTag;
    }

    /**
     * Effect: Return the id of a submission if a submission-tag.
     * @return [Integer] : returns the id of a submission.
     */
    public Integer getIdSubmission() {
        return idSubmission;
    }

    /**
     * Effect: Sets the id of the submission of the submission-tag.
     * @param[Integer] id_tag: new value for submission id
     */
    public void setIdSubmission(Integer idSubmission) {
        this.idSubmission = idSubmission;
    }
}