package manager;

import notification.NotificationUpdate;
import notification.NotificationCenter;
import service.SubscriberService;
import service.SubscriptionService;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class SubscriptionManager implements SubscriptionService {

    private NotificationCenter notificationCenter;

    public SubscriptionManager(NotificationCenter notificationCenter) throws RemoteException {
        this.notificationCenter = notificationCenter;
    }

    @Override
    public void addSubscriber(SubscriberService subscriber) throws RemoteException {
        notificationCenter.addSubscriber(subscriber);
    }

    @Override
    public void removeSubscriber(SubscriberService subscriber) throws RemoteException {
        notificationCenter.removeSubscriber(subscriber);
    }


    @Override
    public void notifyAll(NotificationUpdate notification) throws RemoteException {
        notificationCenter.notifyAll(notification);
    }

}
