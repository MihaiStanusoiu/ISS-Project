package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import listener.Listener;
import manager.StageManager;
import notification.Notification;
import notification.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import service.SubscriberService;
import utils.ThrowPipe;
import view.ViewType;

import java.rmi.RemoteException;

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
public class ControllerTopBar implements ControllerInterface, SubscriberService {

    @SuppressWarnings("all")
    @FXML private Label usernameLabel;

    @SuppressWarnings("all")
    @FXML private Button userProfileButton;

    @FXML private Button signUpButton;
    @FXML private Button loginButton;

    private final StageManager manager;
    private final Listener listener;

    @Autowired @Lazy
    public ControllerTopBar(StageManager manager, Listener listener) throws RemoteException {
        this.manager = manager;
        this.listener = listener;
        this.listener.addSubscriber(this);
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
     * @implNote status: In development
     */
    @FXML private void onSignUpButtonClick() {
        manager.switchScene(ViewType.SIGN_UP);
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
        // TODO: Make Profile User View & MyProfile View
        System.out.println("Profile UserEntity View");
    }

    private void setButton(Button button, String text, Double opacity) {
        button.setText(text);
        button.setOpacity(opacity);
    }

    private void showActiveUser() throws RemoteException {
        usernameLabel.setText(listener.getActiveUser().getName());
        usernameLabel.setOpacity(1.0);
        setButton(signUpButton, "", 0.0);
        setButton(loginButton, "", 0.0);
    }

    private void showRegistrationButtons() {
        usernameLabel.setText("");
        usernameLabel.setOpacity(0.0);
        setButton(signUpButton, "Sign Up", 1.0);
        setButton(loginButton, "Login", 1.0);
    }

    @Override
    public void update(Notification notification) throws RemoteException {
        if (notification.getType().equals(NotificationType.SIGNAL_LOGIN) ||
                notification.getType().equals(NotificationType.SIGNAL_SIGN_UP)) {
            Platform.runLater(() -> ThrowPipe.wrap(this::showActiveUser));
        }
        if (notification.getType().equals(NotificationType.SIGNAL_LOGOUT)) {
            Platform.runLater(this::showRegistrationButtons);
        }
    }
}
