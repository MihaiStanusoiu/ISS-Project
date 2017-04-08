package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        08/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

@Component
public class ControllerLogin implements ControllerInterface {

    @FXML TextField usernameTextField;
    @FXML TextField passwordTextField;
    @FXML Label errorLabel;
    @FXML ImageView backgroundImage;
    @FXML StackPane backgroundImagePane;

    @SuppressWarnings("all")
    private final StageManager manager;

    @Autowired
    @Lazy
    public ControllerLogin(StageManager manager) {
        this.manager = manager;
    }

    /**
     * Effect: Adds width & height constraints on the
     * background image view in order to be responsive.
     */
    @Override
    public void initialize() {
        backgroundImage.fitWidthProperty().bind(backgroundImagePane.widthProperty());
        backgroundImage.fitHeightProperty().bind(backgroundImagePane.heightProperty());
    }

    /**
     * Effect: Loads the SignUpView.
     * @implNote status: Unavailable at the moment.
     */
    @FXML void onSignUpButtonClick() {
        System.out.println("Sign Up View");
    }

    /**
     * Effect: The user logs in the system with account data.
     * @implNote status: Unavailable at the moment.
     */
    @FXML void onLoginButtonClick() {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        System.out.println(username + password);
    }

}
