package repository;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.NotificationEntity;
import exception.RepositoryException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.transaction.Transactional;
import java.util.ArrayList;


/**
 * Name:         {ClassName}
 * Effect:       {ClassEffect}
 * Date:         07/05/2017
 * @author Tiron Andreea- Ecaterina
 * @version 1.0
 */
public class NotificationTest {
    private RepositoryInterface<NotificationEntity, Integer> repositoryNotification;
    private DatabaseLoaderInterface loader;
    private NotificationEntity note;
    private NotificationEntity note2;

    @Before
    public void setUp() throws Exception {
        loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        repositoryNotification = new RepositoryEntity<>(NotificationEntity.class, loader);
    }

    @After
    public void tearDown() throws Exception {
        loader.getFactory().close();
    }

    @Test
    @Transactional
    public void add() throws Exception {
       note = new NotificationEntity("payment", false);
        try {
            Integer idNote = repositoryNotification.add(note);
            Assert.assertTrue(idNote.equals(1) &&
                 note.getText().equals("payment") &&
                 note.getPaymentType().equals(false)
            );
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void update() throws Exception {
        note = new NotificationEntity("payment", false);
        note2 = new NotificationEntity("subscription", true);
        try {
            repositoryNotification.add(note);
            repositoryNotification.update(note, note2);
            NotificationEntity result = repositoryNotification.getElementById(note.getId());
            Assert.assertTrue(result.getText().equals("subscription") && result.getPaymentType().equals(true));
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void delete() throws Exception {
        note = new NotificationEntity("payment", false);
        try {
            repositoryNotification.add(note);
            // This test is here only to make sure that we have something in repository in order to delete.
            Assert.assertTrue(note.getText().equals(repositoryNotification.getElementById(1).getText()));
            repositoryNotification.delete(note.getId());
            Assert.assertTrue(repositoryNotification.getAll().isEmpty());
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAll() throws Exception {
        note = new NotificationEntity("payment", false);
        note2 = new NotificationEntity("subscription", true);
        try {
            repositoryNotification.add(note);
            repositoryNotification.add(note2);
            ArrayList<NotificationEntity> result = new ArrayList<>(repositoryNotification.getAll());
            Assert.assertTrue(result.get(0).getId().equals(note.getId()) &&
                    result.get(1).getId().equals(note2.getId()) && result.get(0).getPaymentType().equals(false));
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getElementById() throws Exception {
        note = new NotificationEntity("payment", false);
        note2 = new NotificationEntity("subscription", true);
        try {
            repositoryNotification.add(note);
            repositoryNotification.add(note2);
            NotificationEntity result = repositoryNotification.getElementById(1);
            Assert.assertTrue(result.getId().equals(note.getId()) &&
                    result.getText().equals(note.getText()));
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

}