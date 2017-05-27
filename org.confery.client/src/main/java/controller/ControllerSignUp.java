package controller;

import domain.UserEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import listener.Listener;
import manager.StageManager;
import notification.Notification;
import notification.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import service.SignUpService;
import service.SubscriberService;
import view.ViewType;

import java.rmi.RemoteException;

import static utils.Try.runFunction;

/**
 * @author      Alexandru Stoica
 * @version     1.0
 */

@Lazy
@Component
public class ControllerSignUp implements ControllerInterface, SubscriberService {

    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField displayNameTextField;
    @FXML private TextField confirmTextField;
    @FXML private Label errorLabel;
    @FXML private ImageView backgroundImage;
    @FXML private StackPane backgroundImagePane;

    @Lazy
    @Autowired
    private StageManager manager;

    @Lazy
    @Autowired
    private SignUpService signUpService;

    @Lazy
    @Autowired
    private Listener listener;

    /**
     * Effect: Adds width & height constraints on the
     * background image view in order to be responsive.
     */
    @Override
    public void initialize() {
        backgroundImage.fitWidthProperty().bind(backgroundImagePane.widthProperty());
        backgroundImage.fitHeightProperty().bind(backgroundImagePane.heightProperty());
        manager.getPrimaryStage().setOnCloseRequest(event ->
                runFunction(listener::removeSubscriber, this).orHandle(System.out::print));
        runFunction(listener::addSubscriber, this).orHandle(System.out::println);
    }

    /**
     * Effect: Loads the ConferencesView.
     * @implNote status: In development.
     */
    @FXML void onLogoButtonClick() throws RemoteException {
        manager.switchScene(ViewType.CONFERENCES);
    }

    /**
     * Effect: Loads the LoginView.
     * @implNote status: In development.
     */
    @FXML void onLoginButtonClick() throws RemoteException {
        manager.switchScene(ViewType.LOGIN);
    }

    /**
     * Effect: The user registers in the system with his data.
     * @implNote status: Unavailable at the moment.
     */
    @FXML void onSignUpButtonClick() throws RemoteException {
        String email = emailTextField.getText();
        String displayName = displayNameTextField.getText();
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String confirm = confirmTextField.getText();
        UserEntity user = this.signUpService.signUp(username, password, confirm, email, displayName);
        this.listener.setActiveUser(user);
        this.listener.notifyAll(new Notification(NotificationType.SIGNAL_SIGN_UP));
        manager.switchScene(ViewType.CONFERENCES);
    }

    @Override
    public void update(Notification notification) throws RemoteException {
        System.out.print(notification);
    }
}
