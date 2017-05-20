package protocol;

import domain.MemberRole;
import domain.SessionEntity;
import domain.UserEntity;
import exception.SystemException;

import java.util.List;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface SessionProtocol extends ModelInterface<SessionEntity, Integer> {

    /**
     * Adds a specified user to a specified session and gives him a role in context.
     *
     * @param session The target session
     * @param user    The target user
     * @param role    User's role in the target session
     */
    void addMemberTo(SessionEntity session, UserEntity user, MemberRole role) throws SystemException;

    /**
     * Returns all the session's speakers.
     *
     * @param session The target session
     * @return The target session's speakers
     */
    List<UserEntity> getAllSpeakers(SessionEntity session) throws SystemException;

    /**
     * Returns all the listeners for a specified session.
     *
     * @param session The target session.
     * @return The target session's listeners
     */
    List<UserEntity> getAllListeners(SessionEntity session) throws SystemException;

    /**
     * Returns the chair of the specified session.
     *
     * @param sessionEntity The target session.
     * @return The target session's chair
     * @throws SystemException If we don't have a chair set up for the target session.
     */
    UserEntity getChair(SessionEntity sessionEntity) throws SystemException;

    /**
     * Removes a member from session.
     *
     * @param session The target session
     * @param user    The member we need to remove
     * @return The removed member
     * @throws SystemException If the member is not found in session.
     */
    UserEntity removeMemberFrom(SessionEntity session, UserEntity user) throws SystemException;

    /**
     * Changes the role of a member in target session [This method can't change the chair]
     *
     * @param session The target session
     * @param user    The target member
     * @param role    The new role
     * @throws SystemException If you're trying to change the role of the chair
     * @apiNote Use function 'changeChair' to change the current chair member.
     */
    void changeMemberRoleIn(SessionEntity session, UserEntity user, MemberRole role) throws SystemException;

    /**
     * Changes the chair with another user of the session
     * [the current chair will no longer be a part of the session]
     *
     * @param session The target session
     * @param user    The target user
     * @throws SystemException If the current chair is not set
     */
    void changeChairIn(SessionEntity session, UserEntity user) throws SystemException;

}
