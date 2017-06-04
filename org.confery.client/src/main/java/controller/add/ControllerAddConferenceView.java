package controller.add;

import controller.main.ControllerInterface;
import itemcontroller.ControllerItemInterface;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import list.ListViewBuilder;
import manager.StageManager;
import method.SimpleMethod;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import service.*;
import transfarable.Conference;
import transfarable.Edition;
import transfarable.User;
import utils.ConferenceContext;
import utils.EditionContext;
import view.Icon;
import view.ViewType;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import static utils.Conditional.basedOn;
import static utils.Try.runFunction;
import static utils.Try.runMethod;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
@SuppressWarnings("EmptyMethod")
public class ControllerAddConferenceView
        implements ControllerInterface, ControllerItemInterface<ConferenceContext> {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField acronymTextField;

    @FXML
    private ListView<EditionContext> editionListView;

    @Lazy
    @Autowired
    private StageManager manager;

    @Lazy
    @Autowired
    private CollectionService service;

    private ConferenceContext context;
    private SimpleMethod<RemoteException> handler;
    private AuthenticationService authenticationService;
    private ConferenceService conferenceService;
    private EditionService editionService;
    private SessionService sessionService;

    private static Logger logger;

    public void initialize() {
        logger = Logger.getLogger(ControllerAddConferenceView.class);
        handler = exception -> logger.error(exception.getCause());
        authenticationService = runFunction(service::authenticationService).orHandle(handler);
    }

    @Override
    public void setElement(ConferenceContext element) {
        context = element;
        buildContext();
        buildPreview();
        buildEditionList();
    }

    private void buildContext() {
        getServicesReady();
        setUpServices();
        setUpChair();
    }

    private void setUpChair() {
        User active = runFunction(authenticationService::getActiveUser).orHandle(handler);
        basedOn(!active.getId().equals(0)).runTrue(this::setUpChairForConference, active);
    }

    private void setUpServices() {
        context.setConferenceService(conferenceService);
        context.setEditionService(editionService);
        context.setSessionService(sessionService);
    }

    private void getServicesReady() {
        conferenceService = runFunction(service::conferenceService).orHandle(handler);
        editionService = runFunction(service::editionService).orHandle(handler);
        sessionService = runFunction(service::sessionService).orHandle(handler);
    }

    private void setUpChairForConference(User active) {
        context.setChair(active);
    }

    private void buildPreview() {
        nameTextField.setText(context.getConference().getName());
        acronymTextField.setText(context.getConference().getAcronym());
    }

    private void buildEditionList() {
        editionListView.setItems(context.getEditionContexts());
        editionListView.setOnMouseClicked(event -> onEditionItemClick());
        editionListView = new ListViewBuilder<>(editionListView)
                .setIcon(Icon.CLOSE)
                .textProvider(context -> context.getEdition().getLocation())
                .setAction((list, item) -> ((List)list).remove(item), context.getEditionContexts())
                .build();
    }

    private void onEditionItemClick() {
        context.updateEditionContext(getSelectedEdition());
        manager.switchScene(ViewType.ADD_EDITION, context);
    }

    private EditionContext getSelectedEdition() {
        return editionListView.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void onPublishButtonClick() {
        context.setConference(getCurrentConference());
        runMethod(context::publish).orHandle(exception -> logger.error(exception.getMessage()));
        manager.switchScene(ViewType.CONFERENCES);
    }

    @FXML
    private void onAddEditionButtonClick() {
        context.setConference(getCurrentConference());
        Edition selectedEdition = getDefaultEdition();
        context.addEdition(selectedEdition);
        manager.switchScene(ViewType.ADD_EDITION, context);
    }

    private Edition getDefaultEdition() {
        return new Edition(0, new Date(), new Date(), "", "",
                new Date(), new Date(), new Date(), new Date());
    }

    @NotNull
    private Conference getCurrentConference() {
        return new Conference(nameTextField.getText(), acronymTextField.getText());
    }

}
