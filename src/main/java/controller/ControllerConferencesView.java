package controller;

import domain.Conference;
import itemcontroller.ControllerConferenceItem;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import pagination.PaginationBuilder;
import pagination.PaginationBuilderInterface;
import view.ViewType;
import java.util.Date;

/**
 * Name:        ControllerConferencesView
 * Effect:      Lists all the available conferences.
 * Date:        02/04/2017
 * Tested:      False
 *
 * @author      Alexandru Stoica
 * @version     1.0
 */

@Component
public class ControllerConferencesView implements ControllerInterface {

    @FXML private Button recentButton;
    @FXML private Button popularButton;
    @FXML private TextField searchTextField;
    @FXML private Pagination pagination;

    @SuppressWarnings("all")
    private StageManager manager;

    @Autowired @Lazy
    public ControllerConferencesView(StageManager manager) {
        this.manager = manager;
    }

    /**
     * Effect: Builds the pagination and it's data.
     */
    @Override
    public void initialize() {
        PaginationBuilderInterface<Conference, ControllerConferenceItem, GridPane> builder = new PaginationBuilder<>(2,4);
            // This part is for testing the pagination's builder with mocking data.
        Conference[] conferences = {
                new Conference(1, "Test Conference $1", "TC1", new Date(), new Date(),
                        "New York", "bio", new Date(), new Date(), new Date(), new Date()),
                new Conference(2, "Test Conference $2", "TC2", new Date(), new Date(),
                        "New York", "bio", new Date(), new Date(), new Date(), new Date())
        };
        builder.setElements(conferences);
        builder.setView(ViewType.CONFERENCE_ITEM);
        builder.setStageManager(manager);
        pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
        pagination.setPageFactory(builder::createPage);
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

}
