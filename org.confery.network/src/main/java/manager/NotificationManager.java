package manager;

import domain.NotificationEntity;
import domain.UserEntity;
import exception.SystemException;
import protocol.NotificationProtocol;
import service.NotificationService;
import transfarable.Notification;
import transfarable.User;
import translator.NotificationTranslator;
import translator.UserTranslator;
import utils.Try;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class NotificationManager implements NotificationService {

    protected NotificationProtocol model;
    protected UserEntity active;
    private Function<SystemException, RemoteException> thrower;

    public NotificationManager(NotificationProtocol model) throws RemoteException {
        this.model = model;
        thrower = exception -> new RemoteException(exception != null ? exception.getMessage() : null);
    }

    @Override
    public void activeUser(User user) throws RemoteException {
        UserEntity userEntity = UserTranslator.translate(user);
        active = userEntity;
    }

    @Override
    public Integer add(Notification element) throws RemoteException {
        return Try.runFunction(model::add, NotificationTranslator.translate(element)).orThrow(thrower);
    }

    @Override
    public Notification update(Notification element, Notification with) throws RemoteException {
        Try.runMethod(
                model::update,
                NotificationTranslator.translate(element),
                NotificationTranslator.translate(with)
        ).orThrow(thrower);
        return with;
    }


    @Override
    public Notification delete(Notification element) throws RemoteException {
        return NotificationTranslator.translate(
                Try.runFunction(model::delete, NotificationTranslator.translate(element))
                        .orThrow(thrower)
        );
    }

    @Override
    public Notification getElementById(Integer id) throws RemoteException {
        return NotificationTranslator.translate(
                Try.runFunction(model::getElementById, id)
                        .orThrow(thrower)
        );
    }

    @Override
    public List<Notification> getAll() throws RemoteException {
        List<Notification> transferableNotifications = new ArrayList<>();
        List<NotificationEntity> Notifications = model.getAll();

        for (NotificationEntity NotificationEntity : Notifications) {
            transferableNotifications.add(NotificationTranslator.translate(NotificationEntity));
        }

        return transferableNotifications;
    }

    @Override
    public Notification sendNotificationToUser(User user, Notification notification) throws RemoteException {
        UserEntity userEntity = UserTranslator.translate(user);
        NotificationEntity notificationEntity = NotificationTranslator.translate(notification);

        //  DOES NOT THROW EXCEPTION
        return NotificationTranslator.translate(
                Try.runFunction(model::sendNotificationTo, userEntity, notificationEntity).getElement()
        );
    }

    @Override
    public Notification sendNotificationToUsers(List<User> users, Notification notification) throws RemoteException {
        Notification returnedNotification = null;

        for (User user : users) {
            returnedNotification = sendNotificationToUser(user, notification);
        }

        if (returnedNotification != null) {
            return returnedNotification;
        }
        else {
            throw new RemoteException("No users received");
        }
    }
}
