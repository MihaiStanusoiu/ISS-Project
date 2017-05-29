package itemcontroller;

import controller.main.ControllerInterface;
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
    @SuppressWarnings("unused")
    private StageManager manager;

    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private ConferenceEntity conference;

    @Override
    public void setElement(ConferenceEntity element) {
        this.conference = element;
        setUpChairList();
        updateData();
    }

    @SuppressWarnings("EmptyMethod")
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

    public void initialize() { }

    @Override
    public void update(NotificationUpdate notification) throws RemoteException { }

    @SuppressWarnings("EmptyMethod")
    @FXML
    private void onPublishButtonClick() { }

    @SuppressWarnings("EmptyMethod")
    @FXML
    private void onSaveButtonClick() { }

    @SuppressWarnings("EmptyMethod")
    @FXML
    private void onMembersButtonClick() { }

    @SuppressWarnings("EmptyMethod")
    @FXML
    private void onBasicButtonClick() { }

    @SuppressWarnings("EmptyMethod")
    @FXML
    private void onSessionsButtonClick() { }

    @SuppressWarnings("EmptyMethod")
    @FXML
    private void onSubmissionsButtonClick() { }

}
