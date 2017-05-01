package controller;

import data.Conference;
import data.Edition;
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
