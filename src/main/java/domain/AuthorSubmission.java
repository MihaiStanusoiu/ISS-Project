package domain;

import javax.persistence.*;

/**
 * Name:         AuthorSubmission
 * Effect:       Class for database domain AuthorSubmission table
 * Date:         4/8/2017
 * Tested:       False
 *
 * @author      {Teodorescu Vlad}
 * @version     1.0
 */

@Entity
@Table(name = "AuthorSubmission")
@SuppressWarnings("unused")
public class AuthorSubmission {

    @EmbeddedId
    private AuthorSubmissionItems pkId;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User userTable;

    @ManyToOne
    @JoinColumn(name = "id_submission")
    private Submission submission;

    @Column(name = "is_owner")
    private Boolean isOwner;

    @Column(name = "presentation-url")
    private String presentationUrl;

    public AuthorSubmission() { }

    /**
     * Effect: Getter for the primary key.
     * @return AuthorSubmissionItems : returns pkId.
     */
    public AuthorSubmissionItems getPkId() {
        return pkId;
    }

    /**
     * Effect: Sets the pkId to the given value
     * @param pkId: new value for pkId
     */
    public void setPkId(AuthorSubmissionItems pkId) {
        this.pkId = pkId;
    }

    /**
     * Effect: Getter for the user created by join.
     * @return User : returns userTable.
     */
    public User getUserTable() {
        return userTable;
    }

    /**
     * Effect: Sets the userTable to the given value
     * @param userTable: new value for userTable
     */
    public void setUserTable(User userTable) {
        this.userTable = userTable;
    }

    /**
     * Effect: Getter for the submission created by join.
     * @return Submission : returns submission.
     */
    public Submission getSubmission() {
        return submission;
    }

    /**
     * Effect: Sets the submission to the given value
     * @param submission: new value for submission
     */
    public void setSubmission(Submission submission) {
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
