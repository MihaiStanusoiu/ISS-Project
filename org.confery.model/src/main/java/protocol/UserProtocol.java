package protocol;

import domain.*;
import exception.SystemException;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface UserProtocol extends ModelInterface<UserEntity, Integer> {

    /**
     * Adds an edition to user.
     *
     * @param user The target user [already in the database]
     * @param edition The target edition [already in the database]
     * @param role The wanted role
     * @return The user after we added the edition.
     * @throws SystemException If the user is already a member of the edition.
     */
    UserEntity addEditionTo(UserEntity user, EditionEntity edition, MemberRole role) throws SystemException;

    /**
     * Adds a session to user.
     *
     * @param user The target user [already in the database]
     * @param session The target session [already in the database]
     * @param role The wanted role
     * @return The user after we added the session.
     * @throws SystemException If the user is already a member of the session.
     */
    UserEntity addSessionTo(UserEntity user, SessionEntity session, MemberRole role) throws SystemException;

    /**
     * Adds a submission to user.
     *
     * @param user The target user [already in the database]
     * @param submission The target submission [already in the database]
     * @param isOwner Flag to mark if the user is the owner.
     * @return The user after we added the submission
     * @throws SystemException If the user has already this submission.
     */
    UserEntity addSubmissionTo(UserEntity user, SubmissionEntity submission, Boolean isOwner) throws SystemException;

    /**
     * Adds a notification to user.
     *
     * @param user The target user [already in the database]
     * @param notification The target notification [already in the database]
     * @return The user after we added the notification.
     * @throws SystemException If the user has already this notification.
     */
    UserEntity addNotificationTo(UserEntity user, NotificationEntity notification) throws SystemException;

    /**
     * Removes a edition from the user.
     *
     * @param user The target user [already in the database]
     * @param edition The target edition [already in the database]
     * @return The user after we remove the edition.
     * @throws SystemException If the user if not a member of the edition.
     */
    UserEntity removeEditionFrom(UserEntity user, EditionEntity edition) throws SystemException;

    /**
     * Removes a session from the user.
     *
     * @param user The target user [already in the database]
     * @param session The target session [already in the database]
     * @return The user after we remove the session.
     * @throws SystemException If the user if not a member of the session.
     */
    UserEntity removeSessionFrom(UserEntity user, SessionEntity session) throws SystemException;

    /**
     * Removes a submission from the user.
     *
     * @param user The target user [already in the database]
     * @param submission The target submission [already in the database]
     * @return The user after we remove the submission.
     * @throws SystemException If the user if doesn't have the submission.
     */
    UserEntity removeSubmissionFrom(UserEntity user, SubmissionEntity submission) throws SystemException;

    /**
     * Removes a notification from the user.
     *
     * @param user The target user [already in the database]
     * @param notification The target notification [already in the database]
     * @return The user after we remove the notification.
     * @throws SystemException If the user if doesn't have the notification.
     */
    UserEntity removeNotificationFrom(UserEntity user, NotificationEntity notification) throws SystemException;

}
