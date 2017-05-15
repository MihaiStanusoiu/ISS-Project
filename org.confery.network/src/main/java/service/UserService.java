package service;

import exception.RepositoryException;
import transferable.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface UserService extends Remote {

    Integer add(User user) throws RemoteException, RepositoryException;
    User delete(User user) throws RemoteException, RepositoryException;
    void update(User user, User with) throws RemoteException, RepositoryException;
    User getEntityById(Integer id) throws RemoteException, RepositoryException;
    List<User> getAll() throws RemoteException, RepositoryException;

}
