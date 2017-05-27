package controller;

import domain.NotificationEntity;
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
        NotificationEntity[] notifications = {
                new NotificationEntity("TestA", Boolean.FALSE),
                new NotificationEntity("TestB", Boolean.TRUE),
                new NotificationEntity("TestB", Boolean.TRUE),
                new NotificationEntity("TestB", Boolean.TRUE),
                new NotificationEntity("TestB", Boolean.TRUE),
                new NotificationEntity("TestB", Boolean.TRUE),
                new NotificationEntity("TestB", Boolean.TRUE),
                new NotificationEntity("TestB", Boolean.TRUE)
        };
        pagination = new PaginationBuilder<NotificationEntity, ControllerNotificationItem, GridPane>()
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
