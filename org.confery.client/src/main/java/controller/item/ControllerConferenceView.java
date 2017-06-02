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
import method.SimpleMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import pagination.PaginationBuilder;
import service.CollectionService;
import service.ConferenceService;
import transfarable.Conference;
import transfarable.Edition;
import view.ViewType;

import java.rmi.RemoteException;
import java.util.List;

import static utils.Try.runFunction;

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

    @SuppressWarnings("FieldCanBeLocal")
    private ObservableList<Edition> editions;

    @FXML
    private Pagination pagination;

    @Lazy
    @Autowired
    private StageManager manager;

    @Lazy
    @Autowired
    private CollectionService collectionService;

    private ConferenceService conferenceService;

    private static Logger logger;

    private SimpleMethod<RemoteException> handler;

    @Override
    public void initialize() {
        logger = Logger.getLogger(ControllerConferenceView.class);
        handler = exception -> logger.error(exception.getCause());
        conferenceService = runFunction(collectionService::conferenceService).orHandle(handler);
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
        List<Edition> items = runFunction(conferenceService::getEditionsOf, element).orHandle(handler);
        editions = FXCollections.observableArrayList(items);
        pagination = updatePagination(editions);
    }
}
