package model;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.ConferenceEntity;
import domain.EditionEntity;
import domain.MemberRole;
import domain.UserEntity;
import org.junit.Before;
import org.junit.Test;
import protocol.ConferenceProtocol;
import protocol.EditionProtocol;
import protocol.UserProtocol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ConferenceModelTest {

    private UserProtocol modelUser;
    private EditionProtocol modelEdition;
    private ConferenceProtocol modelConference;

    @Before
    public void setUp() throws Exception {
        DatabaseLoaderInterface loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        modelUser = new UserModel(loader);
        modelEdition = new EditionModel(loader);
        modelConference = new ConferenceModel(loader);
    }

    @Test
    public void isGettingConferencesForUser() throws Exception {
        // declarations:
        ConferenceEntity conference = new ConferenceEntity("TestTest");
        EditionEntity edition = new EditionEntity("Test");
        UserEntity user = new UserEntity("User", "test");
        // preconditions:
        modelUser.add(user);
        Integer id = modelConference.add(conference);
        edition = modelConference.addEditionTo(conference, edition);
        modelEdition.addMemberTo(edition, user, MemberRole.EDITION_CHAIR);
        // then:
        assertEquals(modelConference.getConferencesOf(user).stream().anyMatch(item -> item.getId().equals(id)), Boolean.TRUE);
    }

    @Test
    public void isAddingEditionTo() throws Exception {
        // declaration:
        ConferenceEntity conference = new ConferenceEntity("Test");
        EditionEntity edition = new EditionEntity("test");
        // preconditions:
        Integer id = modelConference.add(conference);
        // when:
        edition = modelConference.addEditionTo(conference, edition);
        Integer idEdition = edition.getId();
        // then:
        assertEquals(modelEdition.getAll().size(), 1);
        assertEquals(modelEdition.getElementById(1).getLocation(), edition.getLocation());
        assertTrue(modelConference.getElementById(id).getEditions().stream()
                .anyMatch(item -> item.getId().equals(idEdition)));
    }

    @Test
    public void isRemovingEditionFrom() throws Exception {
        // declaration:
        ConferenceEntity conference = new ConferenceEntity("Test");
        EditionEntity edition = new EditionEntity("test");
        // preconditions:
        Integer idConference = modelConference.add(conference);
        // when:
        edition = modelConference.addEditionTo(conference, edition);
        Integer idEdition = edition.getId();
        assertEquals((long)modelConference.getElementById(idConference).getEditions().size(), 1L);
        assertTrue(modelConference.getElementById(idConference).getEditions().stream()
                .anyMatch(item -> item.getId().equals(idEdition)));
        modelConference.removeEditionFrom(modelConference.getElementById(idConference), edition);
        // then:
        assertTrue(modelConference.getElementById(idConference).getEditions().stream()
                .noneMatch(item -> item.getId().equals(idEdition)));
    }

    @Test
    public void isGettingChairOfConference() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("test", "password");
        ConferenceEntity conference = new ConferenceEntity("Test");
        EditionEntity edition = new EditionEntity("test");
        // preconditions:
        Integer id = modelUser.add(user);
        Integer idConference = modelConference.add(conference);
        edition = modelConference.addEditionTo(conference, edition);
        modelEdition.addMemberTo(edition, user, MemberRole.EDITION_CHAIR);
        assertEquals(modelConference.getChairOf(modelConference.getElementById(idConference)).getUsername(),
                modelUser.getElementById(id).getUsername());
    }
}