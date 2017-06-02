package controller.item;

import cells.UserListCell;
import controller.main.ControllerInterface;
import itemcontroller.ControllerItemInterface;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import manager.StageManager;
import method.SimpleMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import service.AuthenticationService;
import service.CollectionService;
import service.SessionService;
import transfarable.MemberRoleTransferable;
import transfarable.Session;
import transfarable.User;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static utils.Conditional.basedOn;
import static utils.Try.runFunction;


/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class ControllerSessionView implements ControllerInterface, ControllerItemInterface<Session> {

    @FXML
    private Label locationLabel;

    @FXML
    private Text bioText;

    @FXML
    private Text locationText;

    @FXML
    private Text seatsText;

    @FXML
    private Text startingDateText;

    @FXML
    private Text endingDateText;

    @FXML
    private ListView<User> chairs;

    @FXML
    private ListView<User> speakers;

    @FXML
    private ListView<User> listeners;


    private Session session;

    @Lazy
    @Autowired
    private StageManager manager;

    @Lazy
    @Autowired
    private CollectionService collectionService;

    private AuthenticationService authenticationService;

    private SessionService sessionService;

    private static Logger logger;
    private SimpleMethod<RemoteException> handler;

    @Override
    public void initialize() {
        logger = Logger.getLogger(ControllerSessionView.class);
        handler = exception -> logger.error(exception.getCause());
        authenticationService = runFunction(collectionService::authenticationService).orHandle(handler);
        sessionService = runFunction(collectionService::sessionService).orHandle(handler);
    }

    @FXML
    private void onJoinButtonClick() {
        User active = runFunction(authenticationService::getActiveUser).orHandle(handler);
        basedOn(!active.getId().equals(0)).runTrue(this::addUserAsListener, active);
        manager.refresh(session);
    }

    private void addUserAsListener(User user) {
        runFunction(sessionService::addMemberTo, session, user, MemberRoleTransferable.SESSION_LISTENER);
    }

    @Override
    public void setElement(Session element) {
        this.session = element;
        setUpLists();
        build();
        buildLists();
    }

    private void buildLists() {
        User chair = runFunction(sessionService::getChair, session).orHandle(handler);
        List<User> speakers = runFunction(sessionService::getSpeakers, session).orHandle(handler);
        List<User> listeners = runFunction(sessionService::getListeners, session).orHandle(handler);
        chairs.setItems(FXCollections.observableArrayList(Collections.singletonList(chair)));
        this.speakers.setItems(FXCollections.observableArrayList(speakers));
        this.listeners.setItems(FXCollections.observableArrayList(listeners));
    }

    private void setUpLists() {
        asList(chairs, listeners, speakers).forEach(this::setUpListViewCell);
    }

    private void setUpListViewCell(ListView<User> listView) {
        listView.setCellFactory(param -> new UserListCell(manager));
    }

    private void build() {
        locationLabel.setText(session.getLocation());
        bioText.setText(session.getBio());
        locationText.setText(session.getLocation());
        startingDateText.setText(session.getStartDate().toString());
        endingDateText.setText(session.getEndDate().toString());
        seatsText.setText(session.getSeats().toString());
    }
}
