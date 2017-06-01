package manager;

import checker.NotificationPermissionChecker;
import domain.NotificationEntity;
import domain.UserEntity;
import protocol.NotificationProtocol;
import service.NotificationService;
import transfarable.Notification;
import transfarable.User;
import translator.NotificationTranslator;
import translator.UserTranslator;

import java.rmi.RemoteException;
import java.util.List;

import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class NotificationManager extends GenericManager<Notification, Integer, NotificationEntity> implements NotificationService {

    private UserTranslator userTranslator;

    public NotificationManager(NotificationProtocol model) throws RemoteException {
        super(model);
        checker = new NotificationPermissionChecker();
        translator = new NotificationTranslator();
    }

    @Override
    public User getUser(Notification notification) throws RemoteException {
        return userTranslator.translate(getUserEntity(notification));
    }

    private UserEntity getUserEntity(Notification notification) throws RemoteException {
        return getNotificationEntity(notification).getUser();
    }

    private NotificationEntity getNotificationEntity(Notification notification) throws RemoteException {
        return runFunction(model::getElementById, notification.getId()).orThrow(thrower);
    }


    @Override
    public Notification sendNotificationToUser(User user, Notification notification) throws RemoteException {
        // TODO
        return null;
    }

    @Override
    public Notification sendNotificationToUsers(List<User> users, Notification notification) throws RemoteException {
        // TODO
        return null;
    }
}
