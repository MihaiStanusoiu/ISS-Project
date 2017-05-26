package controller;

import itemcontroller.ControllerNotificationItem;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.layout.GridPane;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import pagination.PaginationBuilder;
import transferable.Notification;
import view.ViewType;

/**
 * @author      Alexandru Stoica
 * @version     1.0
 */

@Lazy
@Component
public class ControllerNotificationsView implements ControllerInterface {

    @FXML private Pagination pagination;

    @Lazy
    @Autowired
    private StageManager manager;

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
