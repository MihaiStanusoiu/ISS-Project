package controller.component;

import context.Context;
import context.ContextClass;
import context.ContextType;
import context.CoreContext;
import controller.main.ControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import listener.ListenerHelper;
import manager.StageManager;
import notification.NotificationUpdate;
import notification.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import view.ViewType;

import java.rmi.RemoteException;


/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
@ContextClass
public class ControllerMenu implements ControllerInterface {

    @FXML
    @Context({ContextType.GUEST})
    public Button logoutButton;

    @FXML
    public Button myConferencesButton;

    @Lazy
    @Autowired
    private StageManager manager;

    @Lazy
    @Autowired
    private ListenerHelper listener;

    @Lazy
    @Autowired
    private CoreContext context;


    @Override
    public void initialize() {
        context.forType(ContextType.GUEST).in(this)
                .run(item -> ((Node) item).setVisible(Boolean.FALSE));
    }

    /**
     * Effect: Loads the ConferencesView responsible
     * for listing all the available conferences.
     *
     * @implNote status: In development.
     */
    @FXML
    private void onConferencesViewButtonClick() {
        manager.switchScene(ViewType.CONFERENCES);
    }

    /**
     * Effect: Loads the AddConferenceView responsible
     * for creating a new conference by active user.
     *
     * @implNote status: Unavailable at the moment.
     */
    @FXML
    private void onAddConferenceViewButtonClick() {
        manager.switchScene(ViewType.ADD_CONFERENCE, null);
    }

    /**
     * Effect: Loads the NotificationsView responsible
     * for listing all the user's notifications.
     *
     * @implNote status: In development.
     */
    @FXML
    private void onNotificationsViewButtonClick() {
        manager.switchScene(ViewType.NOTIFICATIONS);
    }

    /**
     * Effect: Loads the UsersView responsible
     * for listing all the available users from our system.
     *
     * @implNote status: In development.
     */
    @FXML
    private void onUsersViewButtonClick() {
        manager.switchScene(ViewType.USERS);
    }

    /**
     * Effect: Loads the SubmissionsView responsible
     * for listing all the user's submissions.
     *
     * @implNote status: Unavailable at the moment.
     */
    @FXML
    private void onSubmissionsViewButtonClick() {
        System.out.println("Submissions View");
    }

    /**
     * Effect: Loads the MyConferencesView responsible
     * for listing all the user's conferences.
     *
     * @implNote status: In development.
     */
    @FXML
    private void onMyConferencesViewButtonClick() {
        manager.switchScene(ViewType.MY_CONFERENCES);
    }

    /**
     * Effect: UserEntity logout from server's system.
     *
     * @implNote status: Unavailable at the moment.
     */
    @FXML
    private void onLogoutButtonClick() throws RemoteException {
        listener.setActiveUser(null);
        listener.notifyAll(new NotificationUpdate(NotificationType.SIGNAL_LOGOUT));
    }

}
