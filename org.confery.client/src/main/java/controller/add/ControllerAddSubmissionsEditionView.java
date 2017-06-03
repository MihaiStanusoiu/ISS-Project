package controller.add;

import controller.main.ControllerInterface;
import itemcontroller.ControllerItemInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import manager.StageManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import utils.ConferenceContext;
import view.ViewType;

import static utils.Try.runMethod;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class ControllerAddSubmissionsEditionView
        implements ControllerInterface, ControllerItemInterface<ConferenceContext> {

    @FXML
    private Label conferenceNameLabel;

    @Lazy
    @Autowired
    @SuppressWarnings("unused")
    private StageManager manager;

    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private ConferenceContext context;

    @Override
    public void setElement(ConferenceContext element) {
        this.context = element;
        conferenceNameLabel.setText(context.getConference().getName());
    }

    public void initialize() { }

    @FXML
    private void onSessionsButtonClick() {
        manager.switchScene(ViewType.ADD_SESSION, context);
    }

    @FXML
    private void onPublishButtonClick() {
        Logger logger = Logger.getLogger(ControllerAddConferenceView.class);
        runMethod(context::publish).orHandle(exception -> logger.error(exception.getMessage()));
        manager.switchScene(ViewType.CONFERENCES);
    }

    @FXML
    private void onBasicButtonClick() {
        manager.switchScene(ViewType.ADD_EDITION, context);
    }

    @FXML
    private void onMembersButtonClick() {
        manager.switchScene(ViewType.MEMBERS_CONFERENCE, context);
    }

    @FXML
    private void onBackButtonClick() {
        manager.switchScene(ViewType.ADD_CONFERENCE, context);
    }

}
