package manager;

import convertor.UserConvertor;
import domain.UserEntity;
import model.UserModel;
import notification.NotificationCenter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import transferable.User;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */


@RunWith(PowerMockRunner.class)
@PrepareForTest(SignUpManager.class)
public class SignUpManagerTest {

    @Test
    public void isPasswordScoreWorking() throws Exception {
        NotificationCenter center = PowerMockito.mock(NotificationCenter.class);
        UserModel model = PowerMockito.mock(UserModel.class);
        SignUpManager manager = new SignUpManager(center, model);
        Integer score = Whitebox.invokeMethod(manager, "getPasswordScore", "password");
        Assert.assertTrue(score.equals(48));
    }

    @Test
    public void isSigningUser() throws Exception {
        NotificationCenter center = PowerMockito.mock(NotificationCenter.class);
        UserModel model = PowerMockito.mock(UserModel.class);
        UserEntity userEntity = new UserEntity("username", "password");
        List<UserEntity> result = new ArrayList<>();
        result.add(userEntity);
        User user = new User("test", "passwordTest", "try@gmail.com", "Test");
        PowerMockito.doReturn(result).when(model, "getUsers");
        PowerMockito.doReturn(new UserConvertor().convertUser(user)).when(model, "getUserById", 0);
        SignUpManager manager = new SignUpManager(center, model);
        Assert.assertTrue(manager.signUp("test", "passwordTest", "passwordTest", "try@gmail.com", "Test")
                .getUsername().equals("test"));
        try {
            Assert.assertTrue(manager.signUp("username", "passwordTest", "passwordTest", "try@gmail.com", "Test")
                    .getUsername().equals("test"));
        } catch (RemoteException exception) {
            Assert.assertTrue(exception.getMessage().equals("Invalid Username or Password"));
        }

    }
}