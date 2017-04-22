package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * <p> Lists all the available conferences. </p>
 * <p> Tested: False </p>
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

@Component
public class ControllerConferencesView implements ControllerInterface {

    @FXML private Button recentButton;
    @FXML private Button popularButton;
    @FXML private TextField searchTextField;
    @FXML private Pagination pagination;

    private StageManager manager;

    @Autowired @Lazy
    public ControllerConferencesView(StageManager manager) {
        this.manager = manager;
    }

    /**
     * Effect: Builds the pagination and it's data.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void initialize() {
            // This part is for testing the pagination's builder with mocking data.
//        ConferenceEntity[] conferences = {
//              new ConferenceEntity()
//        };
//        pagination = new PaginationBuilder<ConferenceEntity, ControllerConferenceItem, GridPane>()
//              .setRows(2)
//              .setColumns(4)
//              .setElements(conferences)
//              .setView(ViewType.CONFERENCE_ITEM)
//              .setStageManager(this.manager)
//              .setPagination(this.pagination)
//              .build(GridPane.class);
//        pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
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
