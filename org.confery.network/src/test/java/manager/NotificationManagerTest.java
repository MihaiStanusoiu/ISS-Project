package manager;

import domain.NotificationEntity;
import exception.ModelException;
import model.NotificationModel;
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
@PrepareForTest(NotificationManager.class)
public class NotificationManagerTest {

    private NotificationModel model;
    private NotificationManager manager;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        NotificationCenter center = PowerMockito.mock(NotificationCenter.class);
        model = PowerMockito.mock(NotificationModel.class);
        manager = new NotificationManager(model,center);
    }

    @Test
    public void isAddingEdition() throws Exception {
        // declarations:
        NotificationEntity notification = new NotificationEntity("Test", Boolean.FALSE);
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
        NotificationEntity notification = new NotificationEntity(null, Boolean.FALSE);
        // when:
        PowerMockito.doThrow(new ModelException("Notification's text is NULL!")).when(model, "add", notification);
        // then: [test exceptions]
        manager.add(notification);
    }

    @Test
    public void isDeletingEdition() throws Exception {
        // declarations:
        NotificationEntity notification = new NotificationEntity("Test", Boolean.FALSE);
        // when:
        PowerMockito.doReturn(notification).when(model, "delete", notification);
        // then:
        assertEquals(manager.delete(notification), notification);
    }

    @Test
    public void isNotDeletingEdition() throws Exception {
        // expect:
        expectedException.expect(RemoteException.class);
        // declarations:
        NotificationEntity notification = new NotificationEntity("Test", Boolean.FALSE);
        // when:
        PowerMockito.doThrow(new ModelException("Notification does not exist in the database.")).when(model, "delete", notification);
        // then: [test exceptions]
        manager.delete(notification);
    }

    @Test
    public void isUpdatingEdition() throws Exception {
        // declarations:
        NotificationEntity notification = new NotificationEntity("Test", Boolean.FALSE);
        NotificationEntity update =  new NotificationEntity("Test", Boolean.TRUE);
        // when:
        PowerMockito.doNothing().when(model, "update", notification, update);
        // then:
        manager.update(notification, update);
    }

    @Test
    public void isNotUpdatingEdition() throws Exception {
        // expect:
        expectedException.expect(RemoteException.class);
        // declarations:
        NotificationEntity notification = new NotificationEntity("Test", Boolean.FALSE);
        NotificationEntity update = new NotificationEntity("Test", Boolean.FALSE);
        // when:
        PowerMockito.doThrow(new ModelException("Update failed.")).when(model, "update", notification, update);
        // then:
        manager.update(notification, update);
    }

    @Test
    public void isGettingById() throws Exception {
        // declarations:
        NotificationEntity notification = new NotificationEntity("Test", Boolean.FALSE);
        // when:
        PowerMockito.doReturn(notification).when(model, "getElementById", 1);
        // then:
        assertEquals(manager.getElementById(1), notification);
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
