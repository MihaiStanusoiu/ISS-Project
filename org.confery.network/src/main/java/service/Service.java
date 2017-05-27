package service;

import domain.UserEntity;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface Service extends Remote {

    void setActiveUser(UserEntity user) throws RemoteException;

}
