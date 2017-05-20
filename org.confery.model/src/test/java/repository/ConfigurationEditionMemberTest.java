package repository;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.ConfigurationEditionMemberEntity;
import domain.EditionMemberEntity;
import exception.SystemException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Set;

/**
 * Name:         ConfigurationEditionMemberTest
 * Effect:       Test for Configuration Edition Member
 * Date:         21/04/2017
 * @author       Tiron Andreea- Ecaterina
 * @version      1.0
 */

public class ConfigurationEditionMemberTest {
    private RepositoryInterface<EditionMemberEntity, Integer> repositoryEdition;
    private RepositoryInterface<ConfigurationEditionMemberEntity, Integer> repositoryEditionConfiguration;
    private DatabaseLoaderInterface loader;

    @Before
    public void setUp() throws Exception {
        loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        repositoryEditionConfiguration = new RepositoryEntity<>(ConfigurationEditionMemberEntity.class, loader);
        repositoryEdition = new RepositoryEntity<>(EditionMemberEntity.class, loader);
    }

    @After
    public void tearDown() throws Exception {
        loader.getFactory().close();
    }

    @Test
    @Transactional
    public void add() throws Exception {
        ConfigurationEditionMemberEntity user = new ConfigurationEditionMemberEntity(false, true, true);
        try {
            Integer idUser = repositoryEditionConfiguration.add(user);
            Assert.assertTrue(idUser.equals(1) &&
                    user.getChair().equals(false) &&
                    user.getCoChair().equals(true) &&
                    user.getPcMember().equals(true)
            );
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void update() throws Exception {
        ConfigurationEditionMemberEntity user = new ConfigurationEditionMemberEntity(false, true, true);
        ConfigurationEditionMemberEntity update = new ConfigurationEditionMemberEntity(true, true, true);
        try {
            repositoryEditionConfiguration.add(user);
            repositoryEditionConfiguration.update(user, update);
            ConfigurationEditionMemberEntity result = repositoryEditionConfiguration.getElementById(user.getId());
            Assert.assertTrue(result.getChair().equals(true));
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void delete() throws Exception {
        ConfigurationEditionMemberEntity member = new ConfigurationEditionMemberEntity(false, true, true);
        try {
            repositoryEditionConfiguration.add(member);
            // This test is here only to make sure that we have something in repository in order to delete.
            Assert.assertTrue(member.getChair()
                    .equals(repositoryEditionConfiguration.getElementById(1).getChair()));
            repositoryEditionConfiguration.delete(member.getId());
            Assert.assertTrue(repositoryEditionConfiguration.getAll().isEmpty());
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAll() throws Exception {
        ConfigurationEditionMemberEntity user = new ConfigurationEditionMemberEntity(false, true, true);
        ConfigurationEditionMemberEntity test = new ConfigurationEditionMemberEntity(true, true, true);
        try {
            repositoryEditionConfiguration.add(user);
            repositoryEditionConfiguration.add(test);
            ArrayList<ConfigurationEditionMemberEntity> result = new ArrayList<>(repositoryEditionConfiguration.getAll());
            Assert.assertTrue(result.get(0).getId().equals(user.getId()) &&
                    result.get(1).getId().equals(test.getId()));
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getElementById() throws Exception {
        ConfigurationEditionMemberEntity user = new ConfigurationEditionMemberEntity(false, true, true);
        ConfigurationEditionMemberEntity test = new ConfigurationEditionMemberEntity(true, true, true);
        try {
            repositoryEditionConfiguration.add(user);
            repositoryEditionConfiguration.add(test);
            ConfigurationEditionMemberEntity result = repositoryEditionConfiguration.getElementById(1);
            Assert.assertTrue(result.getId().equals(user.getId()) &&
                    result.getChair().equals(user.getChair()));
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAllEditionMemberConfigurations() throws Exception {
        EditionMemberEntity memberSession1 = new EditionMemberEntity();
        EditionMemberEntity memberSession2 = new EditionMemberEntity();
        ConfigurationEditionMemberEntity config = new ConfigurationEditionMemberEntity(true,true,true);
        try {
            repositoryEditionConfiguration.add(config);
            memberSession1.setConfigurationEditionMember(repositoryEditionConfiguration.getElementById(1));
            memberSession2.setConfigurationEditionMember(repositoryEditionConfiguration.getElementById(1));
            repositoryEdition.add(memberSession1);
            repositoryEdition.add(memberSession2);

            ConfigurationEditionMemberEntity newConfig = repositoryEditionConfiguration.getElementById(1);
            Set<EditionMemberEntity> sessions = newConfig.getEditionMembers();
            Assert.assertTrue(sessions.size() == 2);
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }


}