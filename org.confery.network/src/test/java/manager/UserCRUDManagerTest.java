package manager;

import domain.UserEntity;
import exception.ModelException;
import model.UserModel;
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
@PrepareForTest(UserCRUDManager.class)
public class UserCRUDManagerTest {
    private UserModel model;
    private UserCRUDManager manager;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        NotificationCenter center = PowerMockito.mock(NotificationCenter.class);
        model = PowerMockito.mock(UserModel.class);
        manager = new UserCRUDManager(model,center);
    }

    @Test
    public void isAddingEdition() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("username", "password");
        // when:
        PowerMockito.doReturn(1).when(model, "add", user);
        // then:
        assertEquals((long)manager.add(user),1L);
    }

    @Test
    public void isNotAddingEdition() throws Exception {
        // expect:
        expectedException.expect(RemoteException.class);
        // declarations:
        UserEntity user =  new UserEntity("username", null);
        // when:
        PowerMockito.doThrow(new ModelException("User's password is NULL!")).when(model, "add", user);
        // then: [test exceptions]
        manager.add(user);
    }

    @Test
    public void isDeletingEdition() throws Exception {
        // declarations:
        UserEntity user =  new UserEntity("username", "password");
        // when:
        PowerMockito.doReturn(user).when(model, "delete", user);
        // then:
        assertEquals(manager.delete(user), user);
    }

    @Test
    public void isNotDeletingEdition() throws Exception {
        // expect:
        expectedException.expect(RemoteException.class);
        // declarations:
        UserEntity user = new UserEntity("username", "password");
        // when:
        PowerMockito.doThrow(new ModelException("User does not exist in the database.")).when(model, "delete", user);
        // then: [test exceptions]
        manager.delete(user);
    }

    @Test
    public void isUpdatingEdition() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("username", "password");
        UserEntity update =   new UserEntity("username", "updated");
        // when:
        PowerMockito.doNothing().when(model, "update", user, update);
        // then:
        manager.update(user, update);
    }

    @Test
    public void isNotUpdatingEdition() throws Exception {
        // expect:
        expectedException.expect(RemoteException.class);
        // declarations:
        UserEntity user = new UserEntity("username", "password");
        UserEntity update = new UserEntity("username", "updated");
        // when:
        PowerMockito.doThrow(new ModelException("Update failed.")).when(model, "update", user, update);
        // then:
        manager.update(user, update);
    }

    @Test
    public void isGettingById() throws Exception {
        // declarations:
        UserEntity user =  new UserEntity("username", "password");
        // when:
        PowerMockito.doReturn(user).when(model, "getElementById", 1);
        // then:
        assertEquals(manager.getElementById(1), user);
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




