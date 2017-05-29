package controller.component;

import context.Context;
import context.ContextType;
import context.CoreContext;
import controller.main.ControllerInterface;
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
import utils.Try;
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
                Try.runMethod(listener::removeSubscriber, this).orHandle(System.out::print));
        Try.runMethod(listener::addSubscriber, this).orHandle(System.out::println);
    }

    private Boolean existsActiveUser() throws RemoteException {
        return listener.getActiveUser() != null;
    }

    @FXML
    private void onLoginButtonClick() {
        manager.switchScene(ViewType.LOGIN);
    }

    @FXML
    private void onSignUpButtonClick() {
        manager.switchScene(ViewType.SIGN_UP);
    }

    @FXML
    private void onLogoButtonClick() {
        manager.switchScene(ViewType.CONFERENCES);
    }

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
            Platform.runLater(() -> Try.runMethod(this::showActiveUser).orHandle(System.out::println));
        }
        if (notification.getType().equals(NotificationType.SIGNAL_LOGOUT)) {
            Platform.runLater(this::showRegistrationButtons);
        }
    }
}
