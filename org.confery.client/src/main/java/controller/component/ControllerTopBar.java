package controller.component;

import context.Context;
import context.ContextType;
import context.CoreContext;
import controller.main.ControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import manager.StageManager;
import method.SimpleMethod;
import notification.NotificationUpdate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import service.AuthenticationService;
import service.CollectionService;
import service.SubscriberService;
import transfarable.User;
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
    private CollectionService collectionService;

    private AuthenticationService authenticationService;

    @Lazy
    @Autowired
    private CoreContext context;

    private static Logger logger;
    private SimpleMethod<RemoteException> handler;

    @Override
    public void initialize() {
        logger = Logger.getLogger(ControllerTopBar.class);
        handler = exception -> logger.error(exception.getMessage());
        authenticationService = runFunction(collectionService::authenticationService).orHandle(handler);
        User active = runFunction(authenticationService::getActiveUser).orHandle(handler);
        showRegistrationButtons(active);
        showUsername(active);
    }

    private void showRegistrationButtons(User active) {
        context.basedOn(!active.getId().equals(0))
                .forType(ContextType.GUEST).in(this)
                .run(button -> ((Button)button).setVisible(false))
                .or(button -> ((Button)button).setVisible(true));
    }

    private void showUsername(User active) {
        context.basedOn(!active.getId().equals(0))
                .forType(ContextType.REGULAR).in(this)
                .run(label -> ((Label)label).setText(active.getName()))
                .or(label -> ((Label)label).setText(""));
    }

    private Boolean existsActiveUser() {
        return !getActiveUser().getId().equals(0);
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
    private void onProfileButtonClick() {
        basedOn(existsActiveUser())
                .runTrue(manager::switchScene, ViewType.PROFILE, getActiveUser());
    }

    private User getActiveUser() {
        return runFunction(authenticationService::getActiveUser).orHandle(handler);
    }

    @Override
    public void update(NotificationUpdate notification) throws RemoteException {

    }
}
