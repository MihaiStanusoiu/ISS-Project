package controller.read;

import controller.main.ControllerInterface;
import controller.pagination.ControllerPaginationUserItem;
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
import transfarable.User;
import view.ViewType;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class ControllerUsersView implements ControllerInterface {

    @FXML
    private TextField searchTextField;

    @FXML
    public Pagination pagination;

    @Lazy
    @Autowired
    private StageManager manager;

    private ObservableList<User> users;

    @Override
    public void initialize() {
        List<User> items = asList(
                new User("Test", "password", "email@email", "Test Name", "website.com", "superb", "New York"),
                new User("Test", "password", "email@email", "Test Name", "website.com", "superb", "New York"),
                new User("Test", "password", "email@email", "Test Name", "website.com", "superb", "New York"),
                new User("Test", "password", "email@email", "Test Name", "website.com", "superb", "New York"),
                new User("Test", "password", "email@email", "Test Name", "website.com", "superb", "New York"));
        users = FXCollections.observableArrayList(items);
        pagination = updatePagination(users);
        setUpSearchField();
    }

    private void setUpSearchField() {
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> search(newValue));
    }

    private void search(String name) {
        pagination = updatePagination(users.filtered(conference ->
                conference.getName().toLowerCase().contains(name.toLowerCase())));
    }

    private Pagination updatePagination(ObservableList<User> list) {
        return new PaginationBuilder<User, ControllerPaginationUserItem, GridPane>()
                .setElements(list)
                .setView(ViewType.USER_ITEM)
                .setStageManager(manager)
                .setPagination(pagination)
                .build(GridPane.class);
    }

    @FXML
    private void onSearchButtonClick() {
        search(searchTextField.getText());
    }

}