package checker;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.ConferenceEntity;
import domain.EditionEntity;
import domain.MemberRole;
import domain.UserEntity;
import model.ConferenceModel;
import model.EditionModel;
import model.UserModel;
import org.junit.Test;
import protocol.ConferenceProtocol;
import protocol.EditionProtocol;
import protocol.UserProtocol;

import static checker.UserPermissionChecker.isAllowed;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class UserPermissionCheckerTest {

    @Test
    public void isActiveUserAbleToUser() throws Exception {
        // declarations:
        UserEntity active = new UserEntity(1, "Test", "Test");
        UserEntity user = new UserEntity(1, "Test", "Test");
        // then:
        isAllowed(active).toDelete().theUser(user);
        isAllowed(active).toAdd().theUser(user);
        isAllowed(active).toUpdate().theUser(user);
        isAllowed(active).toRead().theUser(user);
    }

    @Test
    public void isActiveUserAbleToEdition() throws Exception {
        // declarations:
        UserEntity active = new UserEntity(1, "Test", "Test");
        ConferenceEntity conference = new ConferenceEntity("Test", "test");
        EditionEntity edition = new EditionEntity("Test");
        DatabaseLoaderInterface loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        ConferenceProtocol conferenceModel = new ConferenceModel(loader);
        EditionProtocol editionModel = new EditionModel(loader);
        UserProtocol userModel = new UserModel(loader);

        // then: [when nothing is set up]
        assertFalse(isAllowed(active).toDelete().theEdition(edition));
        assertFalse(isAllowed(active).toUpdate().theEdition(edition));

        // preconditions:
        userModel.add(active);
        conferenceModel.add(conference);
        conference = conferenceModel.addEditionTo(conference, edition);
        edition = conference.getLatestEdition();
        edition = editionModel.addMemberTo(edition, active, MemberRole.EDITION_CHAIR);

        // then:
        assertTrue(isAllowed(active).toAdd().theEdition(edition));
        assertTrue(isAllowed(active).toDelete().theEdition(edition));
        assertTrue(isAllowed(active).toUpdate().theEdition(edition));
        assertTrue(isAllowed(active).toRead().theEdition(edition));
    }
}