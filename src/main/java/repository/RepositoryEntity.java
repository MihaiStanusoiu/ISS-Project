package repository;

import database.DatabaseLoaderInterface;
import org.hibernate.Session;

import java.util.List;

/**
 * Name:         RepositoryEntity
 * Effect:       Generic repository for entity objects.
 *               <p>
 *                   Don't use this repository. [WIP]
 *               </p>
 * Date:         4/16/2017
 * Tested:       WIP
 *
 * @author      Alexandru Stoica
 * @version     1.0
 */

public class RepositoryEntity<T, Id> implements RepositoryInterface<T, Id> {

    private final DatabaseLoaderInterface loader;

    @SuppressWarnings("all")
    public RepositoryEntity(final DatabaseLoaderInterface loader) {
        this.loader = loader;
    }

    @Override
    public Id add(T element) throws RepositoryException {
        Session session = loader.getFactory().openSession();
        session.beginTransaction();
        session.save(element);
        session.getTransaction().commit();
        return null;
    }

    @Override
    public void update(T element, T with) throws RepositoryException {
    }

    @Override
    public T delete(Id id) throws RepositoryException {
        return null;
    }

    @Override
    public List<T> getAll() throws RepositoryException {
        return null;
    }

    @Override
    public T getElementById() throws RepositoryException {
        return null;
    }

}
