package protocol;

import domain.SessionEntity;
import domain.UserEntity;
import domain.UserRole;
import exception.SystemException;

import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface SessionProtocol extends ModelInterface<SessionEntity, Integer> {

    /**
     * Adds a specified user to a specified session and gives him a role in context.
     * @param session The target session
     * @param user The target user
     * @param role User's role in the target session
     */
    void addUserTo(SessionEntity session, UserEntity user, UserRole role) throws SystemException;

    /**
     * Returns all the session's speakers.
     * @param session The target session
     * @return The target session's speakers
     */
    List<UserEntity> getAllSpeakers(SessionEntity session) throws SystemException;

    /**
     * Returns all the listeners for a specified session.
     * @param session The target session.
     * @return The target session's listeners
     */
    List<UserEntity> getAllListeners(SessionEntity session) throws SystemException;

    /**
     * Returns the chair of the specified session.
     * @param sessionEntity The target session.
     * @return The target session's chair
     * @throws SystemException If we don't have a chair set up for the target session.
     */
    UserEntity getChair(SessionEntity sessionEntity) throws SystemException;

}
