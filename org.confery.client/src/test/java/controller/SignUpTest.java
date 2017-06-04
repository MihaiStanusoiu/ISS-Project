package controller;

import javafx.scene.control.Label;
import org.junit.Test;

import static org.loadui.testfx.Assertions.verifyThat;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class SignUpTest extends TestFxBase {
    
    @Test
    public void isAbleToSignUp() throws Exception {
        // when:
        moveTo("#signUpButton").clickOn();
        clickOn("#emailTextField").write("email@test");
        clickOn("#displayNameTextField").write("Test");
        clickOn("#usernameTextField").write("test.username");
        clickOn("#passwordTextField").write("password123");
        clickOn("#confirmTextField").write("password123");
        moveTo("SIGN UP").clickOn();
        // then:
        verifyThat("#usernameLabel", (Label username) -> username.getText().equals("Test"));
        // finally:
        moveTo("#logoutButton").clickOn();
    }
}
