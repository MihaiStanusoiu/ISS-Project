package service;

import domain.UserEntity;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface ServiceInterface extends Remote {

    void activeUser(UserEntity user) throws RemoteException;

}
