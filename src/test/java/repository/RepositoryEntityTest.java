package repository;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domaintest.UserEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.transaction.Transactional;
import java.util.ArrayList;

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

    private RepositoryInterface<UserEntity, Integer> repositoryUser;
    private DatabaseLoaderInterface loader;

    @Before
    public void setUp() throws Exception {
        loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        repositoryUser = new RepositoryEntity<>(UserEntity.class, loader);
    }

    @After
    public void tearDown() throws Exception {
        loader.getFactory().close();
    }

    @Test
    @Transactional
    public void add() throws Exception {
        UserEntity user = new UserEntity("test", "testPassword", "test@test.com", "Test");
        try {
            Integer idUser = repositoryUser.add(user);
            Assert.assertTrue(idUser.equals(1) &&
                    user.getUsername().equals("test") &&
                    user.getPassword().equals("testPassword") &&
                    user.getEmail().equals("test@test.com") &&
                    user.getName().equals("Test") &&
                    user.getBio().equals("")
            );
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void update() throws Exception {
        UserEntity user = new UserEntity("test", "testPassword", "test@test.com", "Test");
        UserEntity update = new UserEntity("testUpdate", "testPassword", "test@test.com", "Test");
        try {
            repositoryUser.add(user);
            repositoryUser.update(user, update);
            Assert.assertTrue(user.getName().equals(update.getName()));
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void delete() throws Exception {
        UserEntity user = new UserEntity("test", "testPassword", "test@test.com", "Test");
        try {
            repositoryUser.add(user);
            // This test is here only to make sure that we have something in repository in order to delete.
            Assert.assertTrue(user.getName().equals(repositoryUser.getElementById(1).getName()));
            repositoryUser.delete(user.getId());
            Assert.assertTrue(repositoryUser.getAll().isEmpty());
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAll() throws Exception {
        UserEntity user = new UserEntity("test", "testPassword", "test@test.com", "Test");
        UserEntity test = new UserEntity("test", "testPassword", "test@test.com", "Test");
        try {
            repositoryUser.add(user);
            repositoryUser.add(test);
            ArrayList<UserEntity> result = new ArrayList<>(repositoryUser.getAll());
            Assert.assertTrue(result.get(0).getId().equals(user.getId()) &&
                    result.get(1).getId().equals(test.getId()));
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getElementById() throws Exception {
        UserEntity user = new UserEntity("test", "testPassword", "test@test.com", "Test");
        UserEntity test = new UserEntity("testName", "testPassword", "test@test.com", "Test");
        try {
            repositoryUser.add(user);
            repositoryUser.add(test);
            UserEntity result = repositoryUser.getElementById(1);
            Assert.assertTrue(result.getId().equals(user.getId()) &&
                    result.getName().equals(user.getName()));
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

}