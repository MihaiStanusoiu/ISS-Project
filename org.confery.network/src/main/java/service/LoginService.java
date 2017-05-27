package service;

import domain.UserEntity;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface LoginService extends Remote, Service {

    UserEntity login(String username, String password) throws RemoteException;

}
