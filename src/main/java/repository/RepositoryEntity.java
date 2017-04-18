package repository;

import database.DatabaseLoaderInterface;
import domaintest.Idable;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 * Name:         RepositoryEntity
 * Effect:       Generic repository for entity objects.
 * Date:         4/16/2017
 * Tested:       True
 *
 * @author      Alexandru Stoica
 * @version     1.0
 */

public class RepositoryEntity<T extends Idable<Id>, Id extends Serializable>
        implements RepositoryInterface<T, Id> {

    /**
     * The database loader.
     * Needed to load the database configuration for our repository.
     */
    private final DatabaseLoaderInterface loader;

    /**
     * The item's class.
     * We need this attribute in order to know the item's class in our searching methods.
     */
    private Class<T> repositoryClass;

    /**
     * This generic repository needs to know
     * the item's class in order to comunitate with Hibernate.
     * @param repositoryClass The item's class.
     * @param loader The database loader.
     */
    @SuppressWarnings("all")
    public RepositoryEntity(Class<T> repositoryClass, final DatabaseLoaderInterface loader) {
        this.loader = loader;
        this.repositoryClass = repositoryClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Id add(T element) throws RepositoryException {
        Session session = loader.getFactory().openSession();
        session.beginTransaction();
        session.save(element);
        session.getTransaction().commit();
        session.close();
        return element.getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(T element, T with) throws RepositoryException {
        Session session = loader.getFactory().openSession();
        with.setId(element.getId());
        session.beginTransaction();
        session.update(with);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T delete(Id id) throws RepositoryException {
        Session session = loader.getFactory().openSession();
        session.beginTransaction();
        T element = this.getElementById(id);
        session.delete(element);
        session.getTransaction().commit();
        session.close();
        return element;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> getAll() throws RepositoryException {
        Session session = loader.getFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(repositoryClass);
        Root<T> root = criteriaQuery.from(repositoryClass);
        criteriaQuery.select(root);
        Query resultQuery = session.createQuery(criteriaQuery);
        List<T> result = resultQuery.getResultList();
        session.close();
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getElementById(Id id) throws RepositoryException {
        Session session = loader.getFactory().openSession();
        T element = session.get(repositoryClass, id);
        session.close();
        return element;
    }

}
