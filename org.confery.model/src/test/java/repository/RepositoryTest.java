package repository;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.NotificationEntity;
import domain.UserEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Tiron Andreea- Ecaterina
 * @version 1.0
 */

public class RepositoryTest {

    private RepositoryInterface<UserEntity, Integer> repositoryUser;
    private RepositoryInterface<NotificationEntity, Integer> repositoryNotification;
    private DatabaseLoaderInterface loader;

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
        // declarations:
        UserEntity test = new UserEntity(1, "username", "password", "email",
                "name", "website", "bio", "location");
        UserEntity user = new UserEntity("username", "password", "email",
                "name", "website", "bio", "location");
        // when:
        Integer idUser = repositoryUser.add(user);
        // then:
        assertEquals(user, test);
    }

    @Test
    public void update() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("username", "password", "email",
                "name", "website", "bio", "location");
        UserEntity update = new UserEntity("usernameUpdate", "passwordUpdate", "emailUpdate",
                "nameUpdate", "websiteUpdate", "bioUpdate", "locationUpdate");
        // when:
        repositoryUser.add(user);
        repositoryUser.update(user, update);
        UserEntity result = repositoryUser.getElementById(user.getId());
        // then:
        assertEquals(result, update);
    }

    @Test
    public void delete() throws Exception {
        UserEntity user = new UserEntity("username", "password", "email",
                "name", "website", "bio", "location");
        // when:
        repositoryUser.add(user);
        // This test is here only to make sure that we have something in repository in order to delete.
        assertEquals(user, repositoryUser.getElementById(1));
        repositoryUser.delete(user.getId());
        assertTrue(repositoryUser.getAll().isEmpty());
    }

    @Test
    public void getAll() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("username", "password", "email",
                "name", "website", "bio", "location");
        UserEntity test = new UserEntity("test", "password", "email",
                "name", "website", "bio", "location");
        // when:
        repositoryUser.add(user);
        repositoryUser.add(test);
        List<UserEntity> result = repositoryUser.getAll();
        // then:
        assertTrue(result.get(0).equals(user) && result.get(1).equals(test) && result.size() == 2);
    }

    @Test
    public void getElementById() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("username", "password", "email", "name",
                "website", "bio", "location");
        UserEntity test = new UserEntity("test", "password", "email", "name",
                "website", "bio", "location");
        // when:
        repositoryUser.add(user);
        repositoryUser.add(test);
        // then:
        assertEquals(repositoryUser.getElementById(1), user);
    }

    @Test
    public void getAllNotifications() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("username", "password", "email", "name",
                "website", "bio", "location");
        NotificationEntity notificationFirst = new NotificationEntity("payment", false);
        NotificationEntity notificationSecond = new NotificationEntity("subscription", true);
        // when:
        repositoryUser.add(user);
        notificationFirst.setUser(user);
        repositoryNotification.add(notificationFirst);
        notificationSecond.setUser(user);
        repositoryNotification.add(notificationSecond);
        // then:
        Set<NotificationEntity> notifications = repositoryUser.getElementById(1).getNotifications();
        assertTrue(notifications.contains(notificationFirst) &&
                notifications.contains(notificationSecond) && notifications.size() == 2);
    }
}