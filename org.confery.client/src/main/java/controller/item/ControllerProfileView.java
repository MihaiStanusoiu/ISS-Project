package controller.item;

import controller.main.ControllerInterface;
import domain.UserEntity;
import exception.SystemException;
import itemcontroller.ControllerItemInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import listener.Listener;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class ControllerProfileView implements ControllerInterface, ControllerItemInterface<UserEntity> {

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

    private UserEntity user;

    @Lazy
    @Autowired
    private StageManager manager;

    @Lazy
    @Autowired
    private Listener listener;

    public void initialize() { }

    @FXML
    public void onSaveButtonClick() throws RemoteException, SystemException {
        UserEntity other = new UserEntity(user.getId(), user.getUsername(),
                user.getPassword(), emailTextField.getText(),
                nameTextField.getText(), websiteTextField.getText(),
                bioTextField.getText(), locationTextField.getText());
    }

    @FXML
    public void onDeleteButtonClick() throws RemoteException, SystemException {
        //userService.delete(user);
//        listener.notifyAll(new NotificationUpdate(NotificationType.SIGNAL_LOGOUT));
//        listener.setActiveUser(null);
//        manager.switchScene(ViewType.CONFERENCES);
    }

    private void updateUserData() {
        nameTextField.setText(user.getName());
        websiteTextField.setText(user.getWebsite());
        locationTextField.setText(user.getLocation());
        bioTextField.setText(user.getBio());
        emailTextField.setText(user.getEmail());
    }

    @Override
    public void setElement(UserEntity element) {
        user = element;
        updateUserData();
    }

}
