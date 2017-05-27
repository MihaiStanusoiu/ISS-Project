package controller;

import itemcontroller.ControllerConferenceItem;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import listener.Listener;
import manager.StageManager;
import notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import pagination.PaginationBuilder;
import service.SubscriberService;
import transferable.Conference;
import transferable.Edition;
import view.ViewType;

import java.rmi.RemoteException;
import java.util.Date;

import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class ControllerConferencesView
        implements ControllerInterface, SubscriberService {

    @FXML private Button recentButton;
    @FXML private Button popularButton;
    @FXML private TextField searchTextField;
    @FXML private Pagination pagination;

    @Lazy
    @Autowired
    private StageManager manager;

    @Lazy
    @Autowired
    private Listener listener;

    /**
     * Effect: Builds the pagination and it's data.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void initialize() {
        // This part is for testing the pagination's builder with mocking data.
        Edition[] editions = {
                new Edition(new Date(), new Date(), "New York"),
                new Edition(new Date(), new Date(), "New York"),
                new Edition(new Date(), new Date(), "New York"),
                new Edition(new Date(), new Date(), "New York"),
                new Edition(new Date(), new Date(), "New York"),
                new Edition(new Date(), new Date(), "New York"),
                new Edition(new Date(), new Date(), "New York"),
                new Edition(new Date(), new Date(), "New York")
        };
        Conference[] conferences = {
                new Conference("Test", "AAA", editions[0]),
                new Conference("Test", "AAA", editions[1]),
                new Conference("Test", "AAA", editions[2]),
                new Conference("Test", "AAA", editions[3]),
                new Conference("Test", "AAA", editions[4]),
                new Conference("Test", "AAA", editions[5]),
                new Conference("Test", "AAA", editions[6]),
                new Conference("Test", "AAA", editions[7]),
        };
        pagination = new PaginationBuilder<Conference, ControllerConferenceItem, GridPane>()
              .setRows(2)
              .setColumns(4)
              .setElements(conferences)
              .setView(ViewType.CONFERENCE_ITEM)
              .setStageManager(this.manager)
              .setPagination(this.pagination)
              .build(GridPane.class);
        pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
        manager.getPrimaryStage().setOnCloseRequest(event -> {
            System.out.print("mere");
            runFunction(listener::removeSubscriber, this).orHandle(System.out::print);
                });
        runFunction(listener::addSubscriber, this).orHandle(System.out::println);
    }


    /**
     * Effect: Sorts conferences by popularity
     * based on the number of members that participate at the conference.
     * @implNote status: Unavailable at the moment.
     */
    @FXML private void onPopularButtonClick() {
        popularButton.setOpacity(1);
        recentButton.setOpacity(0.5);
    }

    /**
     * Effect: Sorts conferences by date.
     * @implNote status: Unavailable at the moment.
     */
    @FXML private void onRecentButtonClick() {
        popularButton.setOpacity(0.5);
        recentButton.setOpacity(1);
    }

    /**
     * Effect: Search function for conferences.
     * @implNote status: Unavailable at the moment.
     */
    @FXML private void onSearchButtonClick() {
        String searchTerm = searchTextField.getText();
        System.out.println(searchTerm);
    }

    @Override
    public void update(Notification notification) throws RemoteException {
        System.out.print("Test");
    }
}
