package controller.add;

import controller.main.ControllerInterface;
import itemcontroller.ControllerItemInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
import service.UserService;
import transfarable.User;
import utils.ConferenceContext;
import view.Icon;
import view.ViewType;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import static utils.Try.runFunction;
import static utils.Try.runMethod;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class ControllerAddMembersEditionView
        implements ControllerInterface, ControllerItemInterface<ConferenceContext> {

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
    private CollectionService service;

    private SimpleMethod<RemoteException> handler;
    private UserService userService;

    private static Logger logger;

    @Lazy
    @Autowired
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
        getListsElements();
        setUpListViews();
        setUpListViewsItems();
    }

    private void getListsElements() {
        chairs = FXCollections.observableArrayList(context.getChair());
        coChairs = context.getEditionContext().getCoChairs();
        pcMembers = context.getEditionContext().getPcMembers();
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
    }

    private void setUpListViewsItems() {
        chairListView.setItems(chairs);
        coChairsListView.setItems(coChairs);
        pcMembersListView.setItems(pcMembers);
    }

    public void initialize() {
        logger = Logger.getLogger(ControllerAddEditionView.class);
        handler = exception -> logger.error(exception.getCause());
        userService = runFunction(service::userService).orHandle(handler);
    }

    @FXML
    private void onAddCoChairButtonClick() {
        String username = coChairTextField.getText();
        User user = runFunction(userService::findUserByUsername, username).orHandle(handler);
        context.getEditionContext().addCoChair(user);
    }

    @FXML
    private void onAddPCMemberButtonClick() {
        String username = pcMemberTextField.getText();
        User user = runFunction(userService::findUserByUsername, username).orHandle(handler);
        context.getEditionContext().addPcMember(user);
    }

    @FXML
    private void onPublishButtonClick() {
        runMethod(context::publish).orHandle(exception -> logger.error(exception.getMessage()));
        manager.switchScene(ViewType.CONFERENCES);
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
        manager.switchScene(ViewType.ADD_SESSION, context);
    }

    @FXML
    private void onSubmissionsButtonClick() {
        manager.switchScene(ViewType.ADD_SUBMISSION, context);
    }

}
