package repository;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.ConferenceEntity;
import domain.EditionEntity;
import domain.SessionEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    String dateString = "2017/03/13";
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    Date date;

    @Before
    public void setUp() throws Exception {
        loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        repositoryEdition = new RepositoryEntity<>(EditionEntity.class, loader);
        repositorySession = new RepositoryEntity<>(SessionEntity.class,loader);
        EditionEntity new_edition = new EditionEntity(date,date,"location","bio",date,date,date,date);
        try {
            repositoryEdition.add(new_edition);
        }catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(),"Unable to add element to database!");
        }
        try{
            date = dateFormat.parse(dateString);
        }catch (ParseException ex){}
    }

    @After
    public void tearDown() throws Exception {
        loader.getFactory().close();
    }

    @Test
    @Transactional
    public void add() throws Exception {
        SessionEntity user = new SessionEntity("session",date,date,"room_1","bio",100);
        try {
            Integer idUser = repositorySession.add(user);
            user.setEdition(repositoryEdition.getElementById(1));
            Assert.assertTrue(idUser.equals(1) &&
                    user.getLocation().equals("room_1") &&
                    user.getBio().equals("bio") &&
                    user.getName().equals("session") &&
                    user.getStartDate().equals(date) &&
                    user.getStartDate().equals(date) &&
                    user.getEdition().getBio().equals("bio") &&
                    user.getSeats().equals(100)
            );
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void update() throws Exception {
        SessionEntity user = new SessionEntity("session",date,date,"room_1","bio",100);
        SessionEntity update = new SessionEntity("session",date,date,"room_2","bio",100);
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
        SessionEntity user = new SessionEntity("session",date,date,"room_1","bio",100);
        try {
            repositorySession.add(user);

            // This test is here only to make sure that we have something in repository in order to delete.
            Assert.assertTrue(user.getName().equals(repositorySession.getElementById(1).getName()));
            repositorySession.delete(user.getId());
            Assert.assertTrue(repositorySession.getAll().isEmpty());
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAll() throws Exception {
        SessionEntity user = new SessionEntity("session",date,date,"room_1","bio",100);
        SessionEntity test = new SessionEntity("session",date,date,"room_2","bio",100);
        EditionEntity new_edition = new EditionEntity(date,date,"location","bio",date,date,date,date);

        try {
            repositoryEdition.add(new_edition);
            user.setEdition(repositoryEdition.getElementById(1));
            test.setEdition(repositoryEdition.getElementById(1));
            repositorySession.add(user);
            repositorySession.add(test);
            ArrayList<SessionEntity> result = new ArrayList<>(repositorySession.getAll());
            Assert.assertTrue(result.get(0).getId().equals(user.getId()) &&
                    result.get(1).getId().equals(test.getId())
            );
           EditionEntity same_edition = repositoryEdition.getElementById(1);
            Set<SessionEntity> sessions = same_edition.getSessions();
            Assert.assertTrue(sessions.size()==2);
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getElementById() throws Exception {
        SessionEntity user = new SessionEntity("session",date,date,"room_1","bio",100);
        SessionEntity test = new SessionEntity("session",date,date,"room_2","bio",100);
        try {
            repositorySession.add(user);
            repositorySession.add(test);
            SessionEntity result = repositorySession.getElementById(1);
            Assert.assertTrue(result.getId().equals(user.getId()) &&
                    result.getLocation().equals(user.getLocation()));
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }


}