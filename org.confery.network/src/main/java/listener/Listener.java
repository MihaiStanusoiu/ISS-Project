package listener;

import service.SubscriberService;
import service.SubscriptionService;
import transferable.User;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface Listener extends SubscriberService, SubscriptionService, Serializable, Remote {

    void setActiveUser(User user) throws RemoteException;
    User getActiveUser() throws RemoteException;

}
