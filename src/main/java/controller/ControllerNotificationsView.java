package controller;

import domain.Notification;
import itemcontroller.ControllerNotificationItem;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.layout.GridPane;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import pagination.PaginationBuilder;
import pagination.PaginationBuilderInterface;
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
    public void initialize() {
        PaginationBuilderInterface<Notification, ControllerNotificationItem, GridPane> builder =
                new PaginationBuilder<>(2, 4);
        // This part is for testing the pagination's builder with mocking data.
        Notification[] notifications = {
            new Notification(1, "@username added you to FSDF as member of the program committee!",
                true, 1),
            new Notification(1, "@username added you to FSDF as member of the program committee!",
                    false, 1),
            new Notification(1, "@username added you to FSDF as member of the program committee!",
                    false, 1),
            new Notification(1, "@username added you to FSDF as member of the program committee!",
                    true, 1)
        };
        builder.setElements(notifications);
        builder.setView(ViewType.NOTIFICATION_ITEM);
        builder.setStageManager(manager);
        builder.buildPagination(pagination);
        pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
    }

}
