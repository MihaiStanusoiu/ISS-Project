package domain;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import model.SessionModel;
import model.UserModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class SessionEntityTest {

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
    public void isGettingSpeakers() throws Exception {
        // declarations:
        SessionEntity session = new SessionEntity("SuperSession");
        UserEntity speaker = new UserEntity("Speaker", "password");
        // preconditions:
        Integer idSession = sessionModel.add(session);
        Integer idSpeaker = userModel.add(speaker);
        // when:
        sessionModel.addMemberTo(session, speaker, MemberRole.SESSION_SPEAKER);
        // then:
        assertEquals(sessionModel.getElementById(idSession).getSpeakers().get(0),
                userModel.getElementById(idSpeaker));
    }

    @Test
    public void isGettingListeners() throws Exception {
        // declarations:
        SessionEntity session = new SessionEntity("SuperSession");
        UserEntity listener = new UserEntity("Speaker", "password");
        // preconditions:
        Integer idSession = sessionModel.add(session);
        Integer idListener = userModel.add(listener);
        // when:
        sessionModel.addMemberTo(session, listener, MemberRole.SESSION_LISTENER);
        // then:
        assertEquals(sessionModel.getElementById(idSession).getListeners().get(0),
                userModel.getElementById(idListener));
    }

    @Test
    public void isGettingChair() throws Exception {
        // declarations:
        SessionEntity session = new SessionEntity("SuperSession");
        UserEntity chair = new UserEntity("Chair", "password");
        // preconditions:
        Integer idSession = sessionModel.add(session);
        Integer idChair = userModel.add(chair);
        // when:
        sessionModel.addMemberTo(session, chair, MemberRole.SESSION_CHAIR);
        // then:
        assertEquals(sessionModel.getElementById(idSession).getChair(),
                userModel.getElementById(idChair));
    }


    @Test
    public void isGettingExceptionChair() throws Exception {
        // declarations:
        SessionEntity session = new SessionEntity("SuperSession");
        UserEntity chair = new UserEntity("Chair", "password");
        // preconditions:
        Integer idSession = sessionModel.add(session);
        Integer idChair = userModel.add(chair);
        // then:
        assertEquals((long)sessionModel.getElementById(idSession).getChair().getId(), 0L);
    }

    @Test
    public void isGettingAvailableSeats() throws Exception {
        // declarations:
        SessionEntity session = new SessionEntity("SuperSession", 4);
        UserEntity chair = new UserEntity("Chair", "password");
        UserEntity speaker = new UserEntity("Speaker", "password");
        // preconditions:
        Integer idSession = sessionModel.add(session);
        userModel.add(chair);
        userModel.add(speaker);
        // when:
        sessionModel.addMemberTo(session, chair, MemberRole.SESSION_CHAIR);
        sessionModel.addMemberTo(session, speaker, MemberRole.SESSION_SPEAKER);
        // then:
        assertEquals((long) sessionModel.getElementById(idSession).getAvailableSets(), 2L);
    }
}