package databasedomain;

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
public class SubmissionTagTable {

    @EmbeddedId
    SubmissionTagTableId id;

    @ManyToOne
    @JoinColumn(name = "id_submission")
    private SubmissionTable idSubmission;

    @ManyToOne
    @JoinColumn(name = "id_tag")
    private TagTable idTag;

    public SubmissionTagTable() { }

    /**
     * Effect: Return the id of a submission-tag.
     * @return [SubmissionTableId] : returns the id of a submission-tag.
     */
    public SubmissionTagTableId getId() {
        return id;
    }

    /**
     * Effect: Sets the id of a submission-tag.
     * @param id [SubmissionTableId]: new value for id
     */
    public void setId(SubmissionTagTableId id) {
        this.id = id;
    }

    /**
     * Effect: Return the submission of a submission-tag.
     * @return [SubmissionTable] : returns the  submission of a submission-tag.
     */
    public SubmissionTable getIdSubmission() {
        return idSubmission;
    }

    /**
     * Effect: Sets the submission of a submission-tag.
     * @param idSubmission idSubmission: new value for submission
     */
    public void setIdSubmission(SubmissionTable idSubmission) {
        this.idSubmission = idSubmission;
    }

    /**
     * Effect: Return the tag of a submission-tag.
     * @return [TagTable] : returns the tag.
     */
    public TagTable getIdTag() {
        return idTag;
    }

    /**
     * Effect: Sets the tag of a submission-tag.
     * @param idTag [TagTable]: new value for tag
     */
    public void setIdTag(TagTable idTag) {
        this.idTag = idTag;
    }
}
