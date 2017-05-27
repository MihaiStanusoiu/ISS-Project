package service;

import domain.UserEntity;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface CollectionService extends Remote {

    LoginService getLoginService() throws RemoteException;

    SignUpService getSignUpService() throws RemoteException;

    UserEntity getActiveUser() throws RemoteException;

    void setActiveUser(UserEntity activeUser) throws RemoteException;

}
