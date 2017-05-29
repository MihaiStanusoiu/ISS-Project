package itemcontroller;

import controller.main.ControllerInterface;
import domain.UserEntity;
import exception.SystemException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import listener.Listener;
import manager.StageManager;
import notification.NotificationUpdate;
import notification.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import service.SubscriberService;

import view.ViewType;

import java.rmi.RemoteException;

import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class ControllerProfileView implements ControllerInterface,
        ControllerItemInterface<UserEntity>, SubscriberService {

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


    /**
     * Effect: Builds the pagination and it's data.
     */
    public void initialize() {
        manager.getPrimaryStage().setOnCloseRequest(event ->
                runFunction(listener::removeSubscriber, this).orHandle(System.out::print));
        runFunction(listener::addSubscriber, this).orHandle(System.out::println);
    }

    @FXML
    public void onSaveButtonClick() throws RemoteException, SystemException {
        UserEntity other = new UserEntity(user.getId(), user.getUsername(),
                user.getPassword(), emailTextField.getText(),
                nameTextField.getText(), websiteTextField.getText(),
                bioTextField.getText(), locationTextField.getText());
        listener.setActiveUser(other);
        listener.notifyAll(new NotificationUpdate(NotificationType.UPDATE_USER));
    }

    @FXML
    public void onDeleteButtonClick() throws RemoteException, SystemException {
        //userService.delete(user);
        listener.notifyAll(new NotificationUpdate(NotificationType.SIGNAL_LOGOUT));
        listener.setActiveUser(null);
        listener.removeSubscriber(this);
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
    public void update(NotificationUpdate notification) throws RemoteException {
        if (notification.getType().equals(NotificationType.UPDATE_USER)) {
            updateUserData();
        }
    }

    @Override
    public void setElement(UserEntity element) {
        user = element;
        updateUserData();
    }

}
