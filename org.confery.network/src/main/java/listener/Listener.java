package listener;

import domain.UserEntity;
import service.SubscriberService;
import service.SubscriptionService;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface Listener extends SubscriberService, SubscriptionService, Serializable, Remote {

    void setActiveUser(UserEntity user) throws RemoteException;

    UserEntity getActiveUser() throws RemoteException;

}
