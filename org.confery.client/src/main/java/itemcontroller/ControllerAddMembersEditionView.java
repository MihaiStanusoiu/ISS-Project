package itemcontroller;

import controller.main.ControllerInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import list.ListViewBuilder;
import manager.StageManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import transfarable.User;
import utils.ConferenceContext;
import view.Icon;
import view.ViewType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class ControllerAddMembersEditionView
        implements ControllerInterface, ControllerItemInterface<ConferenceContext> {

    private static Logger logger;

    @FXML
    private ListView<User> chairListView;

    @FXML
    private ListView<User> coChairsListView;

    @FXML
    private ListView<User> pcMembersListView;

    @FXML
    private TextField coChairTextField;

    @FXML
    private TextField pcMemberTextField;

    @FXML
    private Label conferenceNameLabel;

    private ObservableList<User> chairs;
    private ObservableList<User> coChairs;
    private ObservableList<User> pcMembers;

    @Lazy
    @Autowired
    @SuppressWarnings("unused")
    private StageManager manager;

    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private ConferenceContext context;

    @Override
    public void setElement(ConferenceContext element) {
        this.context = element;
        build();
    }

    private void build() {
        conferenceNameLabel.setText(context.getConference().getName());

        // TODO Add Active User
        chairs = FXCollections.observableArrayList();

        coChairs = context.getEditionContext().getCoChairs();
        pcMembers = context.getEditionContext().getPcMembers();

        setUpListViews();
    }

    private void setUpListViews() {
        chairListView = new ListViewBuilder<>(chairListView)
                .setIcon(Icon.CLOSE)
                .visibleText(User::getName)
                .setAction((list, item) -> Boolean.TRUE, new ArrayList<>())
                .build();
        coChairsListView = new ListViewBuilder<>(coChairsListView)
                .setIcon(Icon.CLOSE)
                .visibleText(User::getName)
                .setAction(List::remove, context.getEditionContext().getCoChairs())
                .build();
        pcMembersListView = new ListViewBuilder<>(pcMembersListView)
                .setIcon(Icon.CLOSE)
                .visibleText(User::getName)
                .setAction(List::remove, context.getEditionContext().getPcMembers())
                .build();
        setUpListViewsItems();
    }

    private void setUpListViewsItems() {
        chairListView.setItems(chairs);
        coChairsListView.setItems(coChairs);
        pcMembersListView.setItems(pcMembers);
    }

    public void initialize() {
        logger = Logger.getLogger(ControllerAddMembersEditionView.class);
    }

    @FXML
    private void onAddCoChairButtonClick() {
        String username = coChairTextField.getText();
        // TODO Get User Form Service By Username
        User user = new User(username, "password", "email", username);
        context.getEditionContext().addCoChair(user);
    }

    @FXML
    private void onAddPCMemberButtonClick() {
        String username = pcMemberTextField.getText();
        // TODO Get User Form Service By Username
        User user = new User(username, "password", "email", username);
        context.getEditionContext().addPcMember(user);
    }

    @FXML
    private void onPublishButtonClick() {
        // TODO
    }

    @FXML
    private void onSaveButtonClick() {
        // TODO
    }

    @FXML
    private void onBasicButtonClick() {
        manager.switchScene(ViewType.ADD_EDITION, context);
    }

    @FXML
    private void onBackButtonClick() {
        manager.switchScene(ViewType.ADD_CONFERENCE, context);
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
