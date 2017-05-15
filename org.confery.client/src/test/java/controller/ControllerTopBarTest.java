package controller;

import org.junit.Assert;
import org.junit.Test;
import transferable.User;

import java.util.Optional;


/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ControllerTopBarTest {

    /**
     * Ignore this test, this just a playground for Google Guava Framework.
     */
    @Test
    public void googleFunctionalTestingPlayground() throws Exception {
        User user = new User("username", "password", "email", "name");
        User nullUser = null;
        Assert.assertTrue(Optional.ofNullable(nullUser).orElse(user).getUsername().equals("username"));
    }
}