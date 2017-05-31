package manager;

import checker.NotificationPermissionChecker;
import domain.NotificationEntity;
import protocol.NotificationProtocol;
import service.NotificationService;
import transfarable.Notification;
import transfarable.User;
import translator.NotificationTranslator;

import java.rmi.RemoteException;
import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class NotificationManager extends GenericManager<Notification, Integer, NotificationEntity> implements NotificationService {

    public NotificationManager(NotificationProtocol model) throws RemoteException {
        super(model);
        checker = new NotificationPermissionChecker();
        translator = new NotificationTranslator();
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
