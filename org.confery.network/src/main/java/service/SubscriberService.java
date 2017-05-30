package service;

import notification.NotificationUpdate;


import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface SubscriberService extends Remote {

    void update(NotificationUpdate notification) throws RemoteException;

}
