package manager;

import domain.UserEntity;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(SignUpManager.class)
public class LoginManagerTest {

    private UserModel model;
    private LoginManager manager;


    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        NotificationCenter center = PowerMockito.mock(NotificationCenter.class);
        model = PowerMockito.mock(UserModel.class);
        //manager = new LoginManager(center, model);
    }

    @Test
    public void isLoggingUser() throws Exception {
        // declarations:
        List<UserEntity> result = new ArrayList<>();
        result.add(new UserEntity("test", "pass", "", "", "", "", ""));
        // when:
        when(model.getAll()).thenReturn(result);
        // then:
        assertEquals(manager.login("test", "pass").getUsername(), "test");
    }

    @Test
    public void isNotLoggingUser() throws Exception {
        // expect:
        expectedException.expect(RemoteException.class);
        // declarations:
        List<UserEntity> result = new ArrayList<>();
        result.add(new UserEntity("test", "pass", "", "", "", "", ""));
        // when:
        when(model.getAll()).thenReturn(result);
        // then: [test exceptions]
        manager.login("test", "password");
    }
}