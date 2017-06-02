package service;

import transfarable.User;

import java.rmi.RemoteException;

/**
 * @author Vlad Teodorescu
 * @version 1.0
 */

public interface AuthenticationService extends ServiceInterface {

    User getActiveUser() throws RemoteException;

    void addActiveUser(User user) throws RemoteException;

    void deleteActiveUser(User user) throws RemoteException;
}
