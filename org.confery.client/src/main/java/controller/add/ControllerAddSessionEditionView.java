package controller.add;

import controller.main.ControllerInterface;
import itemcontroller.ControllerItemInterface;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import list.ListViewBuilder;
import manager.StageManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import transfarable.Session;
import utils.ConferenceContext;
import view.Icon;
import view.ViewType;

import java.util.Date;
import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class ControllerAddSessionEditionView
        implements ControllerInterface, ControllerItemInterface<ConferenceContext> {

    @SuppressWarnings("unused")
    private static Logger logger;

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
    private ConferenceContext context;

    @Override
    public void setElement(ConferenceContext element) {
        this.context = element;
        build();
    }

    private void build() {
        conferenceNameLabel.setText(context.getConference().getName());
        // TODO Add Active User
        sessions = context.getEditionContext().getSessions();
        setUpListViews();
    }

    private void setUpListViews() {
        sessionsListView = new ListViewBuilder<>(sessionsListView)
                .setIcon(Icon.CLOSE)
                .visibleText(Session::getName)
                .setAction(List::remove, context.getEditionContext().getSessions())
                .build();
        sessionsListView.setItems(sessions);
    }

    public void initialize() {
        logger = Logger.getLogger(ControllerAddSessionEditionView.class);
    }

    @FXML
    private void onAddSessionButtonClick() {
        String name = sessionTextField.getText();
        Session session = new Session(0, name, new Date(), new Date(),
                context.getEditionContext().getEdition().getLocation(), "", 100);
        // TODO Improve Session Constructor
        context.getEditionContext().addSession(session);
    }


    @FXML
    private void onPublishButtonClick() {
        // TODO
    }

    @FXML
    private void onBasicButtonClick() {
        manager.switchScene(ViewType.ADD_EDITION, context);
    }

    @FXML
    private void onMembersButtonClick() {
        manager.switchScene(ViewType.MEMBERS_CONFERENCE, context);
    }

    @FXML
    private void onBackButtonClick() {
        manager.switchScene(ViewType.ADD_CONFERENCE, context);
    }

    @FXML
    private void onSubmissionsButtonClick() {
        // TODO
    }

}
