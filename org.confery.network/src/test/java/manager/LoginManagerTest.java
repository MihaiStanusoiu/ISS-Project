package manager;

import domain.UserEntity;
import model.UserModel;
import notification.NotificationCenter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(SignUpManager.class)
public class LoginManagerTest {

    @Test
    public void isLoggingUser() throws Exception {
        NotificationCenter center = PowerMockito.mock(NotificationCenter.class);
        UserModel model = PowerMockito.mock(UserModel.class);
        List<UserEntity> result = new ArrayList<>();
        result.add(new UserEntity("test", "pass", "", "", "", "", ""));
        when(model.getUsers()).thenReturn(result);
        LoginManager manager = new LoginManager(center, model);
        Assert.assertTrue(manager.login("test", "pass").getUsername().equals("test"));
        try {
            Assert.assertTrue(manager.login("test", "password").getUsername().equals("test"));
        } catch (RemoteException exception) {
            Assert.assertTrue(exception.getMessage().equals("Wrong Username or Password!"));
        }
    }

}