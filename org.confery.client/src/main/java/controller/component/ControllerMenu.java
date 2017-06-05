package controller.component;

import context.Context;
import context.ContextClass;
import context.ContextType;
import context.CoreContext;
import controller.main.ControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import manager.StageManager;
import method.SimpleMethod;
import notifier.LocalNotificationCenter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import service.AuthenticationService;
import service.CollectionService;
import transfarable.User;
import utils.ConferenceContext;
import view.ViewType;

import java.rmi.RemoteException;

import static utils.Try.runFunction;
import static utils.Try.runMethod;


/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
@ContextClass
public class ControllerMenu implements ControllerInterface {

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
    private CollectionService service;

    @Lazy
    @Autowired
    private CoreContext context;

    @Autowired
    private LocalNotificationCenter center;

    private static Logger logger;
    private AuthenticationService authenticationService;

    private SimpleMethod<RemoteException> handler;

    @Override
    public void initialize() {
        logger = Logger.getLogger(ControllerMenu.class);
        handler = exception -> logger.info(exception.getCause().getMessage());
        authenticationService = runFunction(service::authenticationService).orHandle(handler);
        context.in(this).basedOn(getActiveUser().getId().equals(0))
                .forType(ContextType.GUEST)
                .run(button -> ((Button)button).setVisible(false))
                .or(button -> ((Button)button).setVisible(true));
    }

    private User getActiveUser() {
        return runFunction(authenticationService::getActiveUser).orHandle(handler);
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
        manager.switchScene(ViewType.SUBMISSIONS);
    }

    @FXML
    private void onMyConferencesViewButtonClick() {
        manager.switchScene(ViewType.MY_CONFERENCES);
    }

    @FXML
    private void onLogoutButtonClick() {
        runMethod(authenticationService::deleteActiveUser, getActiveUser()).orHandle(handler);
        manager.refresh();
    }

}
