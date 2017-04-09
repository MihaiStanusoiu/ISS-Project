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
import view.ViewType;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        08/04/2017
 * Tested:      False
 *
 * @author      Alexandru Stoica
 * @version     1.0
 */

@Component
public class ControllerSignUp implements ControllerInterface {

    @FXML TextField usernameTextField;
    @FXML TextField passwordTextField;
    @FXML TextField emailTextField;
    @FXML TextField displayNameTextField;
    @FXML TextField confirmTextField;
    @FXML Label errorLabel;
    @FXML ImageView backgroundImage;
    @FXML StackPane backgroundImagePane;

    @SuppressWarnings("all")
    private final StageManager manager;

    @Autowired
    @Lazy
    public ControllerSignUp(StageManager manager) {
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
     * Effect: Loads the ConferencesView.
     * @implNote status: In development.
     */
    @FXML void onLogoButtonClick() {
        manager.switchScene(ViewType.CONFERENCES);
    }

    /**
     * Effect: Loads the LoginView.
     * @implNote status: In development.
     */
    @FXML void onLoginButtonClick() {
        manager.switchScene(ViewType.LOGIN);
    }

    /**
     * Effect: The user registers in the system with his data.
     * @implNote status: Unavailable at the moment.
     */
    @FXML void onSignUpButtonClick() {
        String email = emailTextField.getText();
        String displayName = displayNameTextField.getText();
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String confirm = confirmTextField.getText();
        System.out.println(email + displayName + username + password + confirm);
    }

}
