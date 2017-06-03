package controller.read;

import controller.main.ControllerInterface;
import controller.pagination.ControllerPaginationNotificationItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.layout.GridPane;
import manager.StageManager;
import method.SimpleMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import pagination.PaginationBuilder;
import service.AuthenticationService;
import service.CollectionService;
import service.UserService;
import transfarable.Notification;
import transfarable.User;
import view.ViewType;

import java.rmi.RemoteException;

import static utils.Conditional.basedOn;
import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class ControllerNotificationsView implements ControllerInterface {

    @FXML
    private Pagination pagination;

    @Lazy
    @Autowired
    @SuppressWarnings("unused")
    private StageManager manager;

    @SuppressWarnings("FieldCanBeLocal")
    private ObservableList<Notification> notifications;

    @Lazy
    @Autowired
    private CollectionService service;

    private static Logger logger;

    private SimpleMethod<RemoteException> handler;

    @Override
    public void initialize() {
        logger = Logger.getLogger(ControllerNotificationsView.class);
        handler = exception -> logger.error(exception.getCause().getMessage());
        checkActiveUser();
    }

    private void checkActiveUser() {
        AuthenticationService authenticationService = runFunction(service::authenticationService).orHandle(handler);
        User active = runFunction(authenticationService::getActiveUser).orHandle(handler);
        basedOn(!active.getId().equals(0)).runTrue(this::build, active);
    }

    private void build(User active) {
        UserService userService = runFunction(service::userService).orHandle(handler);
        notifications = FXCollections.observableArrayList(runFunction(userService::getNotificationsFrom, active).orHandle(handler));
        pagination = updatePagination(notifications);
    }

    private Pagination updatePagination(ObservableList<Notification> items) {
        return new PaginationBuilder<Notification, ControllerPaginationNotificationItem, GridPane>()
                .setElements(items)
                .setView(ViewType.NOTIFICATION_ITEM)
                .setStageManager(manager)
                .setPagination(pagination)
                .build(GridPane.class);
    }

}
