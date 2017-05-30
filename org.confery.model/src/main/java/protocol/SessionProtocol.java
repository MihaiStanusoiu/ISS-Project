package protocol;

import domain.MemberRole;
import domain.SessionEntity;
import domain.UserEntity;
import exception.SystemException;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */


public interface SessionProtocol extends ModelInterface<SessionEntity, Integer> {

    /**
     * Adds a specified user to a specified session and gives him a role in context.
     * <p> Preconditions: :user: should already be saved in the database </p>
     * <p> Preconditions: :session: should already be saved in the database </p>
     *
     * @param session The target session [already saved in the database]
     * @param user    The target user [already saved in the database]
     * @param role    User's role in the target session
     * @return The session member after it's been saved to database
     * @throws SystemException If the user is not already saved in the database
     */
    SessionEntity addMemberTo(SessionEntity session, UserEntity user, MemberRole role) throws SystemException;

    /**
     * Removes a member from session.
     *
     * @param session The target session
     * @param user    The member we need to remove
     * @return The removed member
     * @throws SystemException If the member is not found in session.
     */
    SessionEntity removeMemberFrom(SessionEntity session, UserEntity user) throws SystemException;

    /**
     * Changes the role of a member in target session [
     *
     * @param session The target session [already in the database]
     * @param user    The target member [already in the database]
     * @param role    The new role
     * @throws SystemException If you're trying to change the role of the chair
     * @implNote This function cannot change the chair!
     * @apiNote Use function 'changeChair' to change the current chair member.
     */
    SessionEntity changeMemberRoleIn(SessionEntity session, UserEntity user, MemberRole role) throws SystemException;

    /**
     * Changes the chair with another user of the session.
     *
     * @param session The target session [already in the database]
     * @param user    The target user [the new chair] [already in the database]
     * @throws SystemException If the current chair is not set
     * @apiNote The current chair will no longer be a part of the session
     */
    SessionEntity changeChairIn(SessionEntity session, UserEntity user) throws SystemException;

}
