package controller.add;

import controller.main.ControllerInterface;
import itemcontroller.ControllerItemInterface;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import manager.StageManager;
import method.SimpleMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import service.CollectionService;
import service.EditionService;
import transfarable.Conference;
import transfarable.Edition;
import view.ViewType;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
public class ControllerUpdateEditionView
        implements ControllerInterface, ControllerItemInterface<Edition> {

    private static Logger logger;

    @FXML
    private TextField locationTextField;

    @FXML private Label conferenceNameLabel;

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

    @Lazy
    @Autowired
    private CollectionService service;

    private Edition edition;

    private SimpleMethod<RemoteException> remoteHandler;

    private EditionService editionService;

    @Override
    public void setElement(Edition edition) {
        this.edition = edition;
        build();
    }

    private Conference getConference() {
        return runFunction(editionService::getConferenceOf, edition).orHandle(remoteHandler);
    }

    private void build() {
        conferenceNameLabel.setText(getConference().getName());
        locationTextField.setText(edition.getLocation());
        bioTextField.setText(edition.getBio());
        showDates();
    }

    private void showDates() {
        showDate(startingDateDay, startingDateMonth, startingDateYear, edition.getStartDate());
        showDate(endingDateDay, endingDateMonth, endingDateYear, edition.getEndDate());
        showDate(abstractDateDay, abstractDateMonth, abstractDateYear, edition.getAbstractDeadline());
        showDate(paperDateDay, paperDateMonth, paperDateYear, edition.getPaperDeadline());
        showDate(biddingDateDay, biddingDateMonth, biddingDateYear, edition.getBiddingDeadline());
        showDate(evaluationDateDay, evaluationDateMonth, evaluationDateYear, edition.getEvaluationDeadline());
    }

    public void initialize() {
        logger = Logger.getLogger(ControllerUpdateEditionView.class);
        remoteHandler = exception -> logger.error(exception.getCause());
        editionService = runFunction(service::editionService).orHandle(remoteHandler);
    }

    @FXML
    private void onBackButtonClick() {
        manager.switchScene(ViewType.CONFERENCE_UPDATE, getConference());
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
    private void onSaveButtonClick() {
        Edition update = new Edition(edition.getId(), getStartingDate(), getEndingDate(), locationTextField.getText(),
                bioTextField.getText(), getAbstractDate(), getPaperDate(), getEvaluationDate(), getBiddingDate());
        runMethod(editionService::update, edition, update).orHandle(remoteHandler);
        manager.refresh();
    }

    @FXML
    private void onDeleteButtonClick() {
        runFunction(editionService::delete, edition);
        manager.switchScene(ViewType.CONFERENCES);
    }

    @FXML
    private void onMembersButtonClick() {
        // TODO
    }

    @FXML
    private void onBasicButtonClick() {
        // TODO Delete
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
