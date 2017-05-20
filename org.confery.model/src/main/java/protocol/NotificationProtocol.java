package protocol;

import domain.NotificationEntity;
import domain.UserEntity;
import exception.SystemException;

import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface NotificationProtocol
        extends ModelInterface<NotificationEntity, Integer> {

    /**
     * Sends a notification to a specified user.
     * The user should already be available in our system.
     *
     * @param user         The destination user
     * @param notification The notification we want to send
     */
    void sendNotificationTo(UserEntity user, NotificationEntity notification) throws SystemException;

    /**
     * Sends a notification to a list of users.
     *
     * @param users        The list of users
     * @param notification The target notification
     * @throws SystemException If one of the users is not a part of the system.
     */
    void sendNotificationToUsers(List<UserEntity> users, NotificationEntity notification) throws SystemException;
}
