package controller.update;

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
import service.EditionService;
import service.UserService;
import transfarable.Conference;
import transfarable.Edition;
import transfarable.MemberRoleTransferable;
import transfarable.User;
import view.Icon;
import view.ViewType;

import java.rmi.RemoteException;

import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class ControllerUpdateMembersEditionView
        implements ControllerInterface, ControllerItemInterface<Edition> {

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

    private Edition edition;

    private EditionService editionService;

    @Override
    public void setElement(Edition element) {
        this.edition = element;
        build();
    }

    public void initialize() {
        logger = Logger.getLogger(ControllerUpdateMembersEditionView.class);
        handler = exception -> logger.error(exception.getCause());
        userService = runFunction(service::userService).orHandle(handler);
        editionService = runFunction(service::editionService).orHandle(handler);
    }

    private void build() {
        conferenceNameLabel.setText(runFunction(editionService::getConferenceOf, edition).orHandle(handler).getName());
        getListsElements();
        setUpListViews();
        setUpListViewsItems();
    }

    private void getListsElements() {
        chairs = FXCollections.observableArrayList(runFunction(editionService::getChair, edition).orHandle(handler));
        coChairs = FXCollections.observableArrayList(runFunction(editionService::getCoChairsOf, edition).orHandle(handler));
        pcMembers = FXCollections.observableArrayList(runFunction(editionService::getPcMembersOf, edition).orHandle(handler));
    }

    private void setUpListViews() {
        chairListView = new ListViewBuilder<>(chairListView)
                .setIcon(Icon.CLOSE)
                .textProvider(User::getName)
                .build();
        coChairsListView = new ListViewBuilder<>(coChairsListView)
                .setIcon(Icon.CLOSE)
                .textProvider(User::getName)
                .setAction((service, item) -> {
                    runFunction(((EditionService)service)::deleteMemberOfEdition, edition, item).orHandle(handler);
                    manager.refresh(edition);
                    return null;
                }, editionService)
                .build();
        pcMembersListView = new ListViewBuilder<>(pcMembersListView)
                .setIcon(Icon.CLOSE)
                .textProvider(User::getName)
                .setAction((service, item) -> {
                    logger.info(item.getUsername());
                    runFunction(((EditionService)service)::deleteMemberOfEdition, edition, item).orHandle(handler);
                    manager.refresh(edition);
                    return null;
                }, editionService)
                .build();
    }

    private void setUpListViewsItems() {
        chairListView.setItems(chairs);
        coChairsListView.setItems(coChairs);
        pcMembersListView.setItems(pcMembers);
    }

    @FXML
    private void onAddCoChairButtonClick() {
        addMemberToEdition(coChairTextField, MemberRoleTransferable.EDITION_CO_CHAIR);
    }

    private void addMemberToEdition(TextField username, MemberRoleTransferable role) {
        User user = runFunction(userService::findUserByUsername, username.getText()).orHandle(handler);
        edition = runFunction(editionService::addMemberToEdition, edition, user, role).orHandle(handler);
        manager.refresh(edition);
    }

    @FXML
    private void onAddPCMemberButtonClick() {
        addMemberToEdition(pcMemberTextField, MemberRoleTransferable.EDITION_PC_MEMBER);
    }

    @FXML
    private void onSaveButtonClick() {
        manager.refresh(edition);
    }

    @FXML
    private void onBasicButtonClick() {
        manager.switchScene(ViewType.EDITION_UPDATE, edition);
    }

    @FXML
    private void onBackButtonClick() {
        manager.switchScene(ViewType.CONFERENCE_UPDATE, getConference());
    }

    private Conference getConference() {
        return runFunction(editionService::getConferenceOf, edition).orHandle(handler);
    }

    @FXML
    private void onSessionsButtonClick() {
        manager.switchScene(ViewType.SESSIONS_UPDATE, edition);
    }

    @FXML
    private void onSubmissionsButtonClick() {
        // TODO
    }

}
