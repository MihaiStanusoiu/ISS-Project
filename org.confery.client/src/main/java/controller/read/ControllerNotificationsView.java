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
import service.CollectionService;
import service.NotificationService;
import transfarable.Notification;
import view.ViewType;

import java.rmi.RemoteException;

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

    @Override
    public void initialize() {
        logger = Logger.getLogger(ControllerNotificationsView.class);
        SimpleMethod<RemoteException> handler = exception -> logger.error(exception.getCause().getMessage());
        NotificationService notificationService = runFunction(service::notificationService).orHandle(handler);
        notifications = FXCollections.observableArrayList(runFunction(notificationService::getAll).orHandle(handler));
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
