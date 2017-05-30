package controller.add;

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
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
@SuppressWarnings("EmptyMethod")
public class ControllerAddEditionView
        implements ControllerInterface, ControllerItemInterface<ConferenceContext> {

    private static Logger logger;

    @FXML
    private TextField locationTextField;

    @FXML
    private Label conferenceNameLabel;

    @FXML
    private TextField bioTextField;

    @FXML
    private TextField startingDateDay;

    @FXML
    private ChoiceBox<String> startingDateMonth;

    @FXML
    private TextField startingDateYear;

    @FXML
    private TextField endingDateDay;

    @FXML
    private ChoiceBox<String> endingDateMonth;

    @FXML
    private TextField endingDateYear;

    @FXML
    private TextField abstractDateDay;

    @FXML
    private ChoiceBox<String> abstractDateMonth;

    @FXML
    private TextField abstractDateYear;

    @FXML
    private TextField paperDateDay;

    @FXML
    private ChoiceBox<String> paperDateMonth;

    @FXML
    private TextField paperDateYear;

    @FXML
    private TextField biddingDateDay;

    @FXML
    private ChoiceBox<String> biddingDateMonth;

    @FXML
    private TextField biddingDateYear;

    @FXML
    private TextField evaluationDateDay;

    @FXML
    private ChoiceBox<String> evaluationDateMonth;

    @FXML
    private TextField evaluationDateYear;

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
        showDates();
    }

    private void showDates() {
        showDate(startingDateDay, startingDateMonth, startingDateYear, context.getEdition().getStartDate());
        showDate(endingDateDay, endingDateMonth, endingDateYear, context.getEdition().getEndDate());
        showDate(abstractDateDay, abstractDateMonth, abstractDateYear, context.getEdition().getAbstractDeadline());
        showDate(paperDateDay, paperDateMonth, paperDateYear, context.getEdition().getPaperDeadline());
        showDate(biddingDateDay, biddingDateMonth, biddingDateYear, context.getEdition().getBiddingDeadline());
        showDate(evaluationDateDay, evaluationDateMonth, evaluationDateYear, context.getEdition().getEvaluationDeadline());
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
        return runFunction(this::convertToDate, evaluationDateDay, evaluationDateMonth, endingDateYear)
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

    private void showDate(TextField day, ChoiceBox<String> month, TextField year, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        day.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        year.setText(String.valueOf(calendar.get(Calendar.YEAR)));
        month.getSelectionModel().select(calendar.get(Calendar.MONTH));
    }

    private void handler(Throwable exception) {
        logger.warn(exception.getMessage());
    }

    @FXML
    private void onPublishButtonClick() {
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
    }

    @FXML
    private void onSessionsButtonClick() {
    }

    @FXML
    private void onSubmissionsButtonClick() {
    }

}
