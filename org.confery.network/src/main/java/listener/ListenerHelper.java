package listener;

import notification.Notification;
import service.SubscriberService;
import transferable.User;
import utils.ThrowPipe;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ListenerHelper extends UnicastRemoteObject implements Listener {

    private User activeUser;
    private ArrayList<SubscriberService> subscribers;

    public ListenerHelper() throws RemoteException {
        subscribers = new ArrayList<>();
    }

    @Override
    public void setActiveUser(User user) throws RemoteException {
        activeUser = user;
    }

    @Override
    public User getActiveUser() throws RemoteException {
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
        subscribers.forEach(subscriberService -> ThrowPipe.wrap(() -> subscriberService.update(notification)));
    }

}
