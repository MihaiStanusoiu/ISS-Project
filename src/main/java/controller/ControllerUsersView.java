package controller;

import domain.User;
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
import pagination.PaginationBuilderInterface;
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
    public void initialize() {
        PaginationBuilderInterface<User, ControllerUserItem, GridPane> builder =
                new PaginationBuilder<>(2,4);
        // This part is for testing the pagination's builder with mocking data.
        User[] users = {
            new User(1, "UserTest", "password", "user@username.com",
                "Test User", "none", "none", "New York"),
            new User(2, "UserTest", "password", "user@username.com",
                    "Test User", "none", "none", "New York"),
            new User(3, "UserTest", "password", "user@username.com",
                    "Test User", "none", "none", "New York"),
            new User(4, "UserTest", "password", "user@username.com",
                    "Test User", "none", "none", "New York"),
            new User(5, "UserTest", "password", "user@username.com",
                    "Test User", "none", "none", "New York"),
            new User(6, "UserTest", "password", "user@username.com",
                    "Test User", "none", "none", "New York"),
            new User(7, "UserTest", "password", "user@username.com",
                    "Test User", "none", "none", "New York"),
            new User(8, "UserTest", "password", "user@username.com",
                    "Test User", "none", "none", "New York"),
        };
        builder.setElements(users);
        builder.setView(ViewType.USER_ITEM);
        builder.setStageManager(manager);
        builder.buildPagination(pagination);
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