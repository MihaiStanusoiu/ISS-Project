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

public class NotificationManager extends GenericManager<Notification, Integer, NotificationEntity> implements NotificationService {

    private UserTranslator userTranslator;

    protected NotificationProtocol model;

    public NotificationManager(NotificationProtocol model, LoginProtocol loginProtocol) throws RemoteException {
        super(model, loginProtocol);
        checker = new NotificationPermissionChecker();
        translator = new NotificationTranslator();
        userTranslator = new UserTranslator();
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
        UserEntity active = getActiveUser();
        basedOn(checker.isAllowed(active).toUpdate().theObject(translator.translate(notification)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
        return translator.translate(runFunction(model::sendNotificationTo,
                userTranslator.translate(user), translator.translate(notification)).orThrow(thrower));
    }

    @Override
    public Notification sendNotificationToUsers(List<User> users, Notification notification) throws RemoteException {
        UserEntity active = getActiveUser();
        basedOn(checker.isAllowed(active).toUpdate().theObject(translator.translate(notification)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
        List<UserEntity> entities = users.stream()
                .map(user -> userTranslator.translate(user))
                .collect(Collectors.toList());
        return translator.translate(runFunction(model::sendNotificationToUsers, entities,
                translator.translate(notification)).orThrow(thrower));
    }
}
