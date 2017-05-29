package controller.item;

import controller.main.ControllerInterface;
import controller.pagination.ControllerPaginationEditionItem;
import itemcontroller.ControllerItemInterface;
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

    @SuppressWarnings("FieldCanBeLocal")
    private Conference element;

    @SuppressWarnings("FieldCanBeLocal")
    private ObservableList<Edition> editions;

    @FXML
    private Pagination pagination;

    @Lazy
    @Autowired
    private StageManager manager;

    @Override
    public void initialize() {
        Edition[] items = { new Edition(1, new Date(), new Date(),
                "New York", "Lorem", new Date(), new Date(), new Date(), new Date())};
        editions = FXCollections.observableArrayList(items);
        pagination = updatePagination(editions);
    }

    private Pagination updatePagination(ObservableList<Edition> items) {
        return new PaginationBuilder<Edition, ControllerPaginationEditionItem, GridPane>()
                .setElements(items)
                .setView(ViewType.EDITION_ITEM)
                .setStageManager(manager)
                .setPagination(pagination)
                .build(GridPane.class);
    }

    @Override
    public void setElement(Conference element) {
        this.element = element;
        nameLabel.setText(element.getName());
    }
}
