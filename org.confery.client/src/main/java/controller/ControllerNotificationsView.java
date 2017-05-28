package controller;

import item.pagination.controller.ControllerPaginationNotificationItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.layout.GridPane;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import pagination.PaginationBuilder;
import transfarable.Notification;
import view.ViewType;

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
    private StageManager manager;

    private ObservableList<Notification> notifications;


    /**
     * Effect: Builds the pagination and it's data.
     */
    @Override
    public void initialize() {
        Notification[] items = {
                new Notification(1, "Test", Boolean.TRUE),
                new Notification(1, "Test", Boolean.FALSE),
                new Notification(1, "Test", Boolean.FALSE),
                new Notification(1, "Test", Boolean.TRUE),
                new Notification(1, "Test", Boolean.TRUE),
                new Notification(1, "Test", Boolean.TRUE),
        };
        notifications = FXCollections.observableArrayList(items);
        pagination = updatePagination(notifications);
        pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
    }

    @SuppressWarnings("unchecked")
    private Pagination updatePagination(ObservableList<Notification> items) {
        return new PaginationBuilder<Notification, ControllerPaginationNotificationItem, GridPane>()
                .setRows(2).setColumns(4)
                .setElements(items)
                .setView(ViewType.NOTIFICATION_ITEM)
                .setStageManager(manager)
                .setPagination(pagination)
                .build(GridPane.class);
    }
}
