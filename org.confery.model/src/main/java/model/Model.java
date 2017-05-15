package model;

import database.DatabaseLoaderInterface;
import domain.Idable;
import exception.RepositoryException;
import repository.RepositoryEntity;
import repository.RepositoryInterface;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class Model<T extends Idable<Id>, Id extends Serializable>
        implements ModelInterface<T, Id> {

    protected RepositoryInterface<T, Id> repository;

    protected Model(Class<T> entityClass, DatabaseLoaderInterface loader) {
        repository = new RepositoryEntity<>(entityClass, loader);
    }

    @Override
    public Id add(T element) throws RepositoryException {
        return repository.add(element);
    }

    @Override
    public T delete(T element) throws RepositoryException {
        return repository.delete(element.getId());
    }

    @Override
    public void update(T element, T with) throws RepositoryException {
        repository.update(element, with);
    }

    @Override
    public T getElementById(Id id) throws RepositoryException {
        return repository.getElementById(id);
    }

    @Override
    public List<T> getAll() throws RepositoryException {
        return repository.getAll();
    }

}
