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

    @FXML
    private ListView<UserEntity> chairListView;

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

    private void setUpChairList() { }

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
    public void initialize() { }

    @Override
    public void update(NotificationUpdate notification) throws RemoteException { }

    @FXML
    private void onPublishButtonClick() {
        // TODO
    }

    @FXML
    private void onSaveButtonClick() {
        // TODO
    }

    @FXML
    private void onMembersButtonClick() {
        // TODO
    }

    @FXML
    private void onBasicButtonClick() {
        // TODO
    }

    @FXML
    private void onSessionsButtonClick() {
        // TODO
    }

    @FXML
    private void onSubmissionsButtonClick() {
        // TODO
    }

}
