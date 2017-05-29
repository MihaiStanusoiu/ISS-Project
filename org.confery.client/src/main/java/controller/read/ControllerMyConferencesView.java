package controller.read;

import controller.main.ControllerInterface;
import controller.pagination.ControllerPaginationConferenceItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import pagination.PaginationBuilder;
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
public class ControllerMyConferencesView implements ControllerInterface {

    @FXML
    private TextField searchTextField;

    @FXML
    private Pagination pagination;

    @Lazy
    @Autowired
    @SuppressWarnings("unused")
    private StageManager manager;

    private ObservableList<Conference> conferences;

    @Override
    public void initialize() {
        // TODO: Filter conferences based on ownership
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

    private void search(String name) {
        pagination = updatePagination(conferences.filtered(conference ->
                conference.getName().toLowerCase().contains(name.toLowerCase())));
    }

    @FXML
    private void onSearchButtonClick() {
        search(searchTextField.getText());
    }

}
