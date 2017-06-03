package manager;

import checker.NotificationPermissionChecker;
import domain.NotificationEntity;
import domain.UserEntity;
import protocol.LoginProtocol;
import protocol.NotificationProtocol;
import service.NotificationService;
import transfarable.Notification;
import transfarable.User;
import translator.NotificationTranslator;
import translator.UserTranslator;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

import static utils.Conditional.basedOn;
import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica & Tiron Andreea - Ecaterina
 * @version 1.0
 */

public class NotificationManager
        extends GenericManager<Notification, Integer, NotificationEntity>
        implements NotificationService {

    private UserTranslator userTranslator;
    protected NotificationProtocol model;

    public NotificationManager(NotificationProtocol model, LoginProtocol loginProtocol) throws RemoteException {
        super(model, loginProtocol);
        this.model = model;
        checker = new NotificationPermissionChecker();
        translator = new NotificationTranslator();
        userTranslator = new UserTranslator();
    }

    @Override
    public User getUser(Notification notification) throws RemoteException {
        return userTranslator.translate(getNotificationFromDatabase(notification).getUser());
    }

    private NotificationEntity getNotificationFromDatabase(Notification notification) throws RemoteException {
        return runFunction(model::getElementById, notification.getId()).orThrow(thrower);
    }

    @Override
    public Notification sendNotificationToUser(User user, Notification notification) throws RemoteException {
        checkUserPermissions(notification);
        return translator.translate(runFunction(model::sendNotificationTo, userTranslator.translate(user),
                getNotificationFromDatabase(notification)).orThrow(thrower));
    }

    private void checkUserPermissions(Notification notification) throws RemoteException {
        basedOn(checker.isAllowed(getActiveUser()).toUpdate().theObject(getNotificationFromDatabase(notification)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
    }

    @Override
    public Notification sendNotificationToUsers(List<User> users, Notification notification) throws RemoteException {
        checkUserPermissions(notification);
        return translator.translate(runFunction(model::sendNotificationToUsers, translateUsers(users),
                getNotificationFromDatabase(notification)).orThrow(thrower));
    }

    private List<UserEntity> translateUsers(List<User> users) {
        return users.stream().map(user -> userTranslator.translate(user)).collect(Collectors.toList());
    }

}
