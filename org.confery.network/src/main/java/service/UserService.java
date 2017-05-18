package service;

import exception.SystemException;
import transferable.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface UserService extends Remote {

    Integer add(User user) throws RemoteException, SystemException;
    User delete(User user) throws RemoteException, SystemException;
    void update(User user, User with) throws RemoteException, SystemException;
    User getEntityById(Integer id) throws RemoteException, SystemException;
    List<User> getAll() throws RemoteException, SystemException;

}
