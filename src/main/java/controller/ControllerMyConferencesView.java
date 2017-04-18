package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

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
//        Conference[] conferences = {
//        };
//        pagination = new PaginationBuilder<Conference, ControllerConferenceItem, GridPane>()
//                .setRows(2)
//                .setColumns(4)
//                .setElements(conferences)
//                .setView(ViewType.CONFERENCE_ITEM)
//                .setStageManager(this.manager)
//                .setPagination(this.pagination)
//                .build(GridPane.class);
//        pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
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
