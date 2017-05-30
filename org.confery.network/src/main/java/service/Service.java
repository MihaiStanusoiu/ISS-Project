package service;

import domain.Idable;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface Service<T, Id extends Serializable> extends ServiceInterface {

    Id add(T element) throws RemoteException;

    T update(T element, T with) throws RemoteException;

    T delete(T element) throws RemoteException;

    T getElementById(Id id) throws RemoteException;

    List<T> getAll() throws RemoteException;

}
