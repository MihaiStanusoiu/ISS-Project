package service;

import notification.NotificationUpdate;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface SubscriptionService extends Remote {

    void addSubscriber(SubscriberService subscriber) throws RemoteException;

    void removeSubscriber(SubscriberService subscriber) throws RemoteException;

    void notifyAll(NotificationUpdate notification) throws RemoteException;

}
