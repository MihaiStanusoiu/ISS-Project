package service;

import domain.Idable;
import exception.SystemException;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Vlad on 5/26/2017.
 */
public interface CRUDService <T extends Idable<Id>, Id extends Serializable> extends Service, Remote{

    /**
     * Gives an element to a protocol to add it to the database.
     *
     * @param element The target element
     * @return The element's id from database
     * @throws RemoteException If the element is not valid
     */
    Id add (T element) throws RemoteException;

    /**
     * Gives an element to a protocol to delete it from the database.
     *
     * @param element The target element
     * @return The deleted element from database
     * @throws RemoteException If the element doesn't exist in the database
     */
    T delete (T element) throws RemoteException;

    /**
     * Gives to a protocol a :element: that has to be updated :with: another element given to the protocol
     *
     * @param element The target element
     * @param with    The target element with updated fields.
     * @throws RemoteException if the target element doesn't exist in the database
     */
    void update(T element, T with) throws RemoteException;

    /**
     * Gives a protocol the id of the element wanted.
     *
     * @param id The element's id
     * @return The wanted element
     * @throws RemoteException If the wanted element doesn't exist in the database
     */
    T getElementById(Id id) throws RemoteException;

    /**
     * @return All the objects type :T: returned by a protocol
     */
    List<T> getAll();
}
