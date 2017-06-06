package controller.update;

import controller.ControllerEdition;
import controller.main.ControllerInterface;
import itemcontroller.ControllerItemInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import list.ListViewBuilder;
import manager.StageManager;
import method.SimpleMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import service.CollectionService;
import service.EditionService;
import service.SessionService;
import service.UserService;
import transfarable.*;
import utils.DateFormatter;
import view.Icon;
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
public class ControllerUpdateSessionView
        implements ControllerInterface, ControllerItemInterface<Session> {

    private static Logger logger;

    @FXML
    private TextField locationTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private Label editionNameLabel;

    @FXML
    private TextField bioTextField;

    @FXML
    private TextField seatsTextField;

    @FXML
    protected TextField startingDateDay;

    @FXML
    protected ChoiceBox<String> startingDateMonth;

    @FXML
    protected TextField startingDateYear;

    @FXML
    protected TextField endingDateDay;

    @FXML
    protected ChoiceBox<String> endingDateMonth;

    @FXML
    protected TextField endingDateYear;


    @Lazy
    @Autowired
    private StageManager manager;

    @Lazy
    @Autowired
    private CollectionService service;

    private Session session;

    private SimpleMethod<RemoteException> remoteHandler;

    private EditionService editionService;
    private SessionService sessionService;

    @Override
    public void setElement(Session session) {
        this.session = session;
        build();
    }

    private ObservableList<User> speakers;

    @FXML
    private TextField speakerTextField;

    @FXML
    private void onAddSpeakerButtonClick( ) {
        Session update = new Session(session.getId(),nameTextField.getText(), getStartingDate(), getEndingDate(),
                locationTextField.getText(),bioTextField.getText(),Integer.parseInt(seatsTextField.getText()));
        UserService userService = runFunction(service::userService).orHandle(remoteHandler);
        User user = runFunction(userService::findUserByUsername, speakerTextField.getText()).orHandle(remoteHandler);
        runFunction(sessionService::addMemberTo, session, user, MemberRoleTransferable.SESSION_SPEAKER).orHandle(remoteHandler);
        this.manager.refresh(update);
    }

    protected void showDates(Session session) {
        new DateFormatter().setDate(session.getStartDate()).setDayField(startingDateDay)
                .setMonthBox(startingDateMonth).setYearField(startingDateYear).show();
        new DateFormatter().setDate(session.getEndDate()).setDayField(endingDateDay)
                .setMonthBox(endingDateMonth).setYearField(endingDateYear).show();
    }
    @FXML
    private ListView<User> speakersListView;

    private void build() {
        bioTextField.setText(session.getBio());
        locationTextField.setText(session.getLocation());
        nameTextField.setText(session.getName());
        seatsTextField.setText(session.getSeats().toString());
        speakers = FXCollections.observableArrayList(runFunction(sessionService::getSpeakers, session).orHandle(remoteHandler));
        speakersListView.setItems(speakers);
        speakersListView = new ListViewBuilder<>(speakersListView)
                .setIcon(Icon.CLOSE)
                .textProvider(User::getName)
                .setAction((service, item) -> {
                    runFunction(((SessionService)service)::removeMemberFrom, session, item).orHandle(remoteHandler);
                    manager.refresh(session);
                    return null;
                }, sessionService)
                .build();
        showDates(session);
    }

    public void initialize() {
        logger = Logger.getLogger(ControllerUpdateSessionView.class);
        remoteHandler = exception -> logger.error(exception.getCause());
        editionService = runFunction(service::editionService).orHandle(remoteHandler);
        sessionService = runFunction(service::sessionService).orHandle(remoteHandler);
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
        Session update = new Session( session.getId(),nameTextField.getText(), getStartingDate(), getEndingDate(),
                locationTextField.getText(),bioTextField.getText(),Integer.parseInt(seatsTextField.getText()));
        runMethod(sessionService::update, session, update).orHandle(remoteHandler);
        manager.refresh(runFunction(sessionService::getElementById, session.getId()).orHandle(remoteHandler));
    }

    @FXML
    private void onDeleteButtonClick() {
        runFunction(sessionService::delete, session).orHandle(remoteHandler);
        manager.switchScene(ViewType.CONFERENCES);
    }

}
