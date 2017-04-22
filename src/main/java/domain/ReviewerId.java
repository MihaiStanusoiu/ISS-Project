package domain;

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
@SuppressWarnings("unused")
public class ReviewerId {

    @ManyToOne
    @Column(name = "ID_EDITION_MEMBER")
    private Integer idEditionMember;

    @ManyToOne
    @Column(name = "ID_SUBMISSION")
    private Integer idSubmission;

    /**
     * Empty constructor
     */
    public ReviewerId(){}

    /**
     * Effect: Return the id of a conference member.
     * @return [Integer]: returns the id of a conference member.
     */
    public Integer getIdEditionMember() {
        return idEditionMember;
    }

    /**
     * Effect: Sets the id of a conference member.
     * @param idEditionMember: new value for conference member id.
     */
    public void setIdEditionMember(Integer idEditionMember) {
        this.idEditionMember = idEditionMember;
    }

    /**
     * Effect: Return the id of a submission.
     * @return [Integer]: returns the id of a submission.
     */
    public Integer getIdSubmission() {
        return idSubmission;
    }

    /**
     * Effect: Sets the id of a SubmissionEntity.
     * @param idSubmission: new value for submission id.
     */
    public void setIdSubmission(Integer idSubmission) {
        this.idSubmission = idSubmission;
    }
}
