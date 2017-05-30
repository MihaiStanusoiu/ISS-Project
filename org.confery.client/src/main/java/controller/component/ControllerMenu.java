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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import utils.ConferenceContext;
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

    @FXML
    private void onConferencesViewButtonClick() {
        manager.switchScene(ViewType.CONFERENCES);
    }

    @FXML
    private void onAddConferenceViewButtonClick() {
        manager.switchScene(ViewType.ADD_CONFERENCE, new ConferenceContext());
    }

    @FXML
    private void onNotificationsViewButtonClick() {
        manager.switchScene(ViewType.NOTIFICATIONS);
    }

    @FXML
    private void onUsersViewButtonClick() {
        manager.switchScene(ViewType.USERS);
    }

    @FXML
    private void onSubmissionsViewButtonClick() {
        System.out.println("Submissions View");
    }

    @FXML
    private void onMyConferencesViewButtonClick() {
        manager.switchScene(ViewType.MY_CONFERENCES);
    }

    @FXML
    private void onLogoutButtonClick() throws RemoteException {

    }

}
