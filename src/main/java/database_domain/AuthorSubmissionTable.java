package database_domain;

import javax.persistence.*;
/**
 * Name:         AuthorSubmissionTable
 * Effect:       Class for database_domain AuthorSubmission table
 * Date:         4/8/2017
 * Tested:       False
 *
 * @author      {Teodorescu Vlad}
 * @version     1.0
 */

@Entity
@Table(name = "AuthorSubmission")
public class AuthorSubmissionTable
{
    @EmbeddedId
    AuthorSubmissionTableItems PKid;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserTable userTable;

    @ManyToOne
    @JoinColumn(name = "id_submission")
    private SubmissionTable submissionTable;

    @Column(name = "is_owner")
    private Boolean is_owner;

    @Column(name = "presentation_url")
    private String presentation_url;

    public AuthorSubmissionTable() {
    }

    /**
     * Effect: Getter for the primary key.
     * @return AuthorSubmissionTableItems : returns PKid.
     */
    public AuthorSubmissionTableItems getPKid() {
        return PKid;
    }

    /**
     * Effect: Sets the PKid to the given value
     * @param PKid: new value for PKid
     */
    public void setPKid(AuthorSubmissionTableItems PKid) {
        this.PKid = PKid;
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
     * Effect: Getter for the is_owner property.
     * @return Boolean : returns is_owner.
     */
    public Boolean getIs_owner() {
        return is_owner;
    }

    /**
     * Effect: Sets the is_owner property to the given value
     * @param is_owner: new value for is_owner
     */
    public void setIs_owner(Boolean is_owner) {
        this.is_owner = is_owner;
    }

    /**
     * Effect: Getter for the presentation_url.
     * @return String : returns presentation_url.
     */
    public String getPresentation_url() {
        return presentation_url;
    }

    /**
     * Effect: Sets the presentation_url to the given value
     * @param presentation_url: new value for presentation_url
     */
    public void setPresentation_url(String presentation_url) {
        this.presentation_url = presentation_url;
    }
}
