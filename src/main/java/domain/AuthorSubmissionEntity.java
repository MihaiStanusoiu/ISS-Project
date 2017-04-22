package domain;

import javax.persistence.*;

/**
 * Name:         AuthorSubmissionEntity
 * Effect:       Class for database domain AuthorSubmissionEntity table
 * Date:         4/8/2017
 * Tested:       False
 *
 * @author      {Teodorescu Vlad}
 * @version     1.0
 */

@Entity
@Table(name = "AUTHOR_SUBMISSION")
@SuppressWarnings("unused")
public class AuthorSubmissionEntity {

    @EmbeddedId
    private AuthorSubmissionItems pkId;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private UserEntity userTable;

    @ManyToOne
    @JoinColumn(name = "ID_SUBMISSION")
    private SubmissionEntity submission;

    @Column(name = "IS_OWNER")
    private Boolean isOwner;

    @Column(name = "PRESENTATION_URL")
    private String presentationUrl;

    public AuthorSubmissionEntity() { }

    /**
     * Effect: Getter for the primary key.
     * @return AuthorSubmissionItems : returns pkId.
     */
    public AuthorSubmissionItems getId() {
        return pkId;
    }

    /**
     * Effect: Sets the pkId to the given value
     * @param pkId: new value for pkId
     */
    public void setId(AuthorSubmissionItems pkId) {
        this.pkId = pkId;
    }

    /**
     * Effect: Getter for the user created by join.
     * @return UserEntity : returns userTable.
     */
    public UserEntity getUserTable() {
        return userTable;
    }

    /**
     * Effect: Sets the userTable to the given value
     * @param userTable: new value for userTable
     */
    public void setUserTable(UserEntity userTable) {
        this.userTable = userTable;
    }

    /**
     * Effect: Getter for the submission created by join.
     * @return SubmissionEntity : returns submission.
     */
    public SubmissionEntity getSubmission() {
        return submission;
    }

    /**
     * Effect: Sets the submission to the given value
     * @param submission: new value for submission
     */
    public void setSubmission(SubmissionEntity submission) {
        this.submission = submission;
    }

    /**
     * Effect: Getter for the isOwner property.
     * @return Boolean : returns isOwner.
     */
    public Boolean getIsOwner() {
        return isOwner;
    }

    /**
     * Effect: Sets the isOwner property to the given value
     * @param isOwner: new value for isOwner
     */
    public void setIsOwner(Boolean isOwner) {
        this.isOwner = isOwner;
    }

    /**
     * Effect: Getter for the presentationUrl.
     * @return String : returns presentationUrl.
     */
    public String getPresentationUrl() {
        return presentationUrl;
    }

    /**
     * Effect: Sets the presentationUrl to the given value
     * @param presentationUrl: new value for presentationUrl
     */
    public void setPresentationUrl(String presentationUrl) {
        this.presentationUrl = presentationUrl;
    }
}
