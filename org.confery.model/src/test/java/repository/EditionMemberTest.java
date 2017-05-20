package repository;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.*;
import exception.SystemException;
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
 * Date:         08/05/2017
 * @author       Tiron Andreea- Ecaterina
 * @version      1.0
 */
public class EditionMemberTest {

    private RepositoryInterface<ConfigurationEditionMemberEntity, Integer> repositoryEditionConfiguration;
    private RepositoryInterface<EditionMemberEntity, Integer> repositoryEditionMember;
    private RepositoryInterface<EditionEntity, Integer> repositoryEdition;
    private RepositoryInterface<ConferenceEntity,Integer> repositoryConference;
    private RepositoryInterface<UserEntity,Integer> repositoryUser;
    private DatabaseLoaderInterface loader;
    private Date date;
    private ConferenceEntity newConference;
    private EditionEntity edition;
    private UserEntity user;
    private UserEntity user2;
    private ConfigurationEditionMemberEntity userConfiguration;

    @Before
    public void setUp() throws Exception {
        date = new Date();
        loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        repositoryEditionMember = new RepositoryEntity<>(EditionMemberEntity.class, loader);
        repositoryEdition = new RepositoryEntity<>(EditionEntity.class, loader);
        repositoryConference = new RepositoryEntity<>(ConferenceEntity.class, loader);
        repositoryUser = new RepositoryEntity<>(UserEntity.class, loader);
        repositoryEditionConfiguration = new RepositoryEntity<>(ConfigurationEditionMemberEntity.class, loader);
        newConference = new ConferenceEntity("International Engineering Conference", "IEC");
        repositoryConference.add(newConference);
        edition = new EditionEntity(date, date, "location", "bio", date, date, date, date);
        repositoryEdition.add(edition);
        edition.setConference(newConference);
        user = new UserEntity("user", "password", "email", "name", "website", "bio", "location");
        user2 = new UserEntity("username", "password", "email", "name", "website", "bio", "location");
        repositoryUser.add(user);
        repositoryUser.add(user2);
        userConfiguration = new ConfigurationEditionMemberEntity(false, true, true);
        repositoryEditionConfiguration.add(userConfiguration);
    }

    @After
    public void tearDown() throws Exception {
        loader.getFactory().close();
    }

    @Test
    @Transactional
    public void add() throws Exception {
        EditionMemberEntity member = new EditionMemberEntity();
        member.setEdition(edition);
        member.setUser(user);
        member.setConfigurationEditionMember(userConfiguration);
        try {
            Integer idMember = repositoryEditionMember.add(member);
            Assert.assertTrue(idMember.equals(1) &&
                    member.getEdition().getLocation().equals("location") &&
                    member.getUser().getPassword().equals("password") &&
                    member.getUser().getUsername().equals("user") &&
                    member.getConfigurationEditionMember().getCoChair().equals(true)
            );
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void update() throws Exception {
        EditionMemberEntity member = new EditionMemberEntity();
        member.setEdition(edition);
        member.setUser(user);
        member.setConfigurationEditionMember(userConfiguration);
        EditionMemberEntity update = new EditionMemberEntity();
        update.setEdition(edition);
        update.setUser(user2);
        update.setConfigurationEditionMember(userConfiguration);
        try {
            repositoryEditionMember.add(member);
            repositoryEditionMember.update(member, update);
            EditionMemberEntity result = repositoryEditionMember.getElementById(member.getId());
            Assert.assertTrue(result.getUser().getUsername().equals("username"));
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void delete() throws Exception {
        EditionMemberEntity member = new EditionMemberEntity();
        member.setEdition(edition);
        member.setUser(user);
        member.setConfigurationEditionMember(userConfiguration);
        try {
            repositoryEditionMember.add(member);
            // This test is here only to make sure that we have something in repository in order to delete.
            Assert.assertTrue(member.getEdition().getLocation().equals(repositoryEditionMember.getElementById(1).getEdition().getLocation()));
            repositoryEditionMember.delete(member.getId());
            Assert.assertTrue(repositoryEditionMember.getAll().isEmpty());
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAll() throws Exception {
        EditionMemberEntity member = new EditionMemberEntity();
        member.setEdition(edition);
        member.setUser(user);
        member.setConfigurationEditionMember(userConfiguration);
        EditionMemberEntity test = new EditionMemberEntity();
        test.setEdition(edition);
        test.setUser(user2);
        test.setConfigurationEditionMember(userConfiguration);
        try {
            repositoryEditionMember.add(member);
            repositoryEditionMember.add(test);
            ArrayList<EditionMemberEntity> result = new ArrayList<>(repositoryEditionMember.getAll());
            Assert.assertTrue(result.get(0).getId().equals(member.getId()) &&
                    result.get(1).getId().equals(test.getId()));
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getElementById() throws Exception {
        EditionMemberEntity member = new EditionMemberEntity();
        member.setEdition(edition);
        member.setUser(user);
        member.setConfigurationEditionMember(userConfiguration);
        EditionMemberEntity test = new EditionMemberEntity();
        test.setEdition(edition);
        test.setUser(user2);
        test.setConfigurationEditionMember(userConfiguration);
        try {
            repositoryEditionMember.add(member);
            repositoryEditionMember.add(test);
            EditionMemberEntity result = repositoryEditionMember.getElementById(1);
            Assert.assertTrue(result.getId().equals(member.getId()) &&
                    result.getUser().getUsername().equals(member.getUser().getUsername()));
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAllFromUser() throws Exception {
        EditionMemberEntity member1 = new EditionMemberEntity();
        EditionMemberEntity member2 = new EditionMemberEntity();
        EditionMemberEntity member3 = new EditionMemberEntity();
        try{
            member1.setUser(repositoryUser.getElementById(1));
            member2.setUser(repositoryUser.getElementById(1));
            member3.setUser(repositoryUser.getElementById(1));
            repositoryEditionMember.add(member1);
            repositoryEditionMember.add(member2);
            repositoryEditionMember.add(member3);
            UserEntity user0 = repositoryUser.getElementById(1);
            Set<EditionMemberEntity> editionMembers = user0.getEditionMembers();
            Assert.assertTrue(editionMembers.size() == 3);
        }catch (SystemException exception) {
        Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAllFromConfiguration() throws Exception {
        EditionMemberEntity member1 = new EditionMemberEntity();
        EditionMemberEntity member2 = new EditionMemberEntity();
        EditionMemberEntity member3 = new EditionMemberEntity();
        try{
            member1.setConfigurationEditionMember(repositoryEditionConfiguration.getElementById(1));
            member2.setConfigurationEditionMember(repositoryEditionConfiguration.getElementById(1));
            member3.setConfigurationEditionMember(repositoryEditionConfiguration.getElementById(1));
            repositoryEditionMember.add(member1);
            repositoryEditionMember.add(member2);
            repositoryEditionMember.add(member3);
            ConfigurationEditionMemberEntity configuration0 = repositoryEditionConfiguration.getElementById(1);
            Set<EditionMemberEntity> editionMembers = configuration0.getEditionMembers();
            Assert.assertTrue(editionMembers.size() == 3);
        }catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAllFromEdition() throws Exception {
        EditionMemberEntity member1 = new EditionMemberEntity();
        EditionMemberEntity member2 = new EditionMemberEntity();
        EditionMemberEntity member3 = new EditionMemberEntity();
        try{
            member1.setEdition(repositoryEdition.getElementById(1));
            member2.setEdition(repositoryEdition.getElementById(1));
            member3.setEdition(repositoryEdition.getElementById(1));
            repositoryEditionMember.add(member1);
            repositoryEditionMember.add(member2);
            repositoryEditionMember.add(member3);
            EditionEntity edition0 = repositoryEdition.getElementById(1);
            Set<EditionMemberEntity> editionMembers = edition0.getMembers();
            Assert.assertTrue(editionMembers.size() == 3);
        }catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }


}