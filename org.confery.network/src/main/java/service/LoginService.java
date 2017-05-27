package service;

import exception.SystemException;
import transferable.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface LoginService extends Remote, Service {

    User login(String username, String password) throws RemoteException, SystemException;

}
