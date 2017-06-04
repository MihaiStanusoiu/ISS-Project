package controller.item;

import controller.main.ControllerInterface;
import controller.pagination.ControllerPaginationSessionItem;
import itemcontroller.ControllerItemInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import list.ListViewBuilder;
import manager.StageManager;
import method.SimpleMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import pagination.PaginationBuilder;
import service.CollectionService;
import service.EditionService;
import transfarable.Edition;
import transfarable.Session;
import transfarable.Submission;
import transfarable.User;
import view.Icon;
import view.ViewType;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.List;

import static utils.Try.runFunction;

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

    @FXML
    private Pagination pagination;

    private Edition edition;

    @Lazy
    @Autowired
    private StageManager manager;

    @Lazy
    @Autowired
    private CollectionService collectionService;

    private EditionService editionService;
    private static Logger logger;
    private SimpleMethod<RemoteException> handler;

    @Override
    public void initialize() {
        logger = Logger.getLogger(ControllerEditionView.class);
        handler = exception -> logger.error(exception.getCause());
        editionService = runFunction(collectionService::editionService).orHandle(handler);
    }

    @FXML
    private void onSubmitPaperButtonClick() {
        manager.switchScene(ViewType.ADD_SUBMISSION_TO_EDITION, new Submission());
    }

    @Override
    public void setElement(Edition element) {
        this.edition = element;
        build();
        buildLists();
        setUpSessionsPagination();
    }

    private void setUpSessionsPagination() {
        List<Session> items = runFunction(editionService::getAllSessionsOf, edition).orHandle(handler);
        ObservableList<Session> sessions = FXCollections.observableArrayList(items);
        pagination = updatePagination(sessions);
    }

    @SuppressWarnings("unchecked")
    private Pagination updatePagination(ObservableList<Session> items) {
        return new PaginationBuilder<Session, ControllerPaginationSessionItem, GridPane>()
                .setRows(1).setColumns(2)
                .setElements(items)
                .setView(ViewType.SESSION_ITEM)
                .setStageManager(manager)
                .setPagination(pagination)
                .build(GridPane.class);
    }

    private ObservableList<User> coChairList;
    private ObservableList<User> pcMemberList;
    private ObservableList<User> chairList;

    private void buildLists() {
        getMembersData();
        setUpListViews();
        setItemsInListView();
    }

    private void setItemsInListView() {
        chairs.setItems(chairList);
        pcMembers.setItems(pcMemberList);
        coChairs.setItems(coChairList);
    }

    private void getMembersData() {
        User chair = runFunction(editionService::getChair, edition).orHandle(handler);
        chairList = FXCollections.observableList(Collections.singletonList(chair));
        coChairList = FXCollections.observableList(runFunction(editionService::getCoChairsOf, edition).orHandle(handler));
        pcMemberList = FXCollections.observableList(runFunction(editionService::getPcMembersOf, edition).orHandle(handler));
    }

    private void setUpListViews() {
        coChairs = new ListViewBuilder<>(coChairs)
                .textProvider(User::getName)
                .setIcon(Icon.CLOSE)
                .build();
        chairs = new ListViewBuilder<>(chairs)
                .textProvider(User::getName)
                .setIcon(Icon.CLOSE)
                .build();
        pcMembers = new ListViewBuilder<>(pcMembers)
                .textProvider(User::getName)
                .build();
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
