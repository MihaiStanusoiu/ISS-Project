package controller;

import exception.SystemException;
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
import transferable.User;
import view.ViewType;

import java.rmi.RemoteException;

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
    }

    /**
     * Effect: Loads the ConferencesView.
     * @implNote status: In development.
     */
    @FXML void onLogoButtonClick() throws RemoteException {
        this.listener.removeSubscriber(this);
        manager.switchScene(ViewType.CONFERENCES);
    }

    /**
     * Effect: Loads the LoginView.
     * @implNote status: In development.
     */
    @FXML void onLoginButtonClick() throws RemoteException {
        this.listener.removeSubscriber(this);
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
        try {
            User user = this.signUpService.signUp(username, password, confirm, email, displayName);
            this.listener.setActiveUser(user);
            this.listener.notifyAll(new Notification(NotificationType.SIGNAL_SIGN_UP));
            this.listener.removeSubscriber(this);
            manager.switchScene(ViewType.CONFERENCES);
        } catch (SystemException exception) {
            errorLabel.setText(exception.getCause().getMessage());
        }
    }

    @Override
    public void update(Notification notification) throws RemoteException {
        System.out.print(notification);
    }
}
