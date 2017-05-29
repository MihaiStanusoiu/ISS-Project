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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import pagination.PaginationBuilder;
import service.CollectionService;
import transfarable.Conference;
import view.ViewType;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class ControllerConferencesView implements ControllerInterface {

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

    @Override
    public void initialize() {
        List<Conference> items = asList(
                new Conference("Conference Name", "TEST"),
                new Conference("Conference Name", "TEST"),
                new Conference("Conference Name", "TEST"),
                new Conference("Conference Name", "TEST"));
        conferences = FXCollections.observableArrayList(items);
        pagination = updatePagination(conferences);
        setUpSearchTextField();
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
        // TODO: Sort conferences by number of members
    }

    @FXML
    private void onRecentButtonClick() {
        switchOpacity(popularButton, recentButton);
        // TODO: Sort conferences by the starting date of the latest edition.
    }

    @FXML
    private void onSearchButtonClick() {
        search(searchTextField.getText());
    }
}
