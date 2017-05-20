package itemcontroller;

import cells.UserListCell;
import controller.ControllerInterface;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import listener.Listener;
import manager.StageManager;
import notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import service.SubscriberService;
import transferable.Conference;
import transferable.User;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */


@Component
public class ControllerMembersConferenceView
        implements ControllerInterface, ControllerItemInterface<Conference>, SubscriberService {

    @FXML private ListView<User> chairListView;

    private final StageManager manager;
    private final Listener listener;
    private Conference conference;


    @Autowired @Lazy
    public ControllerMembersConferenceView(StageManager manager, Listener listener)
            throws RemoteException {
        this.manager = manager;
        this.listener = listener;
        this.listener.addSubscriber(this);
        this.chairListView = new ListView<>();
    }

    @Override
    public void setElement(Conference element) {
        this.conference = element;
        setUpChairList();
        updateData();
    }

    private void setUpChairList() {
        chairListView.setCellFactory(param -> new UserListCell(manager));
    }

    private void updateData() {
        updateChairList();
    }

    private void updateChairList() {
        List<User> chairs = new ArrayList<>();
        chairs.add(new User("john-snow", "test", "John Snow"));
        updateListView(chairListView, chairs);
    }

    private <T> void updateListView(ListView<T> listView, List<T> list) {
        listView.setItems(FXCollections.observableArrayList(list));
    }

    /**
     * Effect: Builds the pagination and it's data.
     */
    public void initialize() { }

    @Override
    public void update(Notification notification) throws RemoteException { }

    @FXML private void onPublishButtonClick() {
        System.out.print("Publish Button Click" +  conference.getName());
        // TODO
    }

    @FXML private void onSaveButtonClick() {
        System.out.print("Save Button Click" +  conference.getName());
        // TODO
    }

    @FXML private void onMembersButtonClick() {
        System.out.print("Members Button Click" +  conference.getName());
        // TODO
    }

    @FXML private void onBasicButtonClick() {
        System.out.print("Basic Button Click" +  conference.getName());
        // TODO
    }

    @FXML private void onSessionsButtonClick() {
        System.out.print("Session Button Click" +  conference.getName());
        // TODO
    }

    @FXML private void onSubmissionsButtonClick() {
        System.out.print("Submission Button Click" +  conference.getName());
        // TODO
    }

}
