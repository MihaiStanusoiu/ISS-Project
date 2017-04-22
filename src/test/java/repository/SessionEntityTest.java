package repository;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.EditionEntity;
import domain.SessionEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

/**
 * Name:         {ClassName}
 * Effect:       {ClassEffect}
 * Date:         22/04/2017
 * Tested:       False
 *
 * @author Tiron Andreea- Ecaterina
 * @version 1.0
 */

public class SessionEntityTest {

    private RepositoryInterface<EditionEntity, Integer> repositoryEdition;
    private RepositoryInterface<SessionEntity,Integer> repositorySession;
    private DatabaseLoaderInterface loader;
    private Date date;

    @Before
    public void setUp() throws Exception {
        date = new Date();
        loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        repositoryEdition = new RepositoryEntity<>(EditionEntity.class, loader);
        repositorySession = new RepositoryEntity<>(SessionEntity.class,loader);
        EditionEntity newEdition = new EditionEntity(date, date,"location","bio", date, date, date, date);
        repositoryEdition.add(newEdition);
    }

    @After
    public void tearDown() throws Exception {
        loader.getFactory().close();
    }

    @Test
    @Transactional
    public void add() throws Exception {
        SessionEntity session = new SessionEntity("session", date, date, "room_1", "bio", 100);
        try {
            Integer idSession = repositorySession.add(session);
            session.setEdition(repositoryEdition.getElementById(1));
            Assert.assertTrue(idSession.equals(1) &&
                    session.getLocation().equals("room_1") &&
                    session.getBio().equals("bio") &&
                    session.getName().equals("session") &&
                    session.getStartDate().equals(date) &&
                    session.getStartDate().equals(date) &&
                    session.getEdition().getBio().equals("bio") &&
                    session.getSeats().equals(100)
            );
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void update() throws Exception {
        SessionEntity user = new SessionEntity("session", date, date, "room_1", "bio", 100);
        SessionEntity update = new SessionEntity("session", date, date, "room_2", "bio", 100);
        try {
            repositorySession.add(user);
            repositorySession.update(user, update);
            SessionEntity result = repositorySession.getElementById(user.getId());
            Assert.assertTrue(result.getLocation().equals("room_2"));
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void delete() throws Exception {
        SessionEntity session = new SessionEntity("session", date, date, "room_1", "bio", 100);
        try {
            repositorySession.add(session);
            // This test is here only to make sure that we have something in repository in order to delete.
            Assert.assertTrue(session.getLocation().equals(repositorySession.getElementById(1).getLocation()));
            repositorySession.delete(session.getId());
            Assert.assertTrue(repositorySession.getAll().isEmpty());
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAll() throws Exception {
        SessionEntity session = new SessionEntity("session", date, date, "room_1", "bio", 100);
        SessionEntity test = new SessionEntity("session", date, date, "room_2", "bio", 100);
        EditionEntity newEdition = new EditionEntity(date, date, "location", "bio", date, date, date, date);
        try {
            repositoryEdition.add(newEdition);
            session.setEdition(repositoryEdition.getElementById(1));
            test.setEdition(repositoryEdition.getElementById(1));
            repositorySession.add(session);
            repositorySession.add(test);
            ArrayList<SessionEntity> result = new ArrayList<>(repositorySession.getAll());
            Assert.assertTrue(result.get(0).getId().equals(session.getId()) &&
                    result.get(1).getId().equals(test.getId())
            );
            EditionEntity sameEdition = repositoryEdition.getElementById(1);
            Set<SessionEntity> sessions = sameEdition.getSessions();
            Assert.assertTrue(sessions.size()==2);
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getElementById() throws Exception {
        SessionEntity session = new SessionEntity("session", date, date, "room_1", "bio", 100);
        SessionEntity test = new SessionEntity("session", date, date, "room_2", "bio", 100);
        try {
            repositorySession.add(session);
            repositorySession.add(test);
            SessionEntity result = repositorySession.getElementById(1);
            Assert.assertTrue(result.getId().equals(session.getId()) &&
                    result.getLocation().equals(session.getLocation()));
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }


}