package itemcontroller;

import controller.main.ControllerInterface;
import domain.ConferenceEntity;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import manager.StageManager;
import notification.NotificationUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import service.SubscriberService;
import view.ViewType;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class ControllerAddConferenceView
        implements ControllerInterface, ControllerItemInterface<ConferenceEntity>, SubscriberService {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField acronymTextField;

    @FXML
    private TextField locationTextField;

    @FXML
    private TextField bioTextField;

    @Lazy
    @Autowired
    private StageManager manager;


    private ConferenceEntity conference;

    @Override
    public void setElement(ConferenceEntity element) {
        this.conference = element;
    }

    /**
     * Effect: Builds the pagination and it's data.
     */
    public void initialize() {
    }

    @Override
    public void update(NotificationUpdate notification) throws RemoteException {
    }

    @FXML
    private void onPublishButtonClick() {
        // TODO
    }

    @FXML
    private void onSaveButtonClick() {
        // TODO
    }

    @FXML
    private void onMembersButtonClick() {
        manager.switchScene(ViewType.MEMBERS_CONFERENCE, conference);
        // TODO
    }

    @FXML
    private void onBasicButtonClick() {
        // TODO
    }

    @FXML
    private void onSessionsButtonClick() {
        // TODO
    }

    @FXML
    private void onSubmissionsButtonClick() {
        // TODO
    }

}
