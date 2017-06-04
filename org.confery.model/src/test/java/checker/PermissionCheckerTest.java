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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class PermissionCheckerTest {

    @Test
    public void isActiveUserAbleToUser() throws Exception {
        // declarations:
        UserEntity active = new UserEntity(1, "Test", "Test");
        UserEntity user = new UserEntity(1, "Test", "Test");
        PermissionChecker<UserEntity> checker = new UserPermissionChecker();
        // then:
        assertTrue(checker.isAllowed(active).toDelete().theObject(user));
        assertTrue(checker.isAllowed(active).toAdd().theObject(user));
        assertTrue(checker.isAllowed(active).toUpdate().theObject(user));
        assertTrue(checker.isAllowed(active).toRead().theObject(user));
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
        PermissionChecker<EditionEntity> checker = new EditionPermissionChecker();
        // then: [when nothing is set up]
        assertFalse(checker.isAllowed(active).toUpdate().theObject(edition));
        assertFalse(checker.isAllowed(active).toDelete().theObject(edition));
        // preconditions:
        userModel.add(active);
        Integer idConference = conferenceModel.add(conference);
        conferenceModel.addEditionTo(conferenceModel.getElementById(idConference), edition);
        edition = conferenceModel.getElementById(idConference).getLatestEdition();
        edition = editionModel.addMemberTo(edition, active, MemberRole.EDITION_CHAIR);
        // then:
        assertTrue(checker.isAllowed(active).toAdd().theObject(edition));
        assertTrue(checker.isAllowed(active).toDelete().theObject(edition));
        assertTrue(checker.isAllowed(active).toUpdate().theObject(edition));
        assertTrue(checker.isAllowed(active).toRead().theObject(edition));
    }
}