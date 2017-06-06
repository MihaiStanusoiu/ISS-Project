package checker;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.*;
import model.ConferenceModel;
import model.EditionModel;
import model.SessionModel;
import model.UserModel;
import org.junit.Test;
import protocol.ConferenceProtocol;
import protocol.EditionProtocol;
import protocol.SessionProtocol;
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

    @Test
    public void isActiveUserAbleToNotification() throws Exception {
        // declarations:
        UserEntity active = new UserEntity(1, "Test", "Test");
        NotificationEntity notification = new NotificationEntity("Notification");
        PermissionChecker<NotificationEntity> checker = new NotificationPermissionChecker();
        // then:
        assertTrue(checker.isAllowed(active).toDelete().theObject(notification));
        assertTrue(checker.isAllowed(active).toAdd().theObject(notification));
        assertTrue(checker.isAllowed(active).toUpdate().theObject(notification));
        assertTrue(checker.isAllowed(active).toRead().theObject(notification));
    }


    @Test
    public void isActiveUserAbleToTag() throws Exception {
        // declarations:
        UserEntity active = new UserEntity(1, "Test", "Test");
        TagEntity tag = new TagEntity("Tag");
        PermissionChecker<TagEntity> checker = new TagPermissionChecker();
        // then:
        assertFalse(checker.isAllowed(active).toDelete().theObject(tag));
        assertTrue(checker.isAllowed(active).toAdd().theObject(tag));
        assertFalse(checker.isAllowed(active).toUpdate().theObject(tag));
        assertTrue(checker.isAllowed(active).toRead().theObject(tag));
    }

    @Test
    public void isActiveUserAbleToTopic() throws Exception {
        // declarations:
        UserEntity active = new UserEntity(1, "Test", "Test");
        TopicEntity topic = new TopicEntity("Topic");
        PermissionChecker<TopicEntity> checker = new TopicPermissionChecker();
        // then:
        assertFalse(checker.isAllowed(active).toDelete().theObject(topic));
        assertTrue(checker.isAllowed(active).toAdd().theObject(topic));
        assertFalse(checker.isAllowed(active).toUpdate().theObject(topic));
        assertTrue(checker.isAllowed(active).toRead().theObject(topic));
    }

    @Test
    public void isActiveUserAbleToSubmission() throws Exception {
        // declarations:
        UserEntity active = new UserEntity(1, "Test", "Test");
        SubmissionEntity submission = new SubmissionEntity("Paper", "abstract.com", "full.com");
        PermissionChecker<SubmissionEntity> checker = new SubmissionPermissionChecker();
        // then:
        assertTrue(checker.isAllowed(active).toDelete().theObject(submission));
        assertTrue(checker.isAllowed(active).toAdd().theObject(submission));
        assertTrue(checker.isAllowed(active).toUpdate().theObject(submission));
        assertTrue(checker.isAllowed(active).toRead().theObject(submission));
    }

    @Test
    public void isActiveUserAbleToConference() throws Exception {
    // declarations:
        UserEntity active = new UserEntity(1, "Test", "Test");
        ConferenceEntity conference = new ConferenceEntity("Test", "test");
        EditionEntity edition = new EditionEntity("Test");
        DatabaseLoaderInterface loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        ConferenceProtocol conferenceModel = new ConferenceModel(loader);
        UserProtocol userModel = new UserModel(loader);
        EditionProtocol editionModel = new EditionModel(loader);
        PermissionChecker<ConferenceEntity> checker = new ConferencePermissionChecker();
        // then: [when nothing is set up]
        assertFalse(checker.isAllowed(active).toUpdate().theObject(conference));
        assertFalse(checker.isAllowed(active).toDelete().theObject(conference));
        // preconditions:
        Integer id = userModel.add(active);
        active = userModel.getElementById(id);
        Integer idConference = conferenceModel.add(conference);
        edition = conferenceModel.addEditionTo(conferenceModel.getElementById(idConference), edition);
        edition = conferenceModel.getElementById(idConference).getLatestEdition();
        edition = editionModel.addMemberTo(edition, active, MemberRole.EDITION_CHAIR);
        UserEntity c = conferenceModel.getChairOf( conferenceModel.getElementById(idConference));
        conference = conferenceModel.getElementById(idConference);
        // then:1
        assertTrue(checker.isAllowed(active).toAdd().theObject(conference));
        assertTrue(checker.isAllowed(active).toDelete().theObject(conference));
        assertTrue(checker.isAllowed(active).toUpdate().theObject(conference));
        assertTrue(checker.isAllowed(active).toRead().theObject(conference));
    }

    @Test
    public void isActiveUserAbleToSession() throws Exception {
        // declarations:
        UserEntity active = new UserEntity(1, "Test", "Test");
        ConferenceEntity conference = new ConferenceEntity("Test", "test");
        EditionEntity edition = new EditionEntity("Test");
        SessionEntity session = new SessionEntity("Test");
        DatabaseLoaderInterface loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        ConferenceProtocol conferenceModel = new ConferenceModel(loader);
        UserProtocol userModel = new UserModel(loader);
        EditionProtocol editionModel = new EditionModel(loader);
        SessionProtocol sessionModel = new SessionModel(loader);
        PermissionChecker<SessionEntity> checker = new SessionPermissionChecker();
        // then: [when nothing is set up]
        assertFalse(checker.isAllowed(active).toUpdate().theObject(session));
        assertFalse(checker.isAllowed(active).toDelete().theObject(session));
        // preconditions:
        userModel.add(active);
        Integer idConference = conferenceModel.add(conference);
        conferenceModel.addEditionTo(conferenceModel.getElementById(idConference), edition);
        edition = conferenceModel.getElementById(idConference).getLatestEdition();
        Integer idSession = sessionModel.add(session);
        sessionModel.addMemberTo(sessionModel.getElementById(idSession),active,MemberRole.SESSION_CHAIR);
        session = sessionModel.getElementById(idSession);
        // then:
        assertTrue(checker.isAllowed(active).toAdd().theObject(session));
        assertTrue(checker.isAllowed(active).toDelete().theObject(session));
        assertTrue(checker.isAllowed(active).toUpdate().theObject(session));
        assertTrue(checker.isAllowed(active).toRead().theObject(session));
    }
}