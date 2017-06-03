package protocol;

import database.DatabaseLoaderInterface;
import domain.Idable;
import exception.SystemException;

import java.io.Serializable;
import java.util.List;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */


public interface ModelInterface<T extends Idable<Id>, Id extends Serializable> {

    DatabaseLoaderInterface getLoader();

    /**
     * Adds an element to database.
     *
     * @param element The target element
     * @return The element's id from database
     * @throws SystemException If the element is not valid
     */
    Id add(T element) throws SystemException;

    /**
     * Deletes an element from database.
     *
     * @param element The target element
     * @return The deleted element from database
     * @throws SystemException If the element doesn't exist in the database
     */
    T delete(T element) throws SystemException;

    /**
     * Updates an :element: :with: another element
     *
     * @param element The target element
     * @param with    The target element with updated fields.
     * @throws SystemException if the target element doesn't exist in the database
     * @apiNote The :with: object doesn't need to know the id of the :element:
     */
    void update(T element, T with) throws SystemException;

    /**
     * Returns an element based on it's id.
     *
     * @param id The element's id
     * @return The wanted element
     * @throws SystemException If the wanted element doesn't exist in the database
     */
    T getElementById(Id id) throws SystemException;

    /**
     * @return All the objects type :T: from database
     */
    List<T> getAll();

}
