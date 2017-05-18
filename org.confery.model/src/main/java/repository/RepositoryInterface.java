package repository;

import domain.Idable;
import exception.SystemException;

import java.io.Serializable;
import java.util.List;

/**
 * Name:         RepositoryInterface
 * Effect:       Defines a generic repository for our system.
 * Date:         4/16/2017
 * Tested:       True
 *
 * @author      Alexandru Stoica
 * @version     1.0
 */

public interface RepositoryInterface<T extends Idable<Id>, Id extends Serializable> {

    /**
     * @param element The element we need to add to database.
     * @return The id of the new element in the database.
     * @throws SystemException If the action failed.
     */
    Id add(T element) throws SystemException;

    /**
     * @param element The old element from our system.
     * @param with The element with the updated data.
     *             (ignore the id primary key property)
     * @throws SystemException If the action fails
     */
    void update(T element, T with) throws SystemException;

    /**
     * @param id The object's id.
     * @return The deleted item from our system.
     * @throws SystemException If the action fails.
     */
    T delete(Id id) throws SystemException;

    /**
     * @return All the items type T from our database.
     */
    List<T> getAll();

    /**
     * @param id The object's id.
     * @return The wanted object based on the given id.
     * @throws SystemException If the object is not found.
     */
    T getElementById(Id id) throws SystemException;

}
