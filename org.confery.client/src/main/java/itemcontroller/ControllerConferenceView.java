package itemcontroller;

import controller.main.ControllerInterface;
import item.pagination.controller.ControllerPaginationEditionItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.GridPane;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import pagination.PaginationBuilder;
import transfarable.Conference;
import transfarable.Edition;
import view.ViewType;

import java.util.Date;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class ControllerConferenceView
        implements ControllerInterface, ControllerItemInterface<Conference> {

    @FXML
    private Label nameLabel;

    private Conference element;

    private ObservableList<Edition> editions;

    @FXML
    private Pagination pagination;

    @Lazy
    @Autowired
    private StageManager manager;

    /**
     * Effect: Builds the pagination and it's data.
     */
    @Override
    public void initialize() {
        Edition[] items = {
                new Edition(1, new Date(), new Date(), "New York", "Lorem",
                        new Date(), new Date(), new Date(), new Date()),
                new Edition(new Date(), "New York"),
                new Edition(new Date(), "New York"),
                new Edition(new Date(), "New York"),
                new Edition(new Date(), "New York"),
                new Edition(new Date(), "New York")
        };
        editions = FXCollections.observableArrayList(items);
        pagination = updatePagination(editions);
    }

    @SuppressWarnings("unchecked")
    private Pagination updatePagination(ObservableList<Edition> items) {
        return new PaginationBuilder<Edition, ControllerPaginationEditionItem, GridPane>()
                .setRows(2).setColumns(4)
                .setElements(items)
                .setView(ViewType.EDITION_ITEM)
                .setStageManager(this.manager)
                .setPagination(this.pagination)
                .build(GridPane.class);
    }

    /**
     * Sets the element in the controller's view
     * and builds the UI data based on the conference's attributes.
     *
     * @param element The view's element.
     */
    @Override
    public void setElement(Conference element) {
        this.element = element;
        nameLabel.setText(element.getName());
    }
}
