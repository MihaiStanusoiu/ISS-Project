package controller;

import domain.ConferenceEntity;
import domain.EditionEntity;
import itemcontroller.ControllerConferenceItem;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import listener.ListenerHelper;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import pagination.PaginationBuilder;
import view.ViewType;

import java.util.Date;

/**
 * @author      Alexandru Stoica
 * @version     1.0
 */

@Lazy
@Component
public class ControllerMyConferencesView
        implements ControllerInterface {

    @FXML private TextField searchTextField;
    @FXML private Pagination pagination;

    @Lazy
    @Autowired
    private StageManager manager;

    @Lazy
    @Autowired
    private ListenerHelper listener;

    /**
     * Effect: Builds the pagination and it's data.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void initialize() {
        // This part is for testing the pagination's builder with mocking data.
        EditionEntity[] editions = {
                new EditionEntity(new Date(), new Date(), "New York"),
                new EditionEntity(new Date(), new Date(), "New York"),
                new EditionEntity(new Date(), new Date(), "New York"),
                new EditionEntity(new Date(), new Date(), "New York"),
                new EditionEntity(new Date(), new Date(), "New York"),
                new EditionEntity(new Date(), new Date(), "New York"),
                new EditionEntity(new Date(), new Date(), "New York"),
                new EditionEntity(new Date(), new Date(), "New York")
        };
        ConferenceEntity[] conferences = {
                new ConferenceEntity("Test", "AAA"),
                new ConferenceEntity("Test", "AAA"),
                new ConferenceEntity("Test", "AAA"),
                new ConferenceEntity("Test", "AAA"),
                new ConferenceEntity("Test", "AAA"),
                new ConferenceEntity("Test", "AAA"),
                new ConferenceEntity("Test", "AAA"),
                new ConferenceEntity("Test", "AAA"),
        };
        pagination = new PaginationBuilder<ConferenceEntity, ControllerConferenceItem, GridPane>()
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
