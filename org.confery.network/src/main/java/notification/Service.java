package notification;

import domain.Idable;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface Service<T extends Idable<Id>, Id extends Serializable> extends Remote {

    Id add(T element) throws RemoteException;

    T update(T element, T with) throws RemoteException;

    T delete(T element) throws RemoteException;

    T getElementById(Id id) throws RemoteException;

    List<T> getAll() throws RemoteException;

}
