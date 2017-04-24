package domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Name:         AuthorSubmissionItems
 * Effect:       Class for database domain AuthorSubmissionEntity table composite PK
 * Date:         4/8/2017
 * Tested:       False
 *
 * @author      {Teodorescu Vlad}
 * @version     1.0
 */

@Embeddable
@SuppressWarnings("unused")
public class AuthorSubmissionItems implements Serializable {

  //  @Column(name = "ID_USER")
    private Integer idUser;

   // @Column(name = "ID_SUBMISSION")
    private Integer idSubmission;

    public AuthorSubmissionItems() { }

    /**
     * Effect: Getter for the id of the user.
     * @return Integer : returns idUser.
     */
    public Integer getIdUser() {
        return idUser;
    }

    /**
     * Effect: Sets the idUser to the given value
     * @param idUser: new value for idUser
     */
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    /**
     * Effect: Getter for the id of the submission.
     * @return Integer : returns idSubmission.
     */
    public Integer getIdSubmission() {
        return idSubmission;
    }

    /**
     * Effect: Sets the idSubmission to the given value
     * @param idSubmission: new value for idSubmission
     */
    public void setIdSubmission(Integer idSubmission) {
        this.idSubmission = idSubmission;
    }
}
