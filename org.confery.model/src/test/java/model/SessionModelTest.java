package model;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.MemberRole;
import domain.SessionEntity;
import domain.UserEntity;
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
        DatabaseLoaderInterface loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        sessionModel = new SessionModel(loader);
        userModel = new UserModel(loader);
    }

    @Test
    public void isAddingMember() throws Exception {
        SessionEntity session = new SessionEntity("Test");
        UserEntity user = new UserEntity("username", "test");
        userModel.add(user);
        sessionModel.add(session);
        sessionModel.addMemberTo(session, user, MemberRole.SESSION_CHAIR);
        Assert.assertTrue(sessionModel.getAll().get(0).getMembers().size() == 1);
    }

    @Test
    public void isGettingAllSpeakers() throws Exception {
        SessionEntity session = new SessionEntity("Test");
        UserEntity user = new UserEntity("username", "test");
        UserEntity test = new UserEntity("username", "test");
        userModel.add(user);
        userModel.add(test);
        sessionModel.add(session);
        sessionModel.addMemberTo(session, test, MemberRole.SESSION_SPEAKER);
        sessionModel.addMemberTo(session, user, MemberRole.SESSION_CHAIR);
        Assert.assertTrue(sessionModel.getAllSpeakers(sessionModel
                .getElementById(session.getId())).get(0).equals(test));
    }

    @Test
    public void isGettingAllListeners() throws Exception {
        SessionEntity session = new SessionEntity("Test");
        UserEntity user = new UserEntity("username", "test");
        UserEntity test = new UserEntity("username", "test");
        userModel.add(user);
        userModel.add(test);
        sessionModel.add(session);
        sessionModel.addMemberTo(session, test, MemberRole.SESSION_LISTENER);
        sessionModel.addMemberTo(session, user, MemberRole.SESSION_LISTENER);
        Assert.assertTrue(sessionModel.getAllListeners(sessionModel
                .getElementById(session.getId())).get(0).equals(test) &&
                sessionModel.getAllListeners(sessionModel.getElementById(session.getId())).size() == 2);
    }

    @Test
    public void isGettingChair() throws Exception {
        SessionEntity session = new SessionEntity("Test");
        UserEntity user = new UserEntity("username", "test");
        UserEntity test = new UserEntity("username", "test");
        userModel.add(user);
        userModel.add(test);
        sessionModel.add(session);
        sessionModel.addMemberTo(session, test, MemberRole.SESSION_SPEAKER);
        sessionModel.addMemberTo(session, user, MemberRole.SESSION_CHAIR);
        Assert.assertTrue(sessionModel.getChair(sessionModel
                .getElementById(session.getId())).equals(user));
    }

    @Test
    public void isRemovingMember() throws Exception {
        SessionEntity session = new SessionEntity("Test");
        UserEntity user = new UserEntity("username", "test");
        UserEntity test = new UserEntity("username", "test");
        userModel.add(user);
        userModel.add(test);
        sessionModel.add(session);
        sessionModel.addMemberTo(session, test, MemberRole.SESSION_SPEAKER);
        Assert.assertTrue(sessionModel.getElementById(session.getId()).getMembers().size() == 1);
        sessionModel.removeMemberFrom(sessionModel.getElementById(session.getId()), test);
        Assert.assertTrue(sessionModel.getElementById(session.getId()).getMembers().size() == 0);
    }

    @Test
    public void isChangingMemberRole() throws Exception {
        SessionEntity session = new SessionEntity("Test");
        UserEntity test = new UserEntity(1, "username", "test");
        userModel.add(test);
        sessionModel.add(session);
        sessionModel.addMemberTo(session, test, MemberRole.SESSION_LISTENER);
        Assert.assertTrue(sessionModel.getAllListeners(sessionModel
                .getElementById(session.getId())).size() == 1);
        sessionModel.changeMemberRoleIn(sessionModel
                .getElementById(session.getId()), test, MemberRole.SESSION_SPEAKER);
        Assert.assertTrue(sessionModel.getAllListeners(sessionModel
                .getElementById(session.getId())).size() == 0 &&
                sessionModel.getAllSpeakers(sessionModel.getElementById(session.getId())).size() == 1);
    }

    @Test
    public void isChangingChair() throws Exception {
        SessionEntity session = new SessionEntity("Test");
        UserEntity user = new UserEntity("username", "test");
        UserEntity test = new UserEntity("username", "test");
        userModel.add(user);
        userModel.add(test);
        sessionModel.add(session);
        sessionModel.addMemberTo(session, user, MemberRole.SESSION_CHAIR);
        Assert.assertTrue(sessionModel.getChair(sessionModel
                .getElementById(session.getId())).equals(user));
        sessionModel.changeChairIn(sessionModel.getElementById(session.getId()), test);
        Assert.assertTrue(sessionModel.getChair(sessionModel
                .getElementById(session.getId())).equals(test));
        Assert.assertTrue(sessionModel.getElementById(session.getId()).getMembers().size() == 1);

    }

}