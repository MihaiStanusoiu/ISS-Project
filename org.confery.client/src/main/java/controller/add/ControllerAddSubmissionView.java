package controller.add;

import controller.main.ControllerInterface;
import itemcontroller.ControllerItemInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import list.ListViewBuilder;
import manager.StageManager;
import method.SimpleMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import service.*;
import transfarable.*;
import view.Icon;
import view.ViewType;

import java.rmi.RemoteException;
import java.util.List;

import static utils.Conditional.basedOn;
import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
@SuppressWarnings("EmptyMethod")
public class ControllerAddSubmissionView
        implements ControllerInterface, ControllerItemInterface<Edition> {

    @Lazy
    @Autowired
    private StageManager manager;

    @Lazy
    @Autowired
    private CollectionService collectionService;

    private static Logger logger;

    private SimpleMethod<RemoteException> handler;

    private SubmissionService submissionService;
    private EditionService editionService;
    private AuthenticationService authenticationService;

    private UserService userService;
    private TopicService topicService;
    private TagService tagService;

    private Edition edition;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField abstractTextField;

    @FXML
    private TextField fullPaperTextField;


    public void initialize() {
        logger = Logger.getLogger(ControllerAddSubmissionView.class);
        handler = exception -> logger.error(exception.getCause());
        submissionService = runFunction(collectionService::submissionService).orHandle(handler);
        userService = runFunction(collectionService::userService).orHandle(handler);
        editionService = runFunction(collectionService::editionService).orHandle(handler);
        authenticationService = runFunction(collectionService::authenticationService).orHandle(handler);
    }

    @Override
    public void setElement(Edition element) {
        edition = element;
        build();
    }

    private ObservableList<User> authors;
    private ObservableList<Topic> topics;
    private ObservableList<Tag> tags;

    private void build() {
        // TODO Setup lists
        authors = FXCollections.observableArrayList();
        tags = FXCollections.observableArrayList();
        topics = FXCollections.observableArrayList();
        authorsListView.setItems(authors);
        topicsListView.setItems(topics);
        tagsListView.setItems(tags);
        authorsListView = new ListViewBuilder<>(authorsListView)
                .textProvider(User::getName)
                .setAction((list, item) -> ((List)list).remove(item), authors)
                .setColor("red")
                .setIcon(Icon.CLOSE)
                .build();
        tagsListView = new ListViewBuilder<>(tagsListView)
                .textProvider(Tag::getWord)
                .setAction((list, item) -> ((List)list).remove(item), tags)
                .setColor("red")
                .setIcon(Icon.CLOSE)
                .build();
        topicsListView = new ListViewBuilder<>(topicsListView)
                .textProvider(Topic::getWord)
                .setAction((list, item) -> ((List)list).remove(item), topics)
                .setColor("red")
                .setIcon(Icon.CLOSE)
                .build();
    }

    @FXML
    private void onSubmitButtonClick() {
        User owner = runFunction(authenticationService::getActiveUser).orHandle(handler);
        basedOn(!owner.getId().equals(0))
                .runTrue(this::ableToSubmit, owner)
                .runFalse(this::unableToSubmit);
    }

    private void ableToSubmit(User owner){
        // TODO IMPROVE
        Integer id = runFunction(submissionService::add, getCurrentSubmission()).orHandle(handler);
        Submission submission = runFunction(submissionService::getElementById, id).orHandle(handler);
        runFunction(submissionService::addOwnerTo, submission, owner);
        topicService = runFunction(collectionService::topicService).orHandle(handler);
        tagService = runFunction(collectionService::tagService).orHandle(handler);
        topics.forEach(topic -> {
            Integer idTopic = runFunction(topicService::add, topic).orHandle(handler);
            Topic item = runFunction(topicService::getElementById, idTopic).orHandle(handler);
            runFunction(submissionService::addTopicTo, submission, item).orHandle(handler);
        });
        tags.forEach(tag -> {
            Integer idTag = runFunction(tagService::add, tag).orHandle(handler);
            Tag item = runFunction(tagService::getElementById, idTag).orHandle(handler);
            runFunction(submissionService::addTagTo, submission, item).orHandle(handler);
        });
        authors.forEach(author -> runFunction(submissionService::addAuthorTo, submission, author).orHandle(handler));
        runFunction(editionService::addSubmissionToEdition, edition, submission).orHandle(handler);
        manager.switchScene(ViewType.SUBMISSIONS);
    }

    private void unableToSubmit() {
        logger.error("You can't send a submission");
    }

    @FXML
    private ListView<User> authorsListView;

    @FXML
    private ListView<Topic> topicsListView;

    @FXML
    private ListView<Tag> tagsListView;


    private Submission getCurrentSubmission() {
        return new Submission(0, nameTextField.getText(), "NOT_REVIEWED",
                abstractTextField.getText(), fullPaperTextField.getText(), Boolean.FALSE);
    }

    @FXML
    private TextField topicTextField;

    @FXML
    private TextField tagTextField;

    @FXML
    private TextField authorTextField;

    @FXML
    private void onAddTopicButtonClick() {
        topics.add(new Topic(0, topicTextField.getText()));
    }

    @FXML
    private void onAddAuthorButtonClick() {
        User author = runFunction(userService::findUserByUsername, authorTextField.getText()).orHandle(handler);
        authors.add(author);
    }

    @FXML
    private void onAddTagButtonClick() {
        tags.add(new Tag(0, tagTextField.getText()));
    }

}
