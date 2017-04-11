package databasedomain;

import javax.persistence.*;

/**
 * Name:         AuthorSubmissionTable
 * Effect:       Class for databasedomain AuthorSubmission table
 * Date:         4/8/2017
 * Tested:       False
 *
 * @author      {Teodorescu Vlad}
 * @version     1.0
 */

@Entity
@Table(name = "AuthorSubmission")
@SuppressWarnings("unused")
public class AuthorSubmissionTable {

    @EmbeddedId
    private AuthorSubmissionTableItems pkId;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserTable userTable;

    @ManyToOne
    @JoinColumn(name = "id_submission")
    private SubmissionTable submissionTable;

    @Column(name = "is_owner")
    private Boolean isOwner;

    @Column(name = "presentation-url")
    private String presentationUrl;

    public AuthorSubmissionTable() { }

    /**
     * Effect: Getter for the primary key.
     * @return AuthorSubmissionTableItems : returns pkId.
     */
    public AuthorSubmissionTableItems getPkId() {
        return pkId;
    }

    /**
     * Effect: Sets the pkId to the given value
     * @param pkId: new value for pkId
     */
    public void setPkId(AuthorSubmissionTableItems pkId) {
        this.pkId = pkId;
    }

    /**
     * Effect: Getter for the user created by join.
     * @return UserTable : returns userTable.
     */
    public UserTable getUserTable() {
        return userTable;
    }

    /**
     * Effect: Sets the userTable to the given value
     * @param userTable: new value for userTable
     */
    public void setUserTable(UserTable userTable) {
        this.userTable = userTable;
    }

    /**
     * Effect: Getter for the submission created by join.
     * @return SubmissionTable : returns submissionTable.
     */
    public SubmissionTable getSubmissionTable() {
        return submissionTable;
    }

    /**
     * Effect: Sets the submissionTable to the given value
     * @param submissionTable: new value for submissionTable
     */
    public void setSubmissionTable(SubmissionTable submissionTable) {
        this.submissionTable = submissionTable;
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
