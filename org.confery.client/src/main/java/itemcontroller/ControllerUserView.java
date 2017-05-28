package itemcontroller;

import controller.ControllerInterface;
import item.pagination.controller.ControllerPaginationConferenceItem;
import item.pagination.controller.ControllerPaginationSubmissionItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import pagination.PaginationBuilder;
import transfarable.Conference;
import transfarable.Submission;
import transfarable.User;
import view.ViewType;

/**
 * @author      Alexandru Stoica
 * @version     1.0
 */

@Lazy
@Component
public class ControllerUserView
        implements ControllerInterface, ControllerItemInterface<User> {

    @FXML private Label nameLabel;
    @FXML private Text bioText;
    @FXML private Text locationText;
    @FXML private Text websiteText;
    @FXML private Text emailText;

    @FXML private Pagination paginationConferences;
    @FXML private Pagination paginationSubmissions;

    private ObservableList<Conference> conferences;
    private ObservableList<Submission> submissions;

    private User element;

    @Lazy
    @Autowired
    private StageManager manager;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize() {
        Conference[] conferenceItems = {
                new Conference("Conference Name", "TEST"),
                new Conference("Conference Name", "TEST"),
                new Conference("Conference Name", "TEST"),
                new Conference("Conference Name", "TEST"),
        };
        Submission[] submissionItems = {
                new Submission(1, "TestName", "Reviewed", "url@uer", "paper", Boolean.FALSE),
                new Submission(1, "TestName", "Not Reviewed", "url@uer", "paper", Boolean.FALSE),
                new Submission(1, "TestName", "Reviewed", "url@uer", "paper", Boolean.FALSE),
                new Submission(1, "TestName", "Reviewed", "url@uer", "paper", Boolean.FALSE)
        };
        conferences = FXCollections.observableArrayList(conferenceItems);
        paginationConferences = updatePaginationConference(conferences);
        submissions = FXCollections.observableArrayList(submissionItems);
        paginationSubmissions = updatePaginationSubmission(submissions);
    }

    @SuppressWarnings("unchecked")
    private Pagination updatePaginationSubmission(ObservableList<Submission> items) {
        return new PaginationBuilder<Submission, ControllerPaginationSubmissionItem, GridPane>()
                .setRows(1)
                .setColumns(4)
                .setElements(items)
                .setView(ViewType.SUBMISSION_ITEM)
                .setStageManager(manager)
                .setPagination(paginationSubmissions)
                .build(GridPane.class);
    }
    @SuppressWarnings("unchecked")
    private Pagination updatePaginationConference(ObservableList<Conference> items) {
        return new PaginationBuilder<Conference, ControllerPaginationConferenceItem, GridPane>()
                .setRows(1)
                .setColumns(4)
                .setElements(items)
                .setView(ViewType.CONFERENCE_ITEM)
                .setStageManager(manager)
                .setPagination(paginationConferences)
                .build(GridPane.class);
    }

    /**
     * {@inheritDoc}
     * @param element The view's element.
     */
    @Override
    public void setElement(User element) {
        this.element = element;
        nameLabel.setText(element.getName());
        bioText.setText(element.getBio());
        locationText.setText(element.getLocation());
        websiteText.setText(element.getWebsite());
        emailText.setText(element.getEmail());
    }

}
