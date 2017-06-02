package controller.pagination;

import controller.item.ControllerConferenceView;
import itemcontroller.PaginationControllerItemInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import manager.StageManager;
import method.SimpleMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import service.CollectionService;
import service.ConferenceService;
import transfarable.Conference;
import view.GradientGenerator;
import view.ViewType;

import java.rmi.RemoteException;

import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.1
 */

@Lazy
@Component
@Scope("prototype")
public class ControllerPaginationConferenceItem
        implements PaginationControllerItemInterface<Conference> {

    @FXML
    private Label editionNumberLabel;

    @FXML
    private Label acronymLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private BorderPane rootPane;

    @FXML
    private Button background;

    @Lazy
    @Autowired
    private StageManager manager;

    private GradientGenerator generator;

    private Conference item;

    @Lazy
    @Autowired
    private CollectionService collectionService;

    private ConferenceService conferenceService;

    private static Logger logger;

    private SimpleMethod<RemoteException> handler;

    @Override
    public void initialize() {
        generator = new GradientGenerator();
        logger = Logger.getLogger(ControllerConferenceView.class);
        handler = exception -> logger.error(exception.getCause());
        conferenceService = runFunction(collectionService::conferenceService).orHandle(handler);
    }

    @Override
    public void setElement(Conference element) {
        this.item = element;
        build();
    }

    @FXML
    public void onItemClick() {
        manager.switchScene(ViewType.CONFERENCE, item);
    }

    private void build() {
        editionNumberLabel.setText(getEditionNumber().toString());
        acronymLabel.setText(item.getAcronym());
        nameLabel.setText(item.getName());
        background.setStyle(String.format("-fx-background-color : %s",
                generator.getGradient().getValue()));   /* sets a random gradient */
    }

    private Integer getEditionNumber() {
        return runFunction(conferenceService::getEditionsOf, item).orHandle(handler).size();
    }

    public Pane getRootPane() {
        return rootPane;
    }

    @Override
    public double getWidth() {
        return rootPane.getWidth();
    }

    @Override
    public double getHeight() {
        return rootPane.getHeight();
    }

}
