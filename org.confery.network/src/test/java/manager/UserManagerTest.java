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
import translator.UserTranslator;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.doReturn;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(SignUpManager.class)
public class UserManagerTest {

    private UserModel model;
    private UserManager manager;
    private UserTranslator translator;
    private User active;
    @Before
    public void setUp() throws Exception {
        model = PowerMockito.mock(UserModel.class);
        manager = new UserManager(model);
        active = new User(1, "username", "password", "email",
                "name", "website", "bio", "location" );
        manager.activeUser(active);
        translator = new UserTranslator();
    }

    @Test
    public void isAdding() throws Exception {
        // declaration:
        UserEntity entity =  translator.translate(active);
        // when:
        doReturn(1).when(model, "add", entity);
        // then:
        assertEquals((long)manager.add(active), 1L);
    }

    @Test
    public void idDeleting() throws Exception {
        // declaration:
        UserEntity entity = translator.translate(active);
        // when:
        doReturn(entity).when(model, "delete", entity);
        // then:
        assertEquals(manager.delete(active).getUsername(), active.getUsername());
    }
    
}