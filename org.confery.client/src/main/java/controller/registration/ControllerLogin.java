package controller.registration;

import controller.main.ControllerInterface;
import exception.SystemException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import listener.Listener;
import manager.StageManager;
import notification.NotificationType;
import notification.NotificationUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import service.CollectionService;
import service.SubscriberService;
import utils.Try;
import view.ViewType;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class ControllerLogin implements ControllerInterface, SubscriberService {

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

    @Lazy
    @Autowired
    private StageManager manager;

    @Lazy
    @Autowired
    private CollectionService service;

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
    void onLoginButtonClick() throws RemoteException, SystemException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        try {
            //UserEntity user = service.loginService().login(username, password);
            //listener.setActiveUser(user);
            listener.notifyAll(new NotificationUpdate(NotificationType.SIGNAL_LOGIN));
            manager.switchScene(ViewType.CONFERENCES);
        } catch (RemoteException exception) {
            errorLabel.setText(exception.getCause().getMessage());
        }
    }

    @Override
    public void update(NotificationUpdate notification) throws RemoteException {
        System.out.print(notification.getType());
    }
}
