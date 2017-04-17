package repository;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.Notification;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.transaction.Transactional;

/**
 * Name:         RepositoryEntityTest
 * Effect:       Test for generic repository.
 *               <p>
 *                   Requires mySql server to run in the background.
 *               </p>
 * Date:         4/16/201
 *
 * @author       Alexandru Stoica
 * @version      1.0
 */

public class RepositoryEntityTest {

    private RepositoryInterface<Notification, Integer> repository;
    private DatabaseLoaderInterface loader;

    @Before
    public void setUp() throws Exception {
        loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        repository = new RepositoryEntity<>(loader);
    }

    @After
    public void tearDown() throws Exception {
        loader.getFactory().close();
    }

    @Test
    @Transactional
    public void add() throws Exception {
        Notification notification = new Notification();
        notification.setText("Test");
        notification.setPaymentType(Boolean.FALSE);
        try {
            repository.add(notification);
            Assert.assertTrue(notification.getId().equals(1));
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void getAll() throws Exception {

    }

    @Test
    public void getElementById() throws Exception {

    }

}