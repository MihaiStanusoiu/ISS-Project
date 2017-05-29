package controller.read;

import controller.main.ControllerInterface;
import controller.pagination.ControllerPaginationNotificationItem;
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

import java.util.List;

import static java.util.Arrays.asList;

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

    @Override
    public void initialize() {
        List<Notification> items = asList(
                new Notification(1, "Test", Boolean.TRUE),
                new Notification(2, "Test", Boolean.FALSE),
                new Notification(3, "Test", Boolean.FALSE),
                new Notification(4, "Test", Boolean.TRUE));
        notifications = FXCollections.observableArrayList(items);
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
