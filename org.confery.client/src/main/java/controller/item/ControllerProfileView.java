package controller.item;

import controller.main.ControllerInterface;
import itemcontroller.ControllerItemInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import manager.StageManager;
import method.SimpleMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import service.AuthenticationService;
import service.CollectionService;
import service.UserService;
import transfarable.User;
import view.ViewType;

import java.rmi.RemoteException;

import static utils.Try.runFunction;
import static utils.Try.runMethod;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class ControllerProfileView
        implements ControllerInterface, ControllerItemInterface<User> {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField websiteTextField;

    @FXML
    private TextField locationTextField;

    @FXML
    private TextField bioTextField;

    @FXML
    private TextField emailTextField;

    private User user;

    @Lazy
    @Autowired
    private StageManager manager;

    @Lazy
    @Autowired
    private CollectionService collectionService;

    private UserService userService;
    private AuthenticationService authenticationService;

    private static Logger logger;

    private SimpleMethod<RemoteException> handler;

    public void initialize() {
        logger = Logger.getLogger(ControllerProfileView.class);
        handler = exception -> logger.error(exception.getCause());
        userService = runFunction(collectionService::userService).orHandle(handler);
        authenticationService = runFunction(collectionService::authenticationService).orHandle(handler);
    }

    @FXML
    public void onSaveButtonClick() {
        User other = new User(user.getId(), user.getUsername(),
                user.getPassword(), emailTextField.getText(),
                nameTextField.getText(), websiteTextField.getText(),
                bioTextField.getText(), locationTextField.getText());
        runMethod(userService::update, user, other).orHandle(handler);
        manager.refresh(other);
    }

    @FXML
    public void onDeleteButtonClick() {
        User active = runFunction(authenticationService::getActiveUser).orHandle(handler);
        runFunction(userService::delete, active).orHandle(handler);
        runMethod(authenticationService::deleteActiveUser, active).orHandle(handler);
        manager.switchScene(ViewType.CONFERENCES);
    }

    private void updateUserData() {
        nameTextField.setText(user.getName());
        websiteTextField.setText(user.getWebsite());
        locationTextField.setText(user.getLocation());
        bioTextField.setText(user.getBio());
        emailTextField.setText(user.getEmail());
    }

    @Override
    public void setElement(User element) {
        user = element;
        updateUserData();
    }

}
