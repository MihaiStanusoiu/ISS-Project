package repository;

import java.util.List;

/**
 * Name:         RepositoryInterface
 * Effect:       Defines how a repository should behave.
 *               WIP: Don't use this interface yet.
 * Date:         4/16/2017
 * Tested:       WIP
 *
 * @author      Alexandru Stoica
 * @version     1.0
 */

public interface RepositoryInterface<T, Id> {

    /**
     * @param element The element we need to add to database.
     * @return The id of the new element in the database.
     * @throws RepositoryException If the action failed.
     */
    Id add(T element) throws RepositoryException;

    void update(T element, T with) throws RepositoryException;
    T delete(Id id) throws RepositoryException;
    List<T> getAll() throws RepositoryException;
    T getElementById() throws RepositoryException;

}
