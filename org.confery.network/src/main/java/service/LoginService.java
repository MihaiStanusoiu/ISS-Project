package service;

import exception.RepositoryException;
import transferable.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface LoginService extends Remote {

    User login(String username, String password) throws RemoteException, RepositoryException;

}
