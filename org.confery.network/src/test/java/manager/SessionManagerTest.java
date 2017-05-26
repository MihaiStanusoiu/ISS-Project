package manager;

import domain.SessionEntity;
import exception.ModelException;
import model.SessionModel;
import notification.NotificationCenter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.rmi.RemoteException;

import static org.junit.Assert.assertEquals;

/**
 * @author Teodorescu Vlad
 * @version 1.0
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SessionManager.class)
public class SessionManagerTest {
    private SessionModel model;
    private SessionManager manager;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        NotificationCenter center = PowerMockito.mock(NotificationCenter.class);
        model = PowerMockito.mock(SessionModel.class);
        manager = new SessionManager(model,center);
    }

    @Test
    public void isAddingEdition() throws Exception {
        // declarations:
        SessionEntity notification =  new SessionEntity("Test");
        // when:
        PowerMockito.doReturn(1).when(model, "add", notification);
        // then:
        assertEquals((long)manager.add(notification),1L);
    }

    @Test
    public void isNotAddingEdition() throws Exception {
        // expect:
        expectedException.expect(RemoteException.class);
        // declarations:
        SessionEntity session =  new SessionEntity(null);
        // when:
        PowerMockito.doThrow(new ModelException("Session's name is NULL!")).when(model, "add", session);
        // then: [test exceptions]
        manager.add(session);
    }

    @Test
    public void isDeletingEdition() throws Exception {
        // declarations:
        SessionEntity session =  new SessionEntity("Test");
        // when:
        PowerMockito.doReturn(session).when(model, "delete", session);
        // then:
        assertEquals(manager.delete(session), session);
    }

    @Test
    public void isNotDeletingEdition() throws Exception {
        // expect:
        expectedException.expect(RemoteException.class);
        // declarations:
        SessionEntity session = new SessionEntity("Test");
        // when:
        PowerMockito.doThrow(new ModelException("Notification does not exist in the database.")).when(model, "delete", session);
        // then: [test exceptions]
        manager.delete(session);
    }

    @Test
    public void isUpdatingEdition() throws Exception {
        // declarations:
        SessionEntity session =  new SessionEntity("Test");
        SessionEntity update =   new SessionEntity("Updated");
        // when:
        PowerMockito.doNothing().when(model, "update", session, update);
        // then:
        manager.update(session, update);
    }

    @Test
    public void isNotUpdatingEdition() throws Exception {
        // expect:
        expectedException.expect(RemoteException.class);
        // declarations:
        SessionEntity session = new SessionEntity("Test");
        SessionEntity update =  new SessionEntity("Test");
        // when:
        PowerMockito.doThrow(new ModelException("Update failed.")).when(model, "update", session, update);
        // then:
        manager.update(session, update);
    }

    @Test
    public void isGettingById() throws Exception {
        // declarations:
        SessionEntity session =  new SessionEntity("Test");
        // when:
        PowerMockito.doReturn(session).when(model, "getElementById", 1);
        // then:
        assertEquals(manager.getElementById(1), session);
    }

    @Test
    public void isNotGettingById() throws Exception {
        // expect:
        expectedException.expect(RemoteException.class);
        // declarations:
        // when:
        PowerMockito.doThrow(new ModelException("Get By id Failed")).when(model, "getElementById", 1);
        // then:
        manager.getElementById(1);
    }
}
