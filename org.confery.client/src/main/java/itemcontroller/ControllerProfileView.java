package itemcontroller;

import controller.ControllerInterface;
import exception.SystemException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import listener.Listener;
import manager.StageManager;
import notification.Notification;
import notification.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import service.SubscriberService;
import service.UserService;
import transferable.User;
import view.ViewType;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */


@Component
public class ControllerProfileView implements ControllerInterface,
        ControllerItemInterface<User>, SubscriberService {

    @FXML private TextField nameTextField;
    @FXML private TextField websiteTextField;
    @FXML private TextField locationTextField;
    @FXML private TextField bioTextField;
    @FXML private TextField emailTextField;

    private User user;

    private final StageManager manager;
    private final Listener listener;
    private final UserService userService;

    @Autowired @Lazy
    public ControllerProfileView(StageManager manager, Listener listener, UserService userService)
            throws RemoteException {
        this.manager = manager;
        this.userService = userService;
        this.listener = listener;
        this.listener.addSubscriber(this);
    }

    /**
     * Effect: Builds the pagination and it's data.
     */
    public void initialize() { }

    @FXML
    public void onSaveButtonClick() throws RemoteException, SystemException {
        User other = new User(user.getId(), user.getUsername(),
                user.getPassword(), emailTextField.getText(),
                nameTextField.getText(), websiteTextField.getText(),
                bioTextField.getText(), locationTextField.getText(), user.getType());
        userService.update(user, other);
        listener.setActiveUser(other);
        listener.notifyAll(new Notification(NotificationType.UPDATE_USER));
    }

    @FXML
    public void onDeleteButtonClick() throws RemoteException, SystemException {
        userService.delete(user);
        listener.notifyAll(new Notification(NotificationType.SIGNAL_LOGOUT));
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
    public void update(Notification notification) throws RemoteException {
        if(notification.getType().equals(NotificationType.UPDATE_USER)) {
            updateUserData();
        }
    }

    @Override
    public void setElement(User element) {
        user = element;
        updateUserData();
    }

}
