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
 * @author Tiron Andreea-Ecaterina
 * @version 1.0
 */
public class SessionMemberTest {

    private RepositoryInterface<UserEntity, Integer> repositoryUser;
    private RepositoryInterface<SessionMemberEntity, Integer> repositorySessionMember;
    private RepositoryInterface<ConferenceEntity, Integer> repositoryConference;
    private RepositoryInterface<EditionEntity, Integer> repositoryEdition;
    private RepositoryInterface<SessionEntity, Integer> repositorySession;
    private RepositoryInterface<ConfigurationSessionMemberEntity, Integer> repositorySessionMemberConfiguration;
    private DatabaseLoaderInterface loader;
    private Date date;
    private ConferenceEntity conference;
    private EditionEntity edition;
    private SessionEntity session;
    private UserEntity user;
    private ConfigurationSessionMemberEntity configuration;
    private ConfigurationSessionMemberEntity configuration2;

    @Before
    public void setUp() throws Exception {
        loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        repositoryUser = new RepositoryEntity<>(UserEntity.class, loader);
        repositorySessionMember = new RepositoryEntity<>(SessionMemberEntity.class, loader);
        repositorySession = new RepositoryEntity<>(SessionEntity.class, loader);
        repositoryEdition = new RepositoryEntity<>(EditionEntity.class, loader);
        repositoryConference = new RepositoryEntity<>(ConferenceEntity.class, loader);
        repositorySessionMemberConfiguration = new RepositoryEntity<>(ConfigurationSessionMemberEntity.class, loader);
        date = new Date();
        conference = new ConferenceEntity("conference", "IC");
        repositoryConference.add(conference);
        edition = new EditionEntity(date, date, "location", "bio", date, date, date, date);
        repositoryEdition.add(edition);
        edition.setConference(conference);
        session = new SessionEntity("nameSession", date, date, "location", "bio", 10);
        repositorySession.add(session);
        session.setEdition(edition);
        user = new UserEntity("user", "password", "email", "name", "website", "bio", "location");
        repositoryUser.add(user);
        configuration = new ConfigurationSessionMemberEntity(false, false, true);
        repositorySessionMemberConfiguration.add(configuration);
        configuration2 = new ConfigurationSessionMemberEntity(false, true, false);
        repositorySessionMemberConfiguration.add(configuration2);
    }

    @After
    public void tearDown() throws Exception {
        loader.getFactory().close();
    }

    @Test
    @Transactional
    public void add() throws Exception {
        SessionMemberEntity sessionMember = new SessionMemberEntity(session, user, configuration);
        try {
            Integer id = repositorySessionMember.add(sessionMember);
            Assert.assertTrue(id.equals(1) &&
                    sessionMember.getSession().getName().equals("nameSession") &&
                    sessionMember.getUser().getName().equals("name") &&
                    sessionMember.getConfiguration().getListener().equals(true)
            );
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void update() throws Exception {
        SessionMemberEntity sessionMember = new SessionMemberEntity(session, user, configuration);
        SessionMemberEntity sessionMember2 = new SessionMemberEntity(session, user, configuration);
        try {
            repositorySessionMember.add(sessionMember);
            repositorySessionMember.update(sessionMember, sessionMember2);
            SessionMemberEntity result = repositorySessionMember.getElementById(sessionMember.getId());
            Assert.assertTrue(result.getConfiguration().getListener().equals(true));
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void delete() throws Exception {
        SessionMemberEntity member = new SessionMemberEntity(session, user, configuration);
        try {
            repositorySessionMember.add(member);
            // This test is here only to make sure that we have something in repository in order to delete.
            Assert.assertTrue(member.getConfiguration().getListener()
                    .equals(repositorySessionMember.getElementById(1).getConfiguration().getListener()));
            repositorySessionMember.delete(member.getId());
            Assert.assertEquals(repositorySessionMember.getAll().isEmpty(), true);
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAll() throws Exception {
        SessionMemberEntity sessionMember = new SessionMemberEntity(session, user, configuration);
        SessionMemberEntity sessionMember2 = new SessionMemberEntity(session, user, configuration);
        try {
            repositorySessionMember.add(sessionMember);
            repositorySessionMember.add(sessionMember2);
            ArrayList<SessionMemberEntity> result = new ArrayList<>(repositorySessionMember.getAll());
            Assert.assertTrue(result.get(0).getId().equals(sessionMember.getId()) &&
                    result.get(1).getId().equals(sessionMember2.getId()));
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getElementById() throws Exception {
        SessionMemberEntity sessionMember = new SessionMemberEntity(session, user, configuration);
        SessionMemberEntity sessionMember2 = new SessionMemberEntity(session, user, configuration);
        try {
            repositorySessionMember.add(sessionMember);
            repositorySessionMember.add(sessionMember2);
            SessionMemberEntity result = repositorySessionMember.getElementById(2);
            Assert.assertTrue(result.getId().equals(sessionMember2.getId())
                    && result.getConfiguration().getListener().equals(true));
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAllFromSession() throws Exception {
        SessionEntity session0 = new SessionEntity("session", date, date, "room_0", "bio", 100);
        SessionMemberEntity member1 = new SessionMemberEntity();
        SessionMemberEntity member2 = new SessionMemberEntity();
        SessionMemberEntity member3 = new SessionMemberEntity();
        try {
            repositorySession.add(session0);
            member1.setSession(repositorySession.getElementById(1));
            member2.setSession(repositorySession.getElementById(1));
            member3.setSession(repositorySession.getElementById(1));
            repositorySessionMember.add(member1);
            repositorySessionMember.add(member2);
            repositorySessionMember.add(member3);
            SessionEntity newConfiguration = repositorySession.getElementById(1);
            Set<SessionMemberEntity> sessionMembers = newConfiguration.getMembers();
            Assert.assertTrue(sessionMembers.size() == 3);
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAllFromUser() throws Exception {
        SessionMemberEntity member1 = new SessionMemberEntity();
        SessionMemberEntity member2 = new SessionMemberEntity();
        SessionMemberEntity member3 = new SessionMemberEntity();
        try {
            member1.setUser(repositoryUser.getElementById(1));
            member2.setUser(repositoryUser.getElementById(1));
            member3.setUser(repositoryUser.getElementById(1));
            repositorySessionMember.add(member1);
            repositorySessionMember.add(member2);
            repositorySessionMember.add(member3);
            UserEntity user0 = repositoryUser.getElementById(1);
            Set<SessionMemberEntity> sessionMembers = user0.getSessionMembers();
            Assert.assertTrue(sessionMembers.size() == 3);
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }
}