package listener;

import domain.UserEntity;
import notification.Notification;
import service.SubscriberService;

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
    public void setActiveUser(UserEntity user) throws RemoteException {
        activeUser = user;
    }

    @Override
    public UserEntity getActiveUser() throws RemoteException {
        return activeUser;
    }

    @Override
    public void update(Notification notification) throws RemoteException {
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
    public void notifyAll(Notification notification) throws RemoteException {
        subscribers.forEach(subscriberService -> runFunction(subscriberService::update, notification));
    }

}
