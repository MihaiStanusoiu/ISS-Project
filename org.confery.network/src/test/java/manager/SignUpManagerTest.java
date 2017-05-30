package manager;

import domain.UserEntity;
import model.UserModel;
import notification.NotificationCenter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.rmi.RemoteException;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */


@RunWith(PowerMockRunner.class)
@PrepareForTest(SignUpManager.class)
public class SignUpManagerTest {

    private UserModel model;
    private SignUpManager manager;
    @Before
    public void setUp() throws Exception {
        NotificationCenter center = PowerMockito.mock(NotificationCenter.class);
        model = PowerMockito.mock(UserModel.class);
        manager = new SignUpManager(center, model);
    }

    @Test
    public void isPasswordScoreWorking() throws Exception {
        // when:
        Integer score = Whitebox.invokeMethod(manager, "getPasswordScore", "password");
        // then:
        Assert.assertTrue(score.equals(48));
    }

    @Test
    public void isSigningUser() throws Exception {
        // declarations:
        UserEntity userEntity = new UserEntity("username", "password");
        UserEntity user = new UserEntity("test", "passwordTest", "try@gmail.com", "Test");
        List<UserEntity> result = singletonList(userEntity);
        // when:
        PowerMockito.doReturn(result).when(model, "getAll");
        PowerMockito.doReturn(1).when(model, "add", user);
        PowerMockito.doReturn(user).when(model, "getElementById", 1);
        // then:
        assertEquals(manager.signUp("test", "passwordTest", "passwordTest", "try@gmail.com", "Test").getUsername(), "test");
    }

    @Test(expected = RemoteException.class)
    public void isNotSigningUser() throws Exception {
        // declarations:
        UserEntity userEntity = new UserEntity("username", "password");
        UserEntity user = new UserEntity("test", "passwordTest", "try@gmail.com", "Test");
        List<UserEntity> result = singletonList(userEntity);
        // when:
        PowerMockito.doReturn(result).when(model, "getAll");
        PowerMockito.doReturn(user).when(model, "getElementById", null);
        // when:
        manager.signUp("username", "passwordTest", "passwordTest", "try@gmail.com", "Test");
    }
}