package testFX;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.junit.Test;

import static org.testfx.api.FxAssert.verifyThat;

/**
 * Created by Vlad on 5/18/2017.
 */
public class SignUpTest extends TestFXBaseClass {

    @Test
    public void userSignUp ()
    {
        String email = "example@company.com";
        String displayName = "DisplayNameExample";
        String username = "UsernameExample";
        String password = "Password123";

        TextField emailTextField = find("#emailTextField");
        emailTextField.setText(email);
        TextField displayNameTextField = find("#displayNameTextField");
        displayNameTextField.setText(displayName);
        TextField usernameTextField = find("#usernameTextField");
        usernameTextField.setText(username );
        TextField passwordTextField = find("#passwordTextField");
        passwordTextField.setText(password);
        TextField confirmTextField = find("#confirmTextField");
        confirmTextField.setText(password);

        clickOn("#signUpButton");
        moveTo("#insertCOnferencesViewIdhere");
        verifyThat("#nameLabel", (Label label) -> {
            String onForm = label.getText();
            return onForm.contains(displayName);
        });
    }
}
