package controller.component;

import context.Context;
import context.ContextClass;
import context.ContextType;
import context.CoreContext;
import controller.main.ControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import listener.ListenerHelper;
import manager.StageManager;
import method.SimpleMethod;
import notifier.LocalNotificationCenter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import service.CollectionService;
import transfarable.User;
import utils.ConferenceContext;
import view.ViewType;

import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;

import static utils.Conditional.basedOn;
import static utils.Try.runFunction;
import static utils.Try.runMethod;


/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
@ContextClass
public class ControllerMenu implements ControllerInterface, Observer {

    @FXML
    @Context({ContextType.GUEST})
    public Button logoutButton;

    @FXML
    public Button myConferencesButton;

    @Lazy
    @Autowired
    private StageManager manager;

    @Lazy
    @Autowired
    private ListenerHelper listener;

    @Lazy
    @Autowired
    private CollectionService service;

    @Lazy
    @Autowired
    private CoreContext context;

    @Autowired
    private LocalNotificationCenter center;

    private static Logger logger;

    private SimpleMethod<RemoteException> handler;

    @Override
    public void initialize() {
        handler = exception -> logger.info(exception.getCause().getMessage());
        logger = Logger.getLogger(ControllerMenu.class);
        center.addObserver(this);
//        context.forType(ContextType.GUEST).in(this)
//                .run(item -> ((Node) item).setVisible(Boolean.FALSE));
    }

    @FXML
    private void onConferencesViewButtonClick() {
        manager.switchScene(ViewType.CONFERENCES);
    }

    @FXML
    private void onAddConferenceViewButtonClick() {
        manager.switchScene(ViewType.ADD_CONFERENCE, new ConferenceContext());
    }

    @FXML
    private void onNotificationsViewButtonClick() {
        manager.switchScene(ViewType.NOTIFICATIONS);
    }

    @FXML
    private void onUsersViewButtonClick() {
        manager.switchScene(ViewType.USERS);
    }

    @FXML
    private void onSubmissionsViewButtonClick() {
        System.out.println("Submissions View");
    }

    @FXML
    private void onMyConferencesViewButtonClick() {
        manager.switchScene(ViewType.MY_CONFERENCES);
    }

    @FXML
    private void onLogoutButtonClick() {
        User active = runFunction(service::getActiveUser).orHandle(handler);
        basedOn(active != null).runTrue(this::resetActiveUser);
    }

    private void resetActiveUser() {
        runMethod(service::activeUser, null).orHandle(handler);
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO
    }
}
