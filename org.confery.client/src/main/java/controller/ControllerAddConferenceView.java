package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import listener.Listener;
import manager.StageManager;
import notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import service.SubscriberService;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */


@Component
public class ControllerAddConferenceView
        implements ControllerInterface, SubscriberService {

    @FXML private TextField nameTextField;
    @FXML private TextField acronymTextField;
    @FXML private TextField locationTextField;
    @FXML private TextField bioTextField;

    private final StageManager manager;
    private final Listener listener;

    @Autowired @Lazy
    public ControllerAddConferenceView(StageManager manager, Listener listener)
            throws RemoteException {
        this.manager = manager;
        this.listener = listener;
        this.listener.addSubscriber(this);
    }

    /**
     * Effect: Builds the pagination and it's data.
     */
    public void initialize() { }

    @Override
    public void update(Notification notification) throws RemoteException { }

    @FXML private void onPublishButtonClick() {
        // TODO
    }

    @FXML private void onSaveButtonClick() {
        // TODO
    }

}
