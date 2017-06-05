package controller.read;

import controller.main.ControllerInterface;
import controller.pagination.ControllerPaginationSubmissionItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import manager.StageManager;
import method.SimpleMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import pagination.PaginationBuilder;
import service.AuthenticationService;
import service.CollectionService;
import service.SubmissionService;
import transfarable.Submission;
import transfarable.User;
import view.ViewType;

import java.rmi.RemoteException;
import java.util.List;

import static utils.Conditional.basedOn;
import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class ControllerMySubmissionsView implements ControllerInterface {

    @FXML
    private TextField searchTextField;

    @FXML
    private Pagination pagination;

    @Lazy
    @Autowired
    @SuppressWarnings("unused")
    private StageManager manager;

    @Lazy
    @Autowired
    private CollectionService collectionService;

    private SubmissionService submissionService;
    private ObservableList<Submission> submissions;
    private static Logger logger;
    private SimpleMethod<RemoteException> handler;

    @Override
    public void initialize() {
        logger = Logger.getLogger(ControllerMySubmissionsView.class);
        handler = exception -> logger.error(exception);
        submissionService = runFunction(collectionService::submissionService).orHandle(handler);
        AuthenticationService authenticationService = runFunction(collectionService::authenticationService).orHandle(handler);
        User active = runFunction(authenticationService::getActiveUser).orHandle(handler);
        basedOn(!active.getId().equals(0)).runTrue(this::build, active);
    }

    private void build(User active) {
        List<Submission> items = runFunction(submissionService::getAll).orHandle(handler);
        submissions = FXCollections.observableArrayList(items);
        pagination = updatePagination(submissions);
        setUpSearchTextField();
    }

    private void setUpSearchTextField() {
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> search(newValue));
    }

    private Pagination updatePagination(ObservableList<Submission> items) {
        return new PaginationBuilder<Submission, ControllerPaginationSubmissionItem, GridPane>()
                .setElements(items)
                .setView(ViewType.SUBMISSION_ITEM)
                .setStageManager(manager)
                .setPagination(pagination)
                .build(GridPane.class);
    }

    private void search(String name) {
        pagination = updatePagination(submissions.filtered(submission ->
                submission.getName().toLowerCase().contains(name.toLowerCase())));
    }

    @FXML
    private void onSearchButtonClick() {
        search(searchTextField.getText());
    }

}
