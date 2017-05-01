package controller;

import data.User;
import itemcontroller.ControllerUserItem;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import pagination.PaginationBuilder;
import view.ViewType;

/**
 * Name:        ControllerUsersView
 * Effect:      Lists all the available users.
 * Date:        05/04/2017
 * Tested:      False
 *
 * @author      Alexandru Stoica
 * @version     1.0
 */

@Component
public class ControllerUsersView implements ControllerInterface {

    @SuppressWarnings("all")
    private StageManager manager;

    @FXML private TextField searchTextField;
    @FXML private Pagination pagination;

    @Autowired @Lazy
    public ControllerUsersView(StageManager manager) {
        this.manager = manager;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void initialize() {
        // This part is for testing the pagination's builder with mocking data.
        User[] users = {
                new User("test_user", "test", "test@test.com", "Test User"),
                new User("test_user", "test", "test@test.com", "Test User"),
                new User("test_user", "test", "test@test.com", "Test User"),
                new User("test_user", "test", "test@test.com", "Test User"),
                new User("test_user", "test", "test@test.com", "Test User"),
                new User("test_user", "test", "test@test.com", "Test User"),
                new User("test_user", "test", "test@test.com", "Test User"),
                new User("test_user", "test", "test@test.com", "Test User")
        };
        pagination = new PaginationBuilder<User, ControllerUserItem, GridPane>()
                .setRows(2)
                .setColumns(4)
                .setElements(users)
                .setView(ViewType.USER_ITEM)
                .setStageManager(this.manager)
                .setPagination(this.pagination)
                .build(GridPane.class);
        pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
    }

    /**
     * Effect: Search function for users.
     * @implNote status: Unavailable at the moment.
     */
    @FXML private void onSearchButtonClick() {
        String searchTerm = searchTextField.getText();
        System.out.println(searchTerm);
    }

}