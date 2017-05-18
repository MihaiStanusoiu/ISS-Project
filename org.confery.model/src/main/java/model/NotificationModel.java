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

import static utils.Conditional.basedOn;
import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class NotificationModel
        extends Model<NotificationEntity, Integer>
        implements NotificationProtocol {

    private RepositoryInterface<UserEntity, Integer> userRepository;

    public NotificationModel(DatabaseLoaderInterface loader) {
        super(NotificationEntity.class, loader);
        userRepository = new RepositoryEntity<>(UserEntity.class, loader);
    }

    @Override
    public void sendNotificationTo(UserEntity user, NotificationEntity notification)
            throws SystemException {
        basedOn(userRepository.getAll().stream().anyMatch(item -> item.getId().equals(user.getId())))
                .orThrow(new ModelException("404! User Not Found!"));
        notification.setUser(user);
        this.add(notification);
    }

    @Override
    public void sendNotificationToUsers(List<UserEntity> users, NotificationEntity notification)
            throws SystemException {
        users.forEach(user -> runFunction(this::sendNotificationTo, user, notification).or(Boolean.FALSE));
    }

}
