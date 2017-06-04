package controller.add;

import controller.ControllerEdition;
import controller.main.ControllerInterface;
import itemcontroller.ControllerItemInterface;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import manager.StageManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import transfarable.Edition;
import utils.ConferenceContext;
import view.ViewType;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static utils.Try.runFunction;
import static utils.Try.runMethod;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
@SuppressWarnings("EmptyMethod")
public class ControllerAddEditionView extends ControllerEdition
        implements ControllerInterface, ControllerItemInterface<ConferenceContext> {

    private static Logger logger;

    @FXML
    private TextField locationTextField;

    @FXML private Label conferenceNameLabel;

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
        showDates(context.getEdition());
    }

    public void initialize() {
        logger = Logger.getLogger(ControllerAddEditionView.class);
    }

    @FXML
    private void onBackButtonClick() {
        context.getEditionContext().updateEdition(getCurrentEdition());
        manager.switchScene(ViewType.ADD_CONFERENCE, context);
    }

    @NotNull
    private Edition getCurrentEdition() {
        return new Edition(0, getStartingDate(), getEndingDate(), locationTextField.getText(),
                bioTextField.getText(), getAbstractDate(), getPaperDate(), getEvaluationDate(), getBiddingDate());
    }

    private Date getEvaluationDate() {
        return runFunction(this::convertToDate, evaluationDateDay, evaluationDateMonth, evaluationDateYear)
                .orHandle(this::handler);
    }

    private Date getBiddingDate() {
        return runFunction(this::convertToDate, biddingDateDay, biddingDateMonth, biddingDateYear)
                .orHandle(this::handler);
    }

    private Date getPaperDate() {
        return runFunction(this::convertToDate, paperDateDay, paperDateMonth, paperDateYear)
                .orHandle(this::handler);
    }

    private Date getAbstractDate() {
        return runFunction(this::convertToDate, abstractDateDay, abstractDateMonth, abstractDateYear)
                .orHandle(this::handler);
    }

    private Date getStartingDate() {
        return runFunction(this::convertToDate, startingDateDay, startingDateMonth, startingDateYear)
                .orHandle(this::handler);
    }

    private Date getEndingDate() {
        return runFunction(this::convertToDate, endingDateDay, endingDateMonth, endingDateYear)
                .orHandle(this::handler);
    }

    private Date convertToDate(TextField day, ChoiceBox<String> month, TextField year) throws ParseException {
        DateFormat format = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);
        return format.parse(month.getSelectionModel().getSelectedItem() + " " + day.getText() + ", " + year.getText());
    }

    private void handler(Throwable exception) {
        logger.warn(exception.getMessage());
    }

    @FXML
    private void onPublishButtonClick() {
        context.getEditionContext().updateEdition(getCurrentEdition());
        runMethod(context::publish).orHandle(exception -> logger.error(exception.getMessage()));
        manager.switchScene(ViewType.CONFERENCES);
    }

    @FXML
    private void onSaveButtonClick() {
    }

    @FXML
    private void onMembersButtonClick() {
        context.getEditionContext().updateEdition(getCurrentEdition());
        manager.switchScene(ViewType.MEMBERS_CONFERENCE, context);
    }

    @FXML
    private void onBasicButtonClick() {
        // TODO DELETE THIS EVENT
    }

    @FXML
    private void onSessionsButtonClick() {
        context.getEditionContext().updateEdition(getCurrentEdition());
        manager.switchScene(ViewType.ADD_SESSION, context);
    }

    @FXML
    private void onSubmissionsButtonClick() {
        manager.switchScene(ViewType.ADD_SUBMISSION, context);
    }

}
