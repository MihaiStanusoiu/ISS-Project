package model;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class UserModelTest {

    private EditionModel editionModel;
    private SessionModel sessionModel;
    private UserModel userModel;
    private NotificationModel notificationModel;
    private SubmissionModel submissionModel;

    @Before
    public void setUp() throws Exception {
        DatabaseLoaderInterface loader =
                new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        userModel = new UserModel(loader);
        sessionModel = new SessionModel(loader);
        editionModel = new EditionModel(loader);
        submissionModel = new SubmissionModel(loader);
        notificationModel = new NotificationModel(loader);
    }

    @Test
    public void isAddingEditionToUser() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("username", "password");
        EditionEntity edition = new EditionEntity("Cluj-Napoca");
        // preconditions:
        userModel.add(user);
        editionModel.add(edition);
        // when:
        user = userModel.addEditionTo(user, edition, MemberRole.EDITION_CO_CHAIR);
        // then:
        assertTrue(user.getEditions().stream().anyMatch(item -> item.getId().equals(edition.getId())));
    }

    @Test
    public void isAddingSessionToUser() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("username", "password");
        SessionEntity session = new SessionEntity("Test");
        // preconditions:
        userModel.add(user);
        sessionModel.add(session);
        // when:
        user = userModel.addSessionTo(user, session, MemberRole.SESSION_LISTENER);
        // then:
        assertTrue(user.getSessions().stream().anyMatch(item -> item.getId().equals(session.getId())));
    }

    @Test
    public void isAddingSubmissionToUser() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("username", "password");
        SubmissionEntity submission = new SubmissionEntity("Test", "NOT_REVIEWED");
        // preconditions:
        userModel.add(user);
        submissionModel.add(submission);
        // when:
        user = userModel.addSubmissionTo(user, submission, Boolean.FALSE);
        // then:
        assertTrue(user.getSubmissions().stream().anyMatch(item -> item.getId().equals(submission.getId())));
    }

    @Test
    public void isAddingNotificationToUser() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("username", "password");
        NotificationEntity notification = new NotificationEntity("Test");
        // preconditions:
        userModel.add(user);
        notificationModel.add(notification);
        // when:
        user = userModel.addNotificationTo(user, notification);
        // then:
        assertTrue(user.getNotifications().stream().anyMatch(item -> item.getId().equals(notification.getId())));
    }

    @Test
    public void isRemovingEditionFromUser() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("username", "password");
        EditionEntity edition = new EditionEntity("Cluj-Napoca");
        // preconditions:
        userModel.add(user);
        editionModel.add(edition);
        // when:
        user = userModel.addEditionTo(user, edition, MemberRole.EDITION_CO_CHAIR);
        // then:
        assertEquals(user.getEditions().size(), 1);
        // when:
        user = userModel.removeEditionFrom(user, edition);
        // then:
        assertEquals(user.getEditions().size(), 0);
    }

    @Test
    public void isRemovingSessionFromUser() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("username", "password");
        SessionEntity session = new SessionEntity("Test");
        // preconditions:
        userModel.add(user);
        sessionModel.add(session);
        // when:
        user = userModel.addSessionTo(user, session, MemberRole.SESSION_LISTENER);
        // then:
        assertEquals(user.getSessions().size(), 1);
        // when:
        user = userModel.removeSessionFrom(user, session);
        // then:
        assertEquals(user.getSessions().size(), 0);
    }

    @Test
    public void isRemovingSubmissionFromUser() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("username", "password");
        SubmissionEntity submission = new SubmissionEntity("Test", "NOT_REVIEWED");
        // preconditions:
        userModel.add(user);
        submissionModel.add(submission);
        // when:
        user = userModel.addSubmissionTo(user, submission, Boolean.FALSE);
        // then:
        assertEquals(user.getSubmissions().size(), 1);
        // when:
        user = userModel.removeSubmissionFrom(user, submission);
        // then:
        assertEquals(user.getSubmissions().size(), 0);
    }

    @Test
    public void isRemovingNotificationFromUser() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("username", "password");
        NotificationEntity notification = new NotificationEntity("Test");
        // preconditions:
        userModel.add(user);
        notificationModel.add(notification);
        // when:
        user = userModel.addNotificationTo(user, notification);
        // then:
        assertEquals(user.getNotifications().size(), 1);
        // when:
        user = userModel.removeNotificationFrom(user, notification);
        // then:
        assertEquals(user.getNotifications().size(), 0);
    }

}