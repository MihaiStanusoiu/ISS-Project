package service;

import domain.UserEntity;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface SignUpService extends Remote {

    UserEntity signUp(String username, String password, String confirm, String email, String name)
            throws RemoteException;

    void setActiveUser(UserEntity activeUser) throws RemoteException;

}
