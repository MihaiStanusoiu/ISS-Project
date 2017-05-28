package controller;

import domain.UserEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import listener.Listener;
import manager.StageManager;
import service.UserService;
import method.SimpleMethod;
import notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import service.CollectionService;
import service.SubscriberService;

import java.rmi.RemoteException;
import java.util.List;

import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class ControllerConferencesView
        implements ControllerInterface, SubscriberService {

    @FXML private Button recentButton;
    @FXML private Button popularButton;
    @FXML private TextField searchTextField;
    @FXML private Pagination pagination;

    @Lazy
    @Autowired
    private StageManager manager;

    @Lazy
    @Autowired
    private Listener listener;

    @Lazy
    @Autowired
    private CollectionService service;

    /**
     * Effect: Builds the pagination and it's data.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void initialize() {
        // This part is for testing the pagination's builder with mocking data.
        // TODO
        SimpleMethod<RemoteException> handler = exception -> System.out.print(exception.getMessage());
        UserService userService = runFunction(service::userService).orHandle(handler);
        List<UserEntity> users = runFunction(userService::getAll).orHandle(handler);
        users.forEach(System.out::println);
        manager.getPrimaryStage().setOnCloseRequest(event ->
                runFunction(listener::removeSubscriber, this).orHandle(System.out::print));
        runFunction(listener::addSubscriber, this).orHandle(System.out::println);
    }


    /**
     * Effect: Sorts conferences by popularity
     * based on the number of members that participate at the conference.
     * @implNote status: Unavailable at the moment.
     */
    @FXML private void onPopularButtonClick() {
        popularButton.setOpacity(1);
        recentButton.setOpacity(0.5);
    }

    /**
     * Effect: Sorts conferences by date.
     * @implNote status: Unavailable at the moment.
     */
    @FXML private void onRecentButtonClick() {
        popularButton.setOpacity(0.5);
        recentButton.setOpacity(1);
    }

    /**
     * Effect: Search function for conferences.
     * @implNote status: Unavailable at the moment.
     */
    @FXML private void onSearchButtonClick() {
        String searchTerm = searchTextField.getText();
        System.out.println(searchTerm);
    }

    @Override
    public void update(Notification notification) throws RemoteException {
        System.out.print("Test");
    }
}
