package itemcontroller;

import controller.ControllerInterface;
import domain.ConferenceEntity;
import domain.UserEntity;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import manager.StageManager;
import notification.NotificationUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import service.SubscriberService;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class ControllerMembersConferenceView
        implements ControllerInterface, ControllerItemInterface<ConferenceEntity>, SubscriberService {

    @FXML private ListView<UserEntity> chairListView;

    @Lazy
    @Autowired
    private StageManager manager;

    private ConferenceEntity conference;

    @Override
    public void setElement(ConferenceEntity element) {
        this.conference = element;
        setUpChairList();
        updateData();
    }

    private void setUpChairList() {
        //chairListView.setCellFactory(param -> new UserListCell(manager));
    }

    private void updateData() {
        updateChairList();
    }

    private void updateChairList() {
        List<UserEntity> chairs = new ArrayList<>();
        chairs.add(new UserEntity("test", "John Snow"));
        updateListView(chairListView, chairs);
    }

    private <T> void updateListView(ListView<T> listView, List<T> list) {
        listView.setItems(FXCollections.observableArrayList(list));
    }

    /**
     * Effect: Builds the pagination and it's data.
     */
    public void initialize() {}

    @Override
    public void update(NotificationUpdate notification) throws RemoteException { }

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
