package service;


import transfarable.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface ServiceInterface extends Remote {

    void activeUser(User user) throws RemoteException;

}
