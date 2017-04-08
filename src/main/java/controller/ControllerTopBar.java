package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import view.ViewType;

/**
 * Name:        ControllerTopBar
 * Effect:      Controls the top-bar navigation system.
 * Date:        05/04/2017
 * Tested:      False
 *
 * @author      Alexandru Stoica
 * @version     1.0
 */

@Component
public class ControllerTopBar implements ControllerInterface {

    @SuppressWarnings("all")
    @FXML private Label usernameLabel;

    @SuppressWarnings("all")
    @FXML private Button userProfileButton;

    private StageManager manager;

    @Autowired @Lazy
    public ControllerTopBar(StageManager manager) {
        this.manager = manager;
    }

    @Override
    public void initialize() { }

    /**
     * Effect: Loads the LoginView responsible
     * for the user's authentication process.
     * @implNote status: In development
     */
    @FXML private void onLoginButtonClick() {
        manager.switchScene(ViewType.LOGIN);
    }

    /**
     * Effect: Loads the SignUpView responsible
     * for the user's authentication process.
     * @implNote status: Unavailable at the moment.
     */
    @FXML private void onSignUpButtonClick() {
        System.out.println("Sign Up View");
    }

    /**
     * Effect: Loads the ConferencesView responsible
     * for listing all the available conferences.
     * @implNote status: In development.
     */
    @FXML private void onLogoButtonClick() {
        manager.switchScene(ViewType.CONFERENCES);
    }

    /**
     * Effect: Loads the ProfileView responsible
     * for updating the active user's profile information.
     * @implNote status: In development.
     */
    @FXML private void onProfileButtonClick() {
        System.out.println("Profile User View");
    }

}
