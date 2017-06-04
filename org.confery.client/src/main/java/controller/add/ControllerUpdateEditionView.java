package controller.add;

import controller.ControllerEdition;
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
public class ControllerUpdateEditionView extends ControllerEdition
        implements ControllerInterface, ControllerItemInterface<Edition> {

    private static Logger logger;

    @FXML
    private TextField locationTextField;

    @FXML private Label conferenceNameLabel;

    @FXML
    private TextField bioTextField;

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
        showDates(edition);
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
        return runFunction(this::convertToDate, evaluationDateDay, evaluationDateMonth, evaluationDateDay)
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
    private void onSaveButtonClick() {
        Edition update = new Edition(edition.getId(), getStartingDate(), getEndingDate(), locationTextField.getText(),
                bioTextField.getText(), getAbstractDate(), getPaperDate(), getEvaluationDate(), getBiddingDate());
        runMethod(editionService::update, edition, update).orHandle(remoteHandler);
        manager.refresh(runFunction(editionService::getElementById, edition.getId()).orHandle(remoteHandler));
    }

    @FXML
    private void onDeleteButtonClick() {
        runFunction(editionService::delete, edition);
        manager.switchScene(ViewType.CONFERENCES);
    }

    @FXML
    private void onMembersButtonClick() {
        manager.switchScene(ViewType.UPDATE_EDITION_MEMBERS, edition);
    }

    @FXML
    private void onBasicButtonClick() {
        // TODO DELETE THIS EVENT
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
