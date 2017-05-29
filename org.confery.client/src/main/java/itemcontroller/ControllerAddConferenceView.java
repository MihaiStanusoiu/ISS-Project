package itemcontroller;

import controller.main.ControllerInterface;
import domain.ConferenceEntity;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import view.ViewType;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class ControllerAddConferenceView
        implements ControllerInterface, ControllerItemInterface<ConferenceEntity> {

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

    public void initialize() { }

    @FXML
    private void onPublishButtonClick() { }

    @FXML
    private void onSaveButtonClick() { }

    @FXML
    private void onMembersButtonClick() {
        manager.switchScene(ViewType.MEMBERS_CONFERENCE, conference);
    }

    @FXML
    private void onBasicButtonClick() { }

    @FXML
    private void onSessionsButtonClick() { }

    @FXML
    private void onSubmissionsButtonClick() { }

}
