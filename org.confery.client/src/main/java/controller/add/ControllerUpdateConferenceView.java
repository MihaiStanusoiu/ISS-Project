package controller.add;

import controller.main.ControllerInterface;
import itemcontroller.ControllerItemInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import list.ListViewBuilder;
import manager.StageManager;
import method.SimpleMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import service.*;
import transfarable.Conference;
import transfarable.Edition;
import view.Icon;
import view.ViewType;

import java.rmi.RemoteException;
import java.util.Date;

import static utils.Try.runFunction;
import static utils.Try.runMethod;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
@SuppressWarnings("EmptyMethod")
public class ControllerUpdateConferenceView
        implements ControllerInterface, ControllerItemInterface<Conference> {

    @FXML
    private TextField nameTextField;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField acronymTextField;

    @FXML
    private ListView<Edition> editionListView;

    @Lazy
    @Autowired
    private StageManager manager;

    @Lazy
    @Autowired
    private CollectionService service;

    private Conference conference;
    private SimpleMethod<RemoteException> handler;
    private AuthenticationService authenticationService;
    private ConferenceService conferenceService;
    private EditionService editionService;
    private SessionService sessionService;

    private static Logger logger;

    public void initialize() {
        logger = Logger.getLogger(ControllerUpdateConferenceView.class);
        handler = exception -> logger.error(exception.getCause());
        authenticationService = runFunction(service::authenticationService).orHandle(handler);
    }

    @Override
    public void setElement(Conference element) {
        conference = element;
        buildContext();
        buildPreview();
        buildEditionList();
    }

    private void buildContext() {
        getServicesReady();
    }

    private void getServicesReady() {
        conferenceService = runFunction(service::conferenceService).orHandle(handler);
        editionService = runFunction(service::editionService).orHandle(handler);
        sessionService = runFunction(service::sessionService).orHandle(handler);
    }

    private void buildPreview() {
        nameLabel.setText(conference.getName());
        nameTextField.setText(conference.getName());
        acronymTextField.setText(conference.getAcronym());
    }

    private void buildEditionList() {
        ObservableList<Edition> editionList = FXCollections.observableArrayList(runFunction(conferenceService::getEditionsOf,
                conference).orHandle(handler));
        editionListView.setItems(editionList);
        editionListView.setOnMouseClicked(event -> onEditionItemClick());
        editionListView = new ListViewBuilder<>(editionListView)
                .setIcon(Icon.CLOSE)
                .visibleText(Edition::getLocation)
                .setAction((service, item) -> {
                    runFunction(service::delete, item).orHandle(handler);
                    manager.refresh(conference);
                    return Boolean.TRUE;
                }, editionService)
                .build();
    }

    private void onEditionItemClick() {
        manager.switchScene(ViewType.EDITION_UPDATE, getSelectedEdition());
    }

    @FXML
    private void onSaveButtonClick() {
        Conference update = new Conference(nameTextField.getText(), acronymTextField.getText());
        runMethod(conferenceService::update, conference, update).orHandle(handler);
        // TODO
        manager.switchScene(ViewType.CONFERENCES);
    }

    @FXML
    private void onDeleteButtonClick() {
        runFunction(conferenceService::delete, conference).orHandle(handler);
        manager.switchScene(ViewType.CONFERENCES);
    }

    @FXML
    private void onAddEditionButtonClick() {
        // TODO
    }

    @SuppressWarnings("unused")
    private Edition getDefaultEdition() {
        return new Edition(0, new Date(), new Date(), "", "",
                new Date(), new Date(), new Date(), new Date());
    }

    private Edition getSelectedEdition() {
        return editionListView.getSelectionModel().getSelectedItem();
    }

}
