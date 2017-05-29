package listener;

import domain.UserEntity;
import notification.NotificationUpdate;
import service.SubscriberService;
import transferable.User;
import translator.UserTranslator;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ListenerHelper extends UnicastRemoteObject implements Listener {

    private UserEntity activeUser;
    private ArrayList<SubscriberService> subscribers;

    public ListenerHelper() throws RemoteException {
        subscribers = new ArrayList<>();
    }

    @Override
    public void setActiveUser(User user) throws RemoteException {
        UserEntity userEntity = UserTranslator.translate(user);
        activeUser = userEntity;
    }

    @Override
    public User getActiveUser() throws RemoteException {
        User transferableUser = UserTranslator.translate(activeUser);
        return transferableUser;
    }

    @Override
    public void update(NotificationUpdate notification) throws RemoteException {
        notifyAll(notification);
    }

    @Override
    public synchronized void addSubscriber(SubscriberService subscriber) throws RemoteException {
        subscribers.add(subscriber);
    }

    @Override
    public synchronized void removeSubscriber(SubscriberService subscriber) throws RemoteException {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifyAll(NotificationUpdate notification) throws RemoteException {
        subscribers.forEach(subscriberService -> runFunction(subscriberService::update, notification));
    }

}
