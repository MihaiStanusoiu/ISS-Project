package controller.read;

import controller.main.ControllerInterface;
import controller.pagination.ControllerPaginationConferenceItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
import service.CollectionService;
import service.ConferenceService;
import transfarable.Conference;
import transfarable.Edition;
import view.ViewType;

import java.rmi.RemoteException;
import java.util.Comparator;
import java.util.List;

import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class ControllerConferencesView implements ControllerInterface {

    private static Logger logger;

    @FXML
    private Button recentButton;

    @FXML
    private Button popularButton;

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
    @SuppressWarnings("unused")
    private CollectionService service;

    private ObservableList<Conference> conferences;
    private SimpleMethod<RemoteException> handler;
    private ConferenceService conferenceService;

    @Override
    public void initialize() {
        logger = Logger.getLogger(StageManager.class);
        handler = exception -> logger.error(exception.getCause());
        conferenceService = runFunction(service::conferenceService).orHandle(handler);
        List<Conference> items = runFunction(conferenceService::getAll).orHandle(handler);
        conferences = FXCollections.observableArrayList(items);
        pagination = updatePagination(conferences);
        setUpSearchTextField();
        sortConferenceByPopular();
    }

    private void setUpSearchTextField() {
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> search(newValue));
    }

    private Pagination updatePagination(ObservableList<Conference> items) {
        return new PaginationBuilder<Conference, ControllerPaginationConferenceItem, GridPane>()
                .setElements(items)
                .setView(ViewType.CONFERENCE_ITEM)
                .setStageManager(manager)
                .setPagination(pagination)
                .build(GridPane.class);
    }

    private void switchOpacity(Node left, Node right) {
        Double aux = right.getOpacity();
        right.setOpacity(left.getOpacity());
        left.setOpacity(aux);
    }

    private void search(String name) {
        pagination = updatePagination(conferences.filtered(conference ->
                conference.getName().toLowerCase().contains(name.toLowerCase())));
    }

    @FXML
    private void onPopularButtonClick() {
        switchOpacity(popularButton, recentButton);
        sortConferenceByPopular();
    }

    private void sortConferenceByPopular() {
        conferences.sort(Comparator.comparing(this::getTheNumberOfEditionsFor));
        updatePagination(conferences);
    }

    private Integer getTheNumberOfEditionsFor(Conference conference) {
        return runFunction(conferenceService::getEditionsOf, conference).orHandle(handler).size();
    }

    private Edition getLatestEdition(Conference conference) {
        return runFunction(conferenceService::getLatestEdition, conference).orHandle(handler);
    }

    @FXML
    private void onRecentButtonClick() {
        switchOpacity(popularButton, recentButton);
        sortConferenceByRecent();
    }

    private void sortConferenceByRecent() {
        conferences.sort(Comparator.comparing(element -> getLatestEdition(element).getStartDate()));
        updatePagination(conferences);
    }

    @FXML
    private void onSearchButtonClick() {
        search(searchTextField.getText());
    }
}
