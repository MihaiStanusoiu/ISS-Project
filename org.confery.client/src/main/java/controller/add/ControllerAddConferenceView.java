package controller.add;

import controller.main.ControllerInterface;
import itemcontroller.ControllerItemInterface;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import list.ListViewBuilder;
import manager.StageManager;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import transfarable.Conference;
import transfarable.Edition;
import utils.ConferenceContext;
import utils.EditionContext;
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
@SuppressWarnings("EmptyMethod")
public class ControllerAddConferenceView
        implements ControllerInterface, ControllerItemInterface<ConferenceContext> {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField acronymTextField;

    @FXML
    private ListView<EditionContext> editionListView;

    @Lazy
    @Autowired
    private StageManager manager;

    private ConferenceContext context;

    public void initialize() { }

    @Override
    public void setElement(ConferenceContext element) {
        context = element;
        build();
    }

    private void build() {
        editionListView = new ListViewBuilder<>(editionListView)
                .setIcon(Icon.CLOSE)
                .visibleText(context -> context.getEdition().getLocation())
                .setAction(List::remove,  context.getEditionContexts())
                .build();
        nameTextField.setText(context.getConference().getName());
        acronymTextField.setText(context.getConference().getAcronym());
        editionListView.setItems(context.getEditionContexts());
        editionListView.setOnMouseClicked(event -> onEditionItemClick());
    }

    private void onEditionItemClick() {
        context.updateEditionContext(getSelectedEdition());
        manager.switchScene(ViewType.ADD_EDITION, context);
    }

    private EditionContext getSelectedEdition() {
        return editionListView.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void onPublishButtonClick() {
        Conference conference = getCurrentConference();
        // TODO Get 'current' user from system
        // TODO Add 'conference' to database
        // TODO Add 'conference' to user [where user is in database]
    }

    @FXML
    private void onAddEditionButtonClick() {
        context.setConference(getCurrentConference());
        Edition selectedEdition = getDefaultEdition();
        context.addEdition(selectedEdition);
        manager.switchScene(ViewType.ADD_EDITION, context);
    }

    private Edition getDefaultEdition() {
        return new Edition(0, new Date(), new Date(), "", "",
                new Date(), new Date(), new Date(), new Date());
    }

    @NotNull
    private Conference getCurrentConference() {
        return new Conference(nameTextField.getText(), acronymTextField.getText());
    }

}
