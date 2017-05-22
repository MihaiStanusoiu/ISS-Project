package protocol;

import domain.NotificationEntity;
import domain.UserEntity;
import exception.SystemException;

import java.util.List;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface NotificationProtocol
        extends ModelInterface<NotificationEntity, Integer> {

    /**
     * Sends a notification to a specified user.
     * <p>It's important to use an user that is already saved in our database.</p>
     * <p>The notification will be added to our database,
     * so you don't need to have the notification saved in database. </p>
     * <p>The notification will be composed only with text.</p>
     *
     * @param user         The destination user [already in the database]
     * @param notification The notification we want to send [not in the database]
     * @return The notification after it's saved in the database
     * @throws SystemException If the user is not already saved in the database
     */
    NotificationEntity sendNotificationTo(UserEntity user, NotificationEntity notification) throws SystemException;

    /**
     * Sends a notification to a list of users.
     * <p>It's important to use a list of users that are already saved in our database.</p>
     * <p>The notification will be added to our database,
     * so you don't need to have the notification saved in database. </p>
     * <p>The notification will be composed only with text.</p>
     *
     * @param users        The list of users [already in the database]
     * @param notification The target notification [not in the database]
     * @return The notification after it's saved in the database
     * @throws SystemException If one of the users is not already saved in the database
     */
    NotificationEntity sendNotificationToUsers(List<UserEntity> users, NotificationEntity notification) throws SystemException;
}
