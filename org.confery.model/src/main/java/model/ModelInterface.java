package model;

import domain.Idable;
import exception.RepositoryException;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface ModelInterface<T extends Idable<Id>, Id extends Serializable> {

    Id add(T element) throws RepositoryException;
    T delete(T element) throws RepositoryException;
    void update(T element, T with) throws  RepositoryException;
    T getElementById(Id id) throws RepositoryException;
    List<T> getAll() throws RepositoryException;

}
