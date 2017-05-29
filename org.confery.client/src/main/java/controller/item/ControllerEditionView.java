package controller.item;

import cells.UserListCell;
import controller.main.ControllerInterface;
import itemcontroller.ControllerItemInterface;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import transfarable.Edition;
import transfarable.User;

import static java.util.Arrays.asList;


/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class ControllerEditionView implements ControllerInterface, ControllerItemInterface<Edition> {

    @FXML
    private Label locationLabel;

    @FXML
    private Text bioText;

    @FXML
    private Text locationText;

    @FXML
    private Text startingDateText;

    @FXML
    private Text endingDateText;

    @FXML
    private Label abstractDeadlineLabel;

    @FXML
    private Label evaluationDeadlineLabel;

    @FXML
    private Label biddingDeadlineLabel;

    @FXML
    private Label papersDeadlineLabel;

    @FXML
    private ListView<User> chairs;

    @FXML
    private ListView<User> pcMembers;

    @FXML
    private ListView<User> coChairs;

    private Edition edition;

    @Lazy
    @Autowired
    private StageManager manager;

    @Override
    public void initialize() {
        User[] chairsList = {new User("Test", "password", "test@test", "Test")};
        User[] coChairsList = {
                new User("Test", "password", "test@test", "Test"),
                new User("Test", "password", "test@test", "Test"),
                new User("Test", "password", "test@test", "Test"),
                new User("Test", "password", "test@test", "Test"),
        };
        User[] pcMembersList = {
                new User("Test", "password", "test@test", "Test"),
                new User("Test", "password", "test@test", "Test"),
                new User("Test", "password", "test@test", "Test"),
                new User("Test", "password", "test@test", "Test"),
        };
        chairs.setItems(FXCollections.observableArrayList(chairsList));
        coChairs.setItems(FXCollections.observableArrayList(coChairsList));
        pcMembers.setItems(FXCollections.observableArrayList(pcMembersList));
    }

    @FXML
    private void onSubmitPaperButtonClick() {
        // TODO Create Submit Paper FXML
    }

    @Override
    public void setElement(Edition element) {
        this.edition = element;
        setUpLists();
        build();
    }

    private void setUpLists() {
        asList(chairs, pcMembers, coChairs).forEach(this::setUpListViewCell);
    }

    private void setUpListViewCell(ListView<User> listView) {
        listView.setCellFactory(param -> new UserListCell(manager));
    }

    private void build() {
        locationLabel.setText(edition.getLocation());
        bioText.setText(edition.getBio());
        locationText.setText(edition.getLocation());
        startingDateText.setText(edition.getStartDate().toString());
        endingDateText.setText(edition.getEndDate().toString());
        abstractDeadlineLabel.setText(edition.getAbstractDeadline().toString());
        papersDeadlineLabel.setText(edition.getPaperDeadline().toString());
        evaluationDeadlineLabel.setText(edition.getEvaluationDeadline().toString());
        biddingDeadlineLabel.setText(edition.getBiddingDeadline().toString());
    }
}
