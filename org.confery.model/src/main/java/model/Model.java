package model;

import database.DatabaseLoaderInterface;
import domain.Idable;
import exception.SystemException;
import protocol.ModelInterface;
import repository.RepositoryEntity;
import repository.RepositoryInterface;

import java.io.Serializable;
import java.util.List;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */


public class Model<T extends Idable<Id>, Id extends Serializable> implements ModelInterface<T, Id> {

    protected RepositoryInterface<T, Id> repository;
    private DatabaseLoaderInterface loader;

    protected Model(Class<T> entityClass, DatabaseLoaderInterface loader) {
        this.loader = loader;
        repository = new RepositoryEntity<>(entityClass, loader);
    }

    public DatabaseLoaderInterface getLoader() {
        return loader;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Id add(T element) throws SystemException {
        return repository.add(element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T delete(T element) throws SystemException {
        return repository.delete(element.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(T element, T with) throws SystemException {
        repository.update(element, with);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getElementById(Id id) throws SystemException {
        return repository.getElementById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getAll() {
        return repository.getAll();
    }

}
