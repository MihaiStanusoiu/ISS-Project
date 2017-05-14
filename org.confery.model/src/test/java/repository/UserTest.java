package repository;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.NotificationEntity;
import domain.UserEntity;
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
 * Date:         08/05/2017
 * @author       Tiron Andreea- Ecaterina
 * @version      1.0
 */
public class UserTest {
    private RepositoryInterface<UserEntity, Integer> repositoryUser;
    private RepositoryInterface<NotificationEntity, Integer> repositoryNotification;
    private DatabaseLoaderInterface loader;
    private UserEntity user;
    private UserEntity user2;
    private NotificationEntity note;
    private NotificationEntity note2;
    private NotificationEntity note3;


    @Before
    public void setUp() throws Exception {
        loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        repositoryUser = new RepositoryEntity<>(UserEntity.class, loader);
        repositoryNotification = new RepositoryEntity<>(NotificationEntity.class, loader);
    }

    @After
    public void tearDown() throws Exception {
        loader.getFactory().close();
    }

    @Test
    @Transactional
    public void add() throws Exception {
        user = new UserEntity("username", "password", "email", "name", "website", "bio", "location");
        try {
            Integer idUser = repositoryUser.add(user);
            Assert.assertTrue(idUser.equals(1) &&
                    user.getName().equals("name") &&
                    user.getUsername().equals("username") &&
                    user.getPassword().equals("password") &&
                    user.getLocation().equals("location") &&
                    user.getBio().equals("bio") &&
                    user.getWebsite().equals("website") &&
                    user.getEmail().equals("email")
            );
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void update() throws Exception {
        user = new UserEntity("username", "password", "email", "name", "website", "bio", "location");
        user2 = new UserEntity("username2", "password2", "email2", "name2", "website2", "bio2", "location2");
        try {
            repositoryUser.add(user);
            repositoryUser.update(user, user2);
            UserEntity result = repositoryUser.getElementById(user.getId());
            Assert.assertTrue(result.getUsername().equals("username2"));
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void delete() throws Exception {
        user = new UserEntity("username", "password", "email", "name", "website", "bio", "location");
        try {
            repositoryUser.add(user);
            // This test is here only to make sure that we have something in repository in order to delete.
            Assert.assertTrue(user.getEmail().equals(repositoryUser.getElementById(1).getEmail()));
            repositoryUser.delete(user.getId());
            Assert.assertTrue(repositoryUser.getAll().isEmpty());
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAll() throws Exception {
        user = new UserEntity("username", "password", "email", "name", "website", "bio", "location");
        user2 = new UserEntity("username2", "password2", "email2", "name2", "website2", "bio2", "location2");
        try {
            repositoryUser.add(user);
            repositoryUser.add(user2);
            ArrayList<UserEntity> result = new ArrayList<>(repositoryUser.getAll());
            Assert.assertTrue(result.get(0).getId().equals(user.getId()) &&
                    result.get(1).getId().equals(user2.getId()));
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getElementById() throws Exception {
        user = new UserEntity("username", "password", "email", "name", "website", "bio", "location");
        user2 = new UserEntity("username2", "password2", "email2", "name2", "website2", "bio2", "location2");
        try {
            repositoryUser.add(user);
            repositoryUser.add(user2);
            UserEntity result = repositoryUser.getElementById(1);
            Assert.assertTrue(result.getId().equals(user.getId()) &&
                    result.getName().equals(user.getName()));
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAllNotifications() throws Exception {
        user = new UserEntity("username", "password", "email", "name", "website", "bio", "location");
        note = new NotificationEntity("payment", false);
        note2 = new NotificationEntity("subscription", true);
        note3 = new NotificationEntity("late", true);
        try{
            repositoryUser.add(user);
            note.setUser(user);
            repositoryNotification.add(note);
            note2.setUser(user);
            repositoryNotification.add(note2);
            note3.setUser(user);
            repositoryNotification.add(note3);

            Boolean isNote = false;
            Boolean isNote2 = false;
            Boolean isNote3 = false;
            ArrayList<NotificationEntity> notifications = new ArrayList<>(repositoryUser.getElementById(1).getNotifications());
            for (NotificationEntity notification : notifications) {
                if (notification.getText().equals("payment"))
                    isNote = true;
                if (notification.getText().equals("subscription"))
                    isNote2 = true;
                if (notification.getText().equals("late"))
                    isNote3 = true;
            }
            Assert.assertTrue(isNote.equals(true) && isNote2.equals(true) && isNote3.equals(true));
            Assert.assertTrue(notifications.size() == 3);
        }catch(RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }
}