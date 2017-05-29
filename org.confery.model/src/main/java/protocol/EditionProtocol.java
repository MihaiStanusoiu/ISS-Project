package protocol;

import domain.*;
import exception.SystemException;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface EditionProtocol extends ModelInterface<EditionEntity, Integer> {

    /**
     * Adds a new member to an edition.
     *
     * @param edition The target edition [already in the database]
     * @param user    The target user [already in the database]
     * @param role    The member's role
     * @return The edition after we add the new member.
     * @throws SystemException If the new member is already a part of the edition or the role is not valid.
     */
    EditionEntity addMemberTo(EditionEntity edition, UserEntity user, MemberRole role) throws SystemException;

    /**
     * Deletes a member from the edition.
     *
     * @param edition The target edition [already in the database]
     * @param member  The target member [already in the database]
     * @return The edition after we deleted the wanted member.
     * @throws SystemException if the member is not a member of the edition or if the member is the chair.
     */
    EditionEntity deleteMemberOf(EditionEntity edition, UserEntity member) throws SystemException;

    /**
     * Adds a session to a edition.
     *
     * @param edition The target edition [already in the database]
     * @param session The target session [already in the database]
     * @return The edition after we add the session.
     * @throws SystemException If the session is not valid.
     */
    EditionEntity addSessionTo(EditionEntity edition, SessionEntity session) throws SystemException;

    /**
     * Deletes a session from the edition.
     *
     * @param edition The target read [already in the database]
     * @param session The target session [already in the database]
     * @return The edition after we delete the session
     * @throws SystemException If the session is not a part of the edition.
     */
    EditionEntity deleteSessionOf(EditionEntity edition, SessionEntity session) throws SystemException;

    /**
     * Adds a submission to a edition.
     *
     * @param edition    The target edition [already in the database]
     * @param submission The target submission [already in the database]
     * @return The edition after we add the submission
     * @throws SystemException If the submission is not valid.
     */
    EditionEntity addSubmissionTo(EditionEntity edition, SubmissionEntity submission) throws SystemException;

    /**
     * Deletes a submission from the database.
     *
     * @param edition    The target edition [already in the database]
     * @param submission The target submission [already in the database]
     * @return The edition after we delete the submission.
     * @throws SystemException If the submission is not a part of the edition.
     */
    EditionEntity deleteSubmissionOf(EditionEntity edition, SubmissionEntity submission) throws SystemException;

}
