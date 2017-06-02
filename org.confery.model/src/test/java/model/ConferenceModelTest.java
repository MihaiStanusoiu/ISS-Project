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
        ConferenceEntity conference = new ConferenceEntity("Test");
        EditionEntity edition = new EditionEntity("Test");
        UserEntity user = new UserEntity("Test", "test");
        // preconditions:
        modelConference.add(conference);
        modelEdition.add(edition);
        modelUser.add(user);
        conference = modelConference.addEditionTo(conference, edition);
        Integer id = conference.getId();
        modelEdition.addMemberTo(edition, user, MemberRole.EDITION_CHAIR);
        // then:
        assertEquals(modelConference.getConferencesOf(user).stream().anyMatch(item -> item.getId().equals(id)), Boolean.TRUE);
    }

}