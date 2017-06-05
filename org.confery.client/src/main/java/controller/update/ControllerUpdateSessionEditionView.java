package controller.update;

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
import service.CollectionService;
import service.EditionService;
import transfarable.Conference;
import transfarable.Edition;
import transfarable.Session;
import view.Icon;
import view.ViewType;

import java.rmi.RemoteException;
import java.util.Date;

import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class ControllerUpdateSessionEditionView
        implements ControllerInterface, ControllerItemInterface<Edition> {

    @FXML
    private ListView<Session> sessionsListView;

    @FXML
    private Label conferenceNameLabel;

    @FXML
    private TextField sessionTextField;

    private ObservableList<Session> sessions;

    @Lazy
    @Autowired
    @SuppressWarnings("unused")
    private StageManager manager;

    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private Edition edition;

    private EditionService editionService;

    @Lazy
    @Autowired
    private CollectionService service;

    @Override
    public void setElement(Edition element) {
        this.edition = element;
        build();
    }

    private SimpleMethod<RemoteException> handler;
    private static Logger logger;

    public void initialize() {
        logger = Logger.getLogger(ControllerUpdateSessionEditionView.class);
        handler = exception ->  logger.error(exception.getClass());
        editionService = runFunction(service::editionService).orHandle(handler);
    }

    private void build() {
        conferenceNameLabel.setText(runFunction(editionService::getConferenceOf, edition).orHandle(handler).getName());
        sessions = FXCollections.observableArrayList(runFunction(editionService::getAllSessionsOf, edition).orHandle(handler));
        setUpListViews();
    }

    private void setUpListViews() {
        sessionsListView.setItems(sessions);
        sessionsListView = new ListViewBuilder<>(sessionsListView)
                .setIcon(Icon.CLOSE)
                .textProvider(Session::getName)
                .setAction((service, item) -> {
                    edition = runFunction(((EditionService)service)::deleteSessionOfEdition, edition, item).orHandle(handler);
                    manager.refresh(edition);
                    return null;
                }, editionService).build();
    }

    @FXML
    private void onAddSessionButtonClick() {
        Session session = new Session(0, sessionTextField.getText(), new Date(), new Date(), edition.getLocation(), "", 100);
        edition = runFunction(editionService::addSessionToEdition, edition, session).orHandle(handler);
        manager.refresh(edition);
    }

    @FXML
    private void onPublishButtonClick() {
        manager.switchScene(ViewType.CONFERENCE);
    }

    @FXML
    private void onBasicButtonClick() {
        manager.switchScene(ViewType.EDITION_UPDATE, edition);
    }

    @FXML
    private void onMembersButtonClick() {
        manager.switchScene(ViewType.UPDATE_EDITION_MEMBERS, edition);
    }

    @FXML
    private void onBackButtonClick() {
        manager.switchScene(ViewType.CONFERENCE_UPDATE, getConference());
    }

    private Conference getConference() {
        return runFunction(editionService::getConferenceOf, edition).orHandle(handler);
    }

    @FXML
    private void onSubmissionsButtonClick() {
        // TODO
    }

}
