package controller;

import item.pagination.controller.ControllerPaginationConferenceItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import listener.Listener;
import manager.StageManager;
import notification.NotificationUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import pagination.PaginationBuilder;
import service.CollectionService;
import service.SubscriberService;
import transfarable.Conference;
import view.ViewType;

import java.rmi.RemoteException;

import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class ControllerConferencesView
        implements ControllerInterface, SubscriberService {

    @FXML private Button recentButton;
    @FXML private Button popularButton;
    @FXML private TextField searchTextField;
    @FXML private Pagination pagination;

    @Lazy
    @Autowired
    private StageManager manager;

    @Lazy
    @Autowired
    private Listener listener;

    @Lazy
    @Autowired
    private CollectionService service;

    private ObservableList<Conference> conferences;

    /**
     * Effect: Builds the pagination and it's data.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void initialize() {
        Conference[] items = {
                    new Conference("Test", "TEST"),
                    new Conference("Test", "TEST"),
                    new Conference("Test", "TEST"),
                    new Conference("Test", "TEST"),
        };
        conferences = FXCollections.observableArrayList(items);
        pagination = updatePagination(conferences);
        manager.getPrimaryStage().setOnCloseRequest(event ->
                runFunction(listener::removeSubscriber, this).orHandle(System.out::print));
        runFunction(listener::addSubscriber, this).orHandle(System.out::println);
        setUpSearchTextField();
    }

    private void setUpSearchTextField() {
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> searchBasedOn(newValue));
    }

    @SuppressWarnings("unchecked")
    private Pagination updatePagination(ObservableList<Conference> items) {
        return new PaginationBuilder<Conference, ControllerPaginationConferenceItem, GridPane>()
                .setRows(2)
                .setColumns(4)
                .setElements(items)
                .setView(ViewType.CONFERENCE_ITEM)
                .setStageManager(this.manager)
                .setPagination(this.pagination)
                .build(GridPane.class);
    }

    /**
     * Effect: Sorts conferences by popularity
     * based on the number of members that participate at the conference.
     * @implNote status: Unavailable at the moment.
     */
    @FXML private void onPopularButtonClick() {
        popularButton.setOpacity(1);
        recentButton.setOpacity(0.5);
    }

    /**
     * Effect: Sorts conferences by date.
     * @implNote status: Unavailable at the moment.
     */
    @FXML private void onRecentButtonClick() {
        popularButton.setOpacity(0.5);
        recentButton.setOpacity(1);
    }

    private void searchBasedOn(String name) {
        pagination = updatePagination(conferences.filtered(conference ->
                conference.getName().toLowerCase().contains(name.toLowerCase())));
    }

    /**
     * Effect: Search function for conferences.
     * @implNote status: Unavailable at the moment.
     */
    @FXML private void onSearchButtonClick() {
        searchBasedOn(searchTextField.getText());
    }

    @Override
    public void update(NotificationUpdate notification) throws RemoteException {
        System.out.print("Test");
    }
}
