package controller;

import javafx.scene.control.Label;
import org.junit.Test;

import static org.loadui.testfx.Assertions.verifyThat;


/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class LoginTest extends TestFxBase {

    @Test
    public void isAbleToLogin() throws Exception {
        // when:
        moveTo("#loginButton").clickOn();
        clickOn("#usernameTextField").write("test");
        clickOn("#passwordTextField").write("password");
        moveTo("LOGIN").clickOn();
        // then:
        verifyThat("#usernameLabel", (Label username) -> username.getText().contains("Dan"));
        // finally:
        moveTo("#logoutButton").clickOn();
    }

}
