package controller;

import item.pagination.controller.ControllerPaginationUserItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import manager.StageManager;
import notification.NotificationUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import pagination.PaginationBuilder;
import service.SubscriberService;
import transfarable.User;
import view.ViewType;

import java.rmi.RemoteException;

/**
 * @author      Alexandru Stoica
 * @version     1.0
 */

@Lazy
@Component
public class ControllerUsersView
        implements ControllerInterface, SubscriberService {

    @Lazy
    @Autowired
    private StageManager manager;

    @FXML private TextField searchTextField;
    @FXML public Pagination pagination;

    private ObservableList<User> users;

    @Override
    public void initialize() {
        User[] usersList = {
                new User("Test", "password", "email@email", "Test Name"),
                new User("Test", "password", "email@email", "Test Name"),
                new User("Test", "password", "email@email", "Test Name"),
                new User("Test", "password", "email@email", "Test Name"),
                new User("Test", "password", "email@email", "Test Name"),
                new User("Test", "password", "email@email", "Test Name"),
                new User("Test", "password", "email@email", "Test Name")
        };
        users = FXCollections.observableArrayList(usersList);
        pagination = updatePagination(users);
        pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
    }

    @SuppressWarnings("unchecked")
    private Pagination updatePagination(ObservableList<User> list) {
        return new PaginationBuilder<User, ControllerPaginationUserItem, GridPane>()
                .setRows(2)
                .setColumns(4)
                .setElements(list)
                .setView(ViewType.USER_ITEM)
                .setStageManager(this.manager)
                .setPagination(this.pagination)
                .build(GridPane.class);
    }

    /**
     * Effect: Search function for users.
     * @implNote status: Unavailable at the moment.
     */
    @FXML private void onSearchButtonClick() {
        String searchTerm = searchTextField.getText();
        System.out.println(searchTerm);
    }

    @Override
    public void update(NotificationUpdate notification) throws RemoteException {
        System.out.println("Hello");
    }
}