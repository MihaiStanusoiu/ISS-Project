package controller;

import javafx.fxml.FXML;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import view.ViewType;

/**
 * Name:        ControllerMenu
 * Effect:      Controls the main side navigation system.
 * Date:        05/04/2017
 * Tested:      False
 *
 * @author      Alexandru Stoica
 * @version     1.0
 */

@Component
public class ControllerMenu implements ControllerInterface {

    private final StageManager manager;

    @Autowired @Lazy
    public ControllerMenu(StageManager manager) {
        this.manager = manager;
    }

    @Override
    public void initialize() { }

    /**
     * Effect: Loads the ConferencesView responsible
     * for listing all the available conferences.
     * @implNote status: In development.
     */
    @FXML private void onConferencesViewButtonClick() {
        manager.switchScene(ViewType.CONFERENCES);
    }

    /**
     * Effect: Loads the AddConferenceView responsible
     * for creating a new conference by active user.
     * @implNote status: Unavailable at the moment.
     */
    @FXML private void onAddConferenceViewButtonClick() {
        System.out.println("Add ConferenceEntity View");
    }

    /**
     * Effect: Loads the NotificationsView responsible
     * for listing all the user's notifications.
     * @implNote status: In development.
     */
    @FXML private void onNotificationsViewButtonClick() {
        manager.switchScene(ViewType.NOTIFICATIONS);
    }

    /**
     * Effect: Loads the UsersView responsible
     * for listing all the available users from our system.
     * @implNote status: In development.
     */
    @FXML private void onUsersViewButtonClick() {
        manager.switchScene(ViewType.USERS);
    }

    /**
     * Effect: Loads the SubmissionsView responsible
     * for listing all the user's submissions.
     * @implNote status: Unavailable at the moment.
     */
    @FXML private void onSubmissionsViewButtonClick() {
        System.out.println("Submissions View");
    }

    /**
     * Effect: Loads the MyConferencesView responsible
     * for listing all the user's conferences.
     * @implNote status: In development.
     */
    @FXML private void onMyConferencesViewButtonClick() {
        manager.switchScene(ViewType.MY_CONFERENCES);
    }

    /**
     * Effect: UserEntity logout from server's system.
     * @implNote status: Unavailable at the moment.
     */
    @FXML private void onLogoutButtonClick() {
        System.out.println("Logout View");
    }

}
