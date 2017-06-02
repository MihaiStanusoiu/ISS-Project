package controller.registration;

import controller.main.ControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import listener.Listener;
import manager.StageManager;
import method.SimpleMethod;
import notification.NotificationUpdate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import service.AuthenticationService;
import service.CollectionService;
import service.SignUpService;
import service.SubscriberService;
import transfarable.User;
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
public class ControllerSignUp implements ControllerInterface, SubscriberService {

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField displayNameTextField;

    @FXML
    private TextField confirmTextField;

    @FXML
    private Label errorLabel;

    @FXML
    private ImageView backgroundImage;

    @FXML
    private StackPane backgroundImagePane;

    @Lazy
    @Autowired
    private StageManager manager;

    @Lazy
    @Autowired
    private CollectionService service;

    @Lazy
    @Autowired
    private Listener listener;

    @FXML
    private Button signUpButton;

    private SignUpService signUpService;

    private SimpleMethod<RemoteException> handler;

    private SimpleMethod<RemoteException> printer;

    private static Logger logger;

    /**
     * Effect: Adds width & height constraints on the
     * background image view in order to be responsive.
     */
    @Override
    public void initialize() {
        logger = Logger.getLogger(ControllerSignUp.class);
        handler = exception -> logger.info(exception.getCause().getMessage());
        printer = exception -> errorLabel.setText(exception.getCause().getMessage());
        signUpService = runFunction(service::signUpService).orHandle(handler);
        backgroundImage.fitWidthProperty().bind(backgroundImagePane.widthProperty());
        backgroundImage.fitHeightProperty().bind(backgroundImagePane.heightProperty());
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
     * Effect: Loads the LoginView.
     *
     * @implNote status: In development.
     */
    @FXML
    void onLoginButtonClick() throws RemoteException {
        manager.switchScene(ViewType.LOGIN);
    }

    /**
     * Effect: The user registers in the system with his data.
     *
     * @implNote status: Unavailable at the moment.
     */
    @FXML
    void onSignUpButtonClick() {
        String email = emailTextField.getText();
        String displayName = displayNameTextField.getText();
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String confirm = confirmTextField.getText();
        User user = runFunction(signUpService::signUp, username, password, confirm, email, displayName).orHandle(printer);
        basedOn(user != null && !user.getId().equals(0)).runTrue(this::makeUserActive, user);
    }

    private void makeUserActive(User user) {
        AuthenticationService authenticationService = runFunction(service::authenticationService).orHandle(handler);
        runMethod(authenticationService::addActiveUser, user).orHandle(handler);
        manager.switchScene(ViewType.CONFERENCES);
    }

    @Override
    public void update(NotificationUpdate notification) throws RemoteException {
        System.out.print(notification);
    }
}
