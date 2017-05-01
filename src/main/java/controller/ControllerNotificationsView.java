package controller;

import data.Notification;
import itemcontroller.ControllerNotificationItem;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.layout.GridPane;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import pagination.PaginationBuilder;
import view.ViewType;

/**
 * Name:        ControllerNotificationsView
 * Effect:      Lists all the available notifications.
 * Date:        02/04/2017
 * Tested:      False
 *
 * @author      Alexandru Stoica
 * @version     1.0
 */

@Component
public class ControllerNotificationsView implements ControllerInterface {

    @FXML private Pagination pagination;

    private StageManager manager;

    @Autowired
    @Lazy
    public ControllerNotificationsView(StageManager manager) {
        this.manager = manager;
    }

    /**
     * Effect: Builds the pagination and it's data.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void initialize() {
        // This part is for testing the pagination's builder with mocking data.
        Notification[] notifications = {
                new Notification("TestA", Boolean.FALSE),
                new Notification("TestB", Boolean.TRUE),
                new Notification("TestB", Boolean.TRUE),
                new Notification("TestB", Boolean.TRUE),
                new Notification("TestB", Boolean.TRUE),
                new Notification("TestB", Boolean.TRUE),
                new Notification("TestB", Boolean.TRUE),
                new Notification("TestB", Boolean.TRUE)
        };
        pagination = new PaginationBuilder<Notification, ControllerNotificationItem, GridPane>()
                .setRows(2)
                .setColumns(4)
                .setElements(notifications)
                .setView(ViewType.NOTIFICATION_ITEM)
                .setStageManager(this.manager)
                .setPagination(this.pagination)
                .build(GridPane.class);
        pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
    }

}
