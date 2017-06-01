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
import method.SimpleMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import pagination.PaginationBuilder;
import service.CollectionService;
import service.UserService;
import transfarable.User;
import view.ViewType;

import java.rmi.RemoteException;

import static utils.Try.runFunction;

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

    @Lazy
    @Autowired
    private CollectionService service;

    private Logger logger;

    @Override
    public void initialize() {
        logger = Logger.getLogger(ControllerUsersView.class);
        SimpleMethod<RemoteException> handler = exception -> logger.error(exception.getCause().getMessage());
        UserService userService = runFunction(service::userService).orHandle(handler);
        users = FXCollections.observableArrayList(runFunction(userService::getAll).orHandle(handler));
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