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
import view.ViewType;

import java.rmi.RemoteException;

/**
 * @author      Alexandru Stoica
 * @version     1.0
 */

@Lazy
@Component
public class ControllerTopBar
        implements ControllerInterface, SubscriberService {

    @FXML private Label usernameLabel;
    @FXML private Button userProfileButton;
    @FXML private Button signUpButton;
    @FXML private Button loginButton;

    @Lazy
    @Autowired
    private StageManager manager;

    @Lazy
    @Autowired
    private Listener listener;

    @Override
    public void initialize() throws RemoteException {
        if (existsActiveUser()) { this.showActiveUser(); }
        else { this.showRegistrationButtons(); }
    }

    private Boolean existsActiveUser() throws RemoteException {
        return listener.getActiveUser() != null;
    }

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
    @FXML private void onProfileButtonClick() throws RemoteException {
        if (existsActiveUser()) {
            manager.switchScene(ViewType.PROFILE, listener.getActiveUser());
        }
        // TODO: Make Profile User View & MyProfile View
        // System.out.println("Profile UserEntity View");
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
                notification.getType().equals(NotificationType.SIGNAL_SIGN_UP) ||
                notification.getType().equals(NotificationType.UPDATE_USER)) {
            Platform.runLater(() -> {
                try {
                    showActiveUser();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });
        }
        if (notification.getType().equals(NotificationType.SIGNAL_LOGOUT)) {
            Platform.runLater(this::showRegistrationButtons);
        }
    }
}
