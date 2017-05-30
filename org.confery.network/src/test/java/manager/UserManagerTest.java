package manager;

import domain.UserEntity;
import model.UserModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import transfarable.User;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static translator.UserTranslator.translate;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(SignUpManager.class)
public class UserManagerTest {

    private UserModel model;
    private UserManager manager;

    @Before
    public void setUp() throws Exception {
        model = PowerMockito.mock(UserModel.class);
        manager = new UserManager(model);
    }

    @Test
    public void isAdding() throws Exception {
        // declaration:
        User user = new User(1, "username", "password", "email",
                "name", "website", "bio", "location" );
        UserEntity entity = translate(user);
        // when:
        doReturn(1).when(model, "add", entity);
        // then:
        assertEquals((long)manager.add(user), 1L);
    }

    @Test
    public void idDeleting() throws Exception {
        // declaration:
        User user = new User(1, "username", "password", "email",
                "name", "website","bio", "location" );
        UserEntity entity = translate(user);
        // when:
        doReturn(entity).when(model, "delete", entity);
        // then:
        assertEquals(manager.delete(user).getUsername(), user.getUsername());
    }
    
}