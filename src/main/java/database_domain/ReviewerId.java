package database_domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * Name:    ReviewerId
 * Effect:  Helping class for composite primary key
 * Date:    9/4/2017
 * Tested:  False
 *
 * @author Simion George-Vlad
 * @version 1.0
 */

@Embeddable
public class ReviewerId {

    @ManyToOne
    @Column(name = "id_conference_member")
    private Integer idConferenceMember;

    @ManyToOne
    @Column(name = "id_submission")
    private Integer idSubmission;

    /**
     * Empty constructor
     */
    public ReviewerId(){}

    /**
     * Effect: Return the id of a conference member.
     * @return [Integer]: returns the id of a conference member.
     */
    public Integer getIdConferenceMember() {
        return idConferenceMember;
    }

    /**
     * Effect: Sets the id of a conference member.
     * @param idConferenceMember: new value for conference member id.
     */
    public void setIdConferenceMember(Integer idConferenceMember) {
        this.idConferenceMember = idConferenceMember;
    }

    /**
     * Effect: Return the id of a submission.
     * @return [Integer]: returns the id of a submission.
     */
    public Integer getIdSubmission() {
        return idSubmission;
    }

    /**
     * Effect: Sets the id of a Submission.
     * @param idSubmission: new value for submission id.
     */
    public void setIdSubmission(Integer idSubmission) {
        this.idSubmission = idSubmission;
    }
}
