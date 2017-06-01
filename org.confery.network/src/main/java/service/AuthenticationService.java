package service;

import transfarable.User;

import java.rmi.RemoteException;

/**
 * Created by Vlad on 6/1/2017.
 */
public interface AuthenticationService extends ServiceInterface {

    void addActiveUser(User user) throws RemoteException;

    void deleteActiveUser(User user) throws RemoteException;
}
