package repository;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.ConfigurationSessionMemberEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.transaction.Transactional;
import java.util.ArrayList;


/**
 * Name:         ConfigurationSessionMemberTest
 * Effect:       Test for ConfigurationSessionMember
 * Date:         22/04/2017
 *
 * @author Tiron Andreea- Ecaterina
 * @version 1.0
 */

public class ConfigurationSessionMemberTest {

    private RepositoryInterface<ConfigurationSessionMemberEntity, Integer> repositorySessionMemberConfiguration;
    private DatabaseLoaderInterface loader;

    @Before
    public void setUp() throws Exception {
        loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        repositorySessionMemberConfiguration = new RepositoryEntity<>(ConfigurationSessionMemberEntity.class, loader);
    }

    @After
    public void tearDown() throws Exception {
        loader.getFactory().close();
    }

    @Test
    @Transactional
    public void add() throws Exception {
        ConfigurationSessionMemberEntity configuration = new ConfigurationSessionMemberEntity(false, false, true);
        try {
            Integer idConfiguration = repositorySessionMemberConfiguration.add(configuration);
            Assert.assertTrue(idConfiguration.equals(1) &&
                    configuration.getChair().equals(false) &&
                    configuration.getSpeaker().equals(false) &&
                    configuration.getListener().equals(true)
            );
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void update() throws Exception {
        ConfigurationSessionMemberEntity user = new ConfigurationSessionMemberEntity(false, false, true);
        ConfigurationSessionMemberEntity update = new ConfigurationSessionMemberEntity(false, true , false);
        try {
            repositorySessionMemberConfiguration.add(user);
            repositorySessionMemberConfiguration.update(user, update);
            ConfigurationSessionMemberEntity result = repositorySessionMemberConfiguration.getElementById(user.getId());
            Assert.assertTrue(result.getListener().equals(false));
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void delete() throws Exception {
        ConfigurationSessionMemberEntity member = new ConfigurationSessionMemberEntity(false, true, true);
        try {
            repositorySessionMemberConfiguration.add(member);
            // This test is here only to make sure that we have something in repository in order to delete.
            Assert.assertTrue(member.getChair()
                    .equals(repositorySessionMemberConfiguration.getElementById(1).getChair()));
            repositorySessionMemberConfiguration.delete(member.getId());
            Assert.assertEquals(repositorySessionMemberConfiguration.getAll().isEmpty(), true);
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAll() throws Exception {
        ConfigurationSessionMemberEntity configuration = new ConfigurationSessionMemberEntity(false, true, true);
        ConfigurationSessionMemberEntity test = new ConfigurationSessionMemberEntity(true, true, true);
        try {
            repositorySessionMemberConfiguration.add(configuration);
            repositorySessionMemberConfiguration.add(test);
            ArrayList<ConfigurationSessionMemberEntity> result = new ArrayList<>(repositorySessionMemberConfiguration.getAll());
            Assert.assertTrue(result.get(0).getId().equals(configuration.getId()) &&
                    result.get(1).getId().equals(test.getId()));
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getElementById() throws Exception {
        ConfigurationSessionMemberEntity configuration = new ConfigurationSessionMemberEntity(false, true, true);
        ConfigurationSessionMemberEntity test = new ConfigurationSessionMemberEntity(true, true, true);
        try {
            repositorySessionMemberConfiguration.add(configuration);
            repositorySessionMemberConfiguration.add(test);
            ConfigurationSessionMemberEntity result = repositorySessionMemberConfiguration.getElementById(1);
            Assert.assertTrue(result.getId().equals(configuration.getId()) &&
                    result.getChair().equals(configuration.getChair()));
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }
}