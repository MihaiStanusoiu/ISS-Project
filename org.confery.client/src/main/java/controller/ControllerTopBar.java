package controller;

import context.Context;
import context.ContextType;
import context.CoreContext;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import listener.Listener;
import manager.StageManager;
import notification.NotificationUpdate;
import notification.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import service.SubscriberService;
import view.ViewType;

import java.rmi.RemoteException;

import static utils.Conditional.basedOn;
import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class ControllerTopBar
        implements ControllerInterface, SubscriberService {

    @FXML
    @Context({ContextType.REGULAR})
    public Label usernameLabel;

    @FXML
    private Button userProfileButton;

    @FXML
    @Context({ContextType.GUEST})
    public Button signUpButton;

    @FXML
    @Context({ContextType.GUEST})
    public Button loginButton;

    @Lazy
    @Autowired
    private StageManager manager;

    @Lazy
    @Autowired
    private Listener listener;

    @Lazy
    @Autowired
    private CoreContext context;

    @Override
    public void initialize() throws RemoteException {
        context.in(this).forType(ContextType.REGULAR)
                .run(item -> ((Label) item).setText(""));
        context.basedOn(listener.getActiveUser() != null)
                .in(this)
                .forType(ContextType.GUEST)
                .run(item -> ((Button) item).setText(runFunction(
                        listener::getActiveUser).or(null).getName()));
        manager.getPrimaryStage().setOnCloseRequest(event ->
                runFunction(listener::removeSubscriber, this).orHandle(System.out::print));
        runFunction(listener::addSubscriber, this).orHandle(System.out::println);
    }

    private Boolean existsActiveUser() throws RemoteException {
        return listener.getActiveUser() != null;
    }

    /**
     * Effect: Loads the LoginView responsible
     * for the user's authentication process.
     *
     * @implNote status: In development
     */
    @FXML
    private void onLoginButtonClick() {
        manager.switchScene(ViewType.LOGIN);
    }

    /**
     * Effect: Loads the SignUpView responsible
     * for the user's authentication process.
     *
     * @implNote status: In development
     */
    @FXML
    private void onSignUpButtonClick() {
        manager.switchScene(ViewType.SIGN_UP);
    }

    /**
     * Effect: Loads the ConferencesView responsible
     * for listing all the available conferences.
     *
     * @implNote status: In development.
     */
    @FXML
    private void onLogoButtonClick() {
        manager.switchScene(ViewType.CONFERENCES);
    }

    /**
     * Effect: Loads the ProfileView responsible
     * for updating the active user's profile information.
     *
     * @implNote status: In development.
     */
    @FXML
    private void onProfileButtonClick() throws RemoteException {
        basedOn(existsActiveUser())
                .runTrue(manager::switchScene, ViewType.PROFILE, listener.getActiveUser());
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
    public void update(NotificationUpdate notification) throws RemoteException {
        if (notification.getType().equals(NotificationType.SIGNAL_LOGIN) ||
                notification.getType().equals(NotificationType.SIGNAL_SIGN_UP) ||
                notification.getType().equals(NotificationType.UPDATE_USER)) {
            Platform.runLater(() -> runFunction(this::showActiveUser).orHandle(System.out::println));
        }
        if (notification.getType().equals(NotificationType.SIGNAL_LOGOUT)) {
            Platform.runLater(this::showRegistrationButtons);
        }
    }
}
