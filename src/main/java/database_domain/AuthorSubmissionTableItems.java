package database_domain;

import javax.persistence.*;
/**
 * Name:         AuthorSubmissionTableItems
 * Effect:       Class for database_domain AuthorSubmission table composite PK
 * Date:         4/8/2017
 * Tested:       False
 *
 * @author      {Teodorescu Vlad}
 * @version     1.0
 */

@Embeddable
public class AuthorSubmissionTableItems
{
    @Column(name = "id_user")
    private Integer id_user;

    @Column(name = "id_submission")
    private Integer id_submission;

    public AuthorSubmissionTableItems() {
    }

    /**
     * Effect: Getter for the id of the user.
     * @return Integer : returns id_user.
     */
    public Integer getId_user() {
        return id_user;
    }

    /**
     * Effect: Sets the id_user to the given value
     * @param id_user: new value for id_user
     */
    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    /**
     * Effect: Getter for the id of the submission.
     * @return Integer : returns id_submission.
     */
    public Integer getId_submission() {
        return id_submission;
    }

    /**
     * Effect: Sets the id_submission to the given value
     * @param id_submission: new value for id_submission
     */
    public void setId_submission(Integer id_submission) {
        this.id_submission = id_submission;
    }
}
