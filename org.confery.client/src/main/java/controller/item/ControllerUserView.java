package controller.item;

import controller.main.ControllerInterface;
import controller.pagination.ControllerPaginationConferenceItem;
import controller.pagination.ControllerPaginationSubmissionItem;
import itemcontroller.ControllerItemInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import manager.StageManager;
import method.SimpleMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import pagination.PaginationBuilder;
import service.CollectionService;
import service.ConferenceService;
import service.UserService;
import transfarable.Conference;
import transfarable.Submission;
import transfarable.User;
import view.ViewType;

import java.rmi.RemoteException;
import java.util.List;

import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class ControllerUserView
        implements ControllerInterface, ControllerItemInterface<User> {

    @FXML
    private Label nameLabel;

    @FXML
    private Text bioText;

    @FXML
    private Text locationText;

    @FXML
    private Text websiteText;

    @FXML
    private Text emailText;

    @FXML
    private Pagination paginationConferences;

    @FXML
    private Pagination paginationSubmissions;

    @SuppressWarnings("FieldCanBeLocal")
    private ObservableList<Conference> conferences;

    @SuppressWarnings("FieldCanBeLocal")
    private ObservableList<Submission> submissions;

    @SuppressWarnings("FieldCanBeLocal")
    private User element;

    @Lazy
    @Autowired
    private StageManager manager;

    @Lazy
    @Autowired
    private CollectionService collectionService;

    private UserService userService;
    private ConferenceService conferenceService;
    private static Logger logger;
    private SimpleMethod<RemoteException> handler;

    @Override
    public void initialize() {
        logger = Logger.getLogger(ControllerUserView.class);
        handler = exception -> logger.error(exception.getCause());
        userService = runFunction(collectionService::userService).orHandle(handler);
        conferenceService = runFunction(collectionService::conferenceService).orHandle(handler);
    }

    @Override
    public void setElement(User element) {
        this.element = element;
        build(element);
        buildLists();
    }

    private void build(User element) {
        nameLabel.setText(element.getName());
        bioText.setText(element.getBio());
        locationText.setText(element.getLocation());
        websiteText.setText(element.getWebsite());
        emailText.setText(element.getEmail());
    }

    private void buildLists() {
        List<Submission> submissionItems = runFunction(userService::getSubmissionsByAuthorship, element).orHandle(handler);
        List<Conference> conferenceItems = runFunction(conferenceService::getConferencesOf, element).orHandle(handler);
        conferences = FXCollections.observableArrayList(conferenceItems);
        submissions = FXCollections.observableArrayList(submissionItems);
        paginationConferences = updatePaginationConference(conferences);
        paginationSubmissions = updatePaginationSubmission(submissions);
    }

    @SuppressWarnings("unchecked")
    private Pagination updatePaginationSubmission(ObservableList<Submission> items) {
        return new PaginationBuilder<Submission, ControllerPaginationSubmissionItem, GridPane>()
                .setRows(1).setColumns(4)
                .setElements(items)
                .setView(ViewType.SUBMISSION_ITEM)
                .setStageManager(manager)
                .setPagination(paginationSubmissions)
                .build(GridPane.class);
    }

    @SuppressWarnings("unchecked")
    private Pagination updatePaginationConference(ObservableList<Conference> items) {
        return new PaginationBuilder<Conference, ControllerPaginationConferenceItem, GridPane>()
                .setRows(1).setColumns(4)
                .setElements(items)
                .setView(ViewType.CONFERENCE_ITEM)
                .setStageManager(manager)
                .setPagination(paginationConferences)
                .build(GridPane.class);
    }

}
