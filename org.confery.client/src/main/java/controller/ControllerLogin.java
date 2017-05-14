package controller;

import exception.RepositoryException;
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
import service.LoginService;
import service.SubscriberService;
import transferable.User;
import view.ViewType;

import java.rmi.RemoteException;

/**
 * Name:        ControllerLogin
 * Effect:      Controls the login-view.
 * Date:        08/04/2017
 * Tested:      False
 *
 * @author      Alexandru Stoica
 * @version     1.0
 */

@Component
public class ControllerLogin implements ControllerInterface, SubscriberService {

    @FXML TextField usernameTextField;
    @FXML TextField passwordTextField;
    @FXML Label errorLabel;
    @FXML ImageView backgroundImage;
    @FXML StackPane backgroundImagePane;

    private final StageManager manager;
    private final LoginService loginService;
    private final Listener listener;

    @Autowired @Lazy
    public ControllerLogin(StageManager manager, LoginService loginService, Listener listener) throws RemoteException {
        this.manager = manager;
        this.loginService = loginService;
        this.listener = listener;
        this.listener.addSubscriber(this);
    }

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
        listener.removeSubscriber(this);
        manager.switchScene(ViewType.CONFERENCES);
    }


    /**
     * Effect: Loads the SignUpView.
     * @implNote status: In development.
     */
    @FXML void onSignUpButtonClick() throws RemoteException {
        listener.removeSubscriber(this);
        manager.switchScene(ViewType.SIGN_UP);
    }

    /**
     * Effect: The user logs in the system with account data.
     * @implNote status: Unavailable at the moment.
     */
    @FXML void onLoginButtonClick() throws RemoteException, RepositoryException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        try {
            User user = loginService.login(username, password);
            listener.setActiveUser(user);
            listener.notifyAll(new Notification(NotificationType.SIGNAL_LOGIN));
            listener.removeSubscriber(this);
            manager.switchScene(ViewType.CONFERENCES);
        } catch (RemoteException exception) {
            errorLabel.setText(exception.getCause().getMessage());
        }
    }

    @Override
    public void update(Notification notification) throws RemoteException {
        System.out.print(notification.getType());
    }
}
