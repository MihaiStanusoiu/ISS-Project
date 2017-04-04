package domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Name:        UserTest
 * Effect:      {ClassEffect}
 * Date:        4/2/2017
 * Tested:      False
 *
 * @author      {Stanusoiu Mihai-Teodor}
 * @version     1.0
 */

public class UserTest {

    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User(
                1,
                "mike", 
                "1234", 
                "mihai@gmail.com", 
                "John Snow",
                "website.com",
                "Student", 
                "str..."
        );
        user.setId(1);
    }

    @Test
    public void isGettingId() throws Exception {
        assertTrue(user.getId().equals(1));
    }

    @Test
    public void isSettingId() throws Exception {
        user.setId(10);
        assertTrue(user.getId().equals(10));
    }

    @Test
    public void isGettingWebsite() throws Exception {
        assertTrue(user.getWebsite().equals("website.com"));
    }

    @Test
    public void isGettingType() throws Exception {
        assertTrue(user.getType().equals(UserType.USER));
    }

    @Test
    public void isGettingPermissions() throws Exception {
        assertTrue(user.getPermissions().contains(Permission.VIEW_CONFERENCE));
    }
}