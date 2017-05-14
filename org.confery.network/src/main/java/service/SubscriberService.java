package service;

import notification.Notification;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface SubscriberService extends Remote {

    void update(Notification notification) throws RemoteException;

}
