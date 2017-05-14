package service;

import exception.RepositoryException;
import transferable.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface SignUpService extends Remote {

    User signUp(String username, String password, String confirm, String email, String name) throws RemoteException, RepositoryException;

}
