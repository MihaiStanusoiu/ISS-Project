package controller.registration;

import controller.main.ControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import listener.Listener;
import manager.StageManager;
import method.SimpleMethod;
import notification.NotificationUpdate;
import notifier.EventType;
import notifier.LocalNotificationCenter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import service.AuthenticationService;
import service.CollectionService;
import service.LoginService;
import service.SubscriberService;
import transfarable.User;
import utils.Try;
import view.ViewType;

import java.rmi.RemoteException;

import static utils.Conditional.basedOn;
import static utils.Try.runFunction;
import static utils.Try.runMethod;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class ControllerLogin implements ControllerInterface, SubscriberService {

    private static Logger logger;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Label errorLabel;

    @FXML
    private ImageView backgroundImage;

    @FXML
    private StackPane backgroundImagePane;

    @Autowired
    private LocalNotificationCenter center;

    @Lazy
    @Autowired
    private StageManager manager;

    @Lazy
    @Autowired
    private CollectionService service;

    private LoginService loginService;

    private final SimpleMethod<RemoteException> handler =
            exception -> logger.error(exception.getCause());

    private final SimpleMethod<RemoteException> printer =
            exception -> errorLabel.setText(exception.getCause().getMessage());

    @Lazy
    @Autowired
    private Listener listener;

    /**
     * Effect: Adds width & height constraints on the
     * background image view in order to be responsive.
     */
    @Override
    public void initialize() {
        logger = Logger.getLogger(ControllerLogin.class);
        loginService = runFunction(service::loginService).orHandle(handler);
        backgroundImage.fitWidthProperty().bind(backgroundImagePane.widthProperty());
        backgroundImage.fitHeightProperty().bind(backgroundImagePane.heightProperty());
        manager.getPrimaryStage().setOnCloseRequest(event ->
                Try.runMethod(listener::removeSubscriber, this).orHandle(System.out::print));
        Try.runMethod(listener::addSubscriber, this).orHandle(System.out::println);

    }

    /**
     * Effect: Loads the ConferencesView.
     *
     * @implNote status: In development.
     */
    @FXML
    void onLogoButtonClick() throws RemoteException {
        manager.switchScene(ViewType.CONFERENCES);
    }

    /**
     * Effect: Loads the SignUpView.
     *
     * @implNote status: In development.
     */
    @FXML
    void onSignUpButtonClick() throws RemoteException {
        manager.switchScene(ViewType.SIGN_UP);
    }

    /**
     * Effect: The user logs in the system with account data.
     *
     * @implNote status: Unavailable at the moment.
     */
    @FXML
    void onLoginButtonClick() {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        User user = runFunction(loginService::login, username, password).orHandle(printer);
        basedOn(!user.getId().equals(0)).runTrue(this::makeUserActive, user);
    }

    private void makeUserActive(User user) {
        AuthenticationService authenticationService = runFunction(service::authenticationService).orHandle(handler);
        runMethod(authenticationService::addActiveUser, user);
        // TODO Notify Everyone About Login Event;
        center.notifyObservers(EventType.LOGIN);
        manager.switchScene(ViewType.CONFERENCES);
    }

    @Override
    public void update(NotificationUpdate notification) throws RemoteException {
        System.out.print(notification.getType());
    }
}
