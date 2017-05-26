package itemcontroller;

import controller.ControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import manager.StageManager;
import notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import service.SubscriberService;
import transferable.Conference;
import view.ViewType;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class ControllerAddConferenceView
        implements ControllerInterface, ControllerItemInterface<Conference>, SubscriberService {

    @FXML private TextField nameTextField;
    @FXML private TextField acronymTextField;
    @FXML private TextField locationTextField;
    @FXML private TextField bioTextField;

    @Lazy
    @Autowired
    private StageManager manager;


    private Conference conference;

    @Override
    public void setElement(Conference element) {
        this.conference = element;
    }

    /**
     * Effect: Builds the pagination and it's data.
     */
    public void initialize() { }

    @Override
    public void update(Notification notification) throws RemoteException { }

    @FXML private void onPublishButtonClick() {
        System.out.print("Publish Button Click" +  conference.getName());
        // TODO
    }

    @FXML private void onSaveButtonClick() {
        System.out.print("Save Button Click" +  conference.getName());
        // TODO
    }

    @FXML private void onMembersButtonClick() {
        System.out.print("Members Button Click" +  conference.getName());
        manager.switchScene(ViewType.MEMBERS_CONFERENCE, conference);
        // TODO
    }

    @FXML private void onBasicButtonClick() {
        System.out.print("Basic Button Click" +  conference.getName());
        // TODO
    }

    @FXML private void onSessionsButtonClick() {
        System.out.print("Session Button Click" +  conference.getName());
        // TODO
    }

    @FXML private void onSubmissionsButtonClick() {
        System.out.print("Submission Button Click" +  conference.getName());
        // TODO
    }

}
