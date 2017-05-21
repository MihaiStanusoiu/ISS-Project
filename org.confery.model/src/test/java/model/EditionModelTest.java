package model;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.*;
import exception.SystemException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class EditionModelTest {

    private EditionModel editionModel;
    private UserModel userModel;
    private SessionModel sessionModel;
    private SubmissionModel submissionModel;

    @Before
    public void setUp() throws Exception {
        DatabaseLoaderInterface loader =
                new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        editionModel = new EditionModel(loader);
        userModel = new UserModel(loader);
        sessionModel = new SessionModel(loader);
        submissionModel = new SubmissionModel(loader);
    }

    @Test
    public void addMemberTo() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("username", "password");
        EditionEntity edition = new EditionEntity("New York");
        // preconditions:
        editionModel.add(edition);
        userModel.add(user);
        // when:
        edition = editionModel.addMemberTo(edition, user, MemberRole.EDITION_CHAIR);
        // then:
        assertEquals(edition.getChair(), user);
    }

    @Test(expected = SystemException.class)
    public void deleteMemberOf() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("username", "password");
        EditionEntity edition = new EditionEntity("New York");
        // preconditions:
        editionModel.add(edition);
        userModel.add(user);
        // when:
        edition = editionModel.addMemberTo(edition, user, MemberRole.EDITION_CHAIR);
        // then:
        assertEquals(edition.getChair(), user);
        // when:
        edition = editionModel.deleteMemberOf(edition, user);
        // then throws:
        edition.getChair();
    }

    @Test
    public void addSessionTo() throws Exception {
        // declarations:
        SessionEntity session = new SessionEntity("Test");
        EditionEntity edition = new EditionEntity("New York");
        // preconditions:
        editionModel.add(edition);
        sessionModel.add(session);
        // when:
        edition = editionModel.addSessionTo(edition, session);
        // then:
        assertTrue(edition.getSessions().stream()
                .anyMatch(item -> item.getId().equals(session.getId())));
    }

    @Test
    public void deleteSessionOf() throws Exception {
        // declarations:
        SessionEntity session = new SessionEntity("Test");
        EditionEntity edition = new EditionEntity("New York");
        // preconditions:
        editionModel.add(edition);
        sessionModel.add(session);
        // when:
        edition = editionModel.addSessionTo(edition, session);
        // then:
        assertEquals(edition.getSessions().size(), 1);
        // when:
        edition = editionModel.deleteSessionOf(edition, session);
        assertEquals(edition.getSubmissions().size(), 0);
    }

    @Test
    public void addSubmissionTo() throws Exception {
        // declarations:
        SubmissionEntity submission = new SubmissionEntity("Test", "NOT_REVIEWED");
        EditionEntity edition = new EditionEntity("New York");
        // preconditions:
        editionModel.add(edition);
        submissionModel.add(submission);
        // when:
        edition = editionModel.addSubmissionTo(edition, submission);
        // then:
        assertTrue(edition.getSubmissions().stream()
                .anyMatch(item -> item.getId().equals(submission.getId())));
    }

    @Test
    public void deleteSubmissionOf() throws Exception {
        // declarations:
        SubmissionEntity submission = new SubmissionEntity("Test", "NOT_REVIEWED");
        EditionEntity edition = new EditionEntity("New York");
        // preconditions:
        editionModel.add(edition);
        submissionModel.add(submission);
        // when:
        edition = editionModel.addSubmissionTo(edition, submission);
        // then:
        assertEquals(edition.getSubmissions().size(), 1);
        // when:
        edition = editionModel.deleteSubmissionOf(edition, submission);
        // then:
        assertEquals(edition.getSubmissions().size(), 0);
    }

}