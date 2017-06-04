package model;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.MemberRole;
import domain.SessionEntity;
import domain.UserEntity;
import exception.ModelException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class SessionModelTest {

    private SessionModel sessionModel;
    private UserModel userModel;

    @Before
    public void setUp() throws Exception {
        DatabaseLoaderInterface loader =
                new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        sessionModel = new SessionModel(loader);
        userModel = new UserModel(loader);
    }

    @Test
    public void isAddingMember() throws Exception {
        // declarations:
        SessionEntity session = new SessionEntity("Test");
        UserEntity user = new UserEntity("username", "test");
        // preconditions:
        userModel.add(user);
        sessionModel.add(session);
        // when:
        session = sessionModel.addMemberTo(session, user, MemberRole.SESSION_CHAIR);
        // then:
        Assert.assertEquals(session.getChair(), user);
    }

    @Test(expected = ModelException.class)
    public void isNotAddingMember() throws Exception {
        // declarations:
        SessionEntity session = new SessionEntity("Test");
        UserEntity user = new UserEntity("username", "test");
        // preconditions:
        sessionModel.add(session);
        // when:
        sessionModel.addMemberTo(session, user, MemberRole.SESSION_CHAIR);
    }

    @Test
    public void isRemovingMember() throws Exception {
        // declarations:
        SessionEntity session = new SessionEntity("Test");
        UserEntity test = new UserEntity("username", "test");
        // preconditions:
        Integer idUser = userModel.add(test);
        Integer idSession = sessionModel.add(session);
        // when:
        sessionModel.addMemberTo(session, test, MemberRole.SESSION_SPEAKER);
        // then:
        Assert.assertEquals(sessionModel.getElementById(idSession).getSpeakers().get(0).getId(), idUser);
        // when:
        session = sessionModel.removeMemberFrom(sessionModel.getElementById(session.getId()), test);
        // then:
        Assert.assertEquals(session.getSpeakers().size(), 0);
    }

}