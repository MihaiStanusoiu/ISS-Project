package service;

import domain.UserEntity;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface CollectionService extends Remote {

    LoginService loginService() throws RemoteException;

    SignUpService signUpService() throws RemoteException;

    UserService userService() throws RemoteException;

    UserEntity getActiveUser() throws RemoteException;

    void activeUser(UserEntity user) throws RemoteException;

}
