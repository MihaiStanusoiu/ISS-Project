package checker;

import domain.EditionEntity;
import domain.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(UserPermissionChecker.class)
public class UserPermissionCheckerTest {


    @Test
    public void isAllowedUserToDeleteEdition() throws Exception {
        // declarations:
        UserPermissionChecker checker = new UserPermissionChecker();
        UserEntity user = new UserEntity("username", "password");
        EditionEntity edition = PowerMockito.mock(EditionEntity.class);
        // when:
        when(edition.getChair()).thenReturn(user);
        // then:
        assertEquals(checker.isAllowed(user).toDelete().theEdition(edition), true);
    }
    @Test

    public void isNotAllowedUserToDeleteEdition() throws Exception {
        // declarations:
        UserPermissionChecker checker = new UserPermissionChecker();
        UserEntity other = new UserEntity("other", "password");
        UserEntity user = new UserEntity("username", "password");
        EditionEntity edition = PowerMockito.mock(EditionEntity.class);
        // when:
        when(edition.getChair()).thenReturn(other);
        // then:
        assertEquals(checker.isAllowed(user).toDelete().theEdition(edition), false);
    }
}