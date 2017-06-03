package model;

import database.DatabaseLoaderInterface;
import domain.NotificationEntity;
import domain.UserEntity;
import exception.ModelException;
import exception.SystemException;
import protocol.NotificationProtocol;
import repository.RepositoryEntity;
import repository.RepositoryInterface;

import java.util.List;
import java.util.Objects;

import static utils.Conditional.basedOn;
import static utils.Try.runFunction;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class NotificationModel extends Model<NotificationEntity, Integer> implements NotificationProtocol {

    private RepositoryInterface<UserEntity, Integer> userRepository;

    public NotificationModel(DatabaseLoaderInterface loader) {
        super(NotificationEntity.class, loader);
        userRepository = new RepositoryEntity<>(UserEntity.class, loader);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NotificationEntity sendNotificationTo(UserEntity user, NotificationEntity notification)
            throws SystemException {
        basedOn(Objects.nonNull(userRepository.getElementById(user.getId())))
                .orThrow(new ModelException("404 Used Not Found!"));
        notification.setUser(user);
        this.update(getElementById(notification.getId()), notification);
        return getElementById(notification.getId());
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public NotificationEntity sendNotificationToUsers(List<UserEntity> users, NotificationEntity notification)
            throws SystemException {
        users.forEach(user -> runFunction(this::sendNotificationTo, user, notification));
        return getElementById(notification.getId());
    }

}
