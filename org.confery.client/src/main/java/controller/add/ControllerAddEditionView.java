package controller.add;

import controller.main.ControllerInterface;
import itemcontroller.ControllerItemInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import manager.StageManager;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import transfarable.Edition;
import utils.ConferenceContext;
import view.ViewType;

import java.util.Date;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
@SuppressWarnings("EmptyMethod")
public class ControllerAddEditionView
        implements ControllerInterface, ControllerItemInterface<ConferenceContext> {


    @FXML
    private TextField locationTextField;

    @FXML
    private Label conferenceNameLabel;

    @FXML
    private TextField bioTextField;

    @Lazy
    @Autowired
    private StageManager manager;

    private ConferenceContext context;

    @Override
    public void setElement(ConferenceContext element) {
        this.context = element;
        build();
    }

    private void build() {
        conferenceNameLabel.setText(context.getConference().getName());
        locationTextField.setText(context.getEdition().getLocation());
        bioTextField.setText(context.getEdition().getBio());
    }

    public void initialize() { }

    @FXML
    private void onBackButtonClick() {
        context.updateEdition(getCurrentEdition());
        manager.switchScene(ViewType.ADD_CONFERENCE, context);
    }

    @NotNull
    private Edition getCurrentEdition() {
        return new Edition(0, new Date(), new Date(), locationTextField.getText(),
                bioTextField.getText(), new Date(), new Date(), new Date(), new Date());
    }

    @FXML
    private void onPublishButtonClick() { }

    @FXML
    private void onSaveButtonClick() { }

    @FXML
    private void onMembersButtonClick() {
        // manager.switchScene(ViewType.MEMBERS_CONFERENCE, conference);
    }

    @FXML
    private void onBasicButtonClick() { }

    @FXML
    private void onSessionsButtonClick() { }

    @FXML
    private void onSubmissionsButtonClick() { }

}
