package service;

import transferable.Idable;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface EntityService<E extends Idable<Id>, Id> extends Remote {

    Id add(E entity) throws RemoteException;
    E remove(E entity)  throws RemoteException;
    E delete(Id id) throws RemoteException;
    void update(E entity, E with) throws RemoteException;
    E getEntityById(Id id) throws RemoteException;
    List<E> getAll() throws RemoteException;

}
