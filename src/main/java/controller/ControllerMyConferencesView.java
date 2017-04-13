package controller;

import domain.Conference;
import itemcontroller.ControllerConferenceItem;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
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
 * Name:        ControllerMyConferencesView
 * Effect:      Lists all the user's conferences.
 * Date:        02/04/2017
 * Tested:      False
 *
 * @author      Alexandru Stoica
 * @version     1.0
 */

@Component
public class ControllerMyConferencesView implements ControllerInterface {

    @FXML private TextField searchTextField;
    @FXML private Pagination pagination;

    private StageManager manager;

    @Autowired @Lazy
    public ControllerMyConferencesView(StageManager manager) {
        this.manager = manager;
    }

    /**
     * Effect: Builds the pagination and it's data.
     */
    @Override
    public void initialize() {
        PaginationBuilderInterface<Conference, ControllerConferenceItem, GridPane> builder =
                new PaginationBuilder<>(2,4);
            // This part is for testing the pagination's builder with mocking data.
        Conference[] conferences = {
                new Conference(1, "Test Conference $1", "TC1", new Date(), new Date(),
                "New York", "bio", new Date(), new Date(), new Date(), new Date()),
                new Conference(2, "Test Conference $2", "TC1", new Date(), new Date(),
                        "New York", "bio", new Date(), new Date(), new Date(), new Date()),
                new Conference(3, "Test Conference $3", "TC1", new Date(), new Date(),
                        "New York", "bio", new Date(), new Date(), new Date(), new Date()),
                new Conference(4, "Test Conference $4", "TC1", new Date(), new Date(),
                        "New York", "bio", new Date(), new Date(), new Date(), new Date()),
                new Conference(1, "Test Conference $1", "TC1", new Date(), new Date(),
                        "New York", "bio", new Date(), new Date(), new Date(), new Date())
        };
        builder.setElements(conferences);
        builder.setView(ViewType.CONFERENCE_ITEM);
        builder.setStageManager(manager);
        builder.buildPagination(pagination);
        pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
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
