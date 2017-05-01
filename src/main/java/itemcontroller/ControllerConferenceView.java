package itemcontroller;

import controller.ControllerInterface;
import data.Conference;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Name:        ControllerConferencesView
 * Effect:      Displays a conference in a main view and shows
 *              all it's details to the application's user.
 * Date:        02/04/2017
 * Tested:      False
 *
 * @author      Alexandru Stoica
 * @version     1.0
 */

@Component
public class ControllerConferenceView
        implements ControllerInterface, ControllerItemInterface<Conference> {

    @FXML private Label nameLabel;

    @SuppressWarnings("all")
    private Conference element;

    @SuppressWarnings("all")
    private StageManager manager;

    @Autowired @Lazy
    public ControllerConferenceView(StageManager manager) {
        this.manager = manager;
    }

    /**
     * Effect: Builds the pagination and it's data.
     */
    @Override
    public void initialize() { }

    /**
     * Sets the element in the controller's view
     * and builds the UI data based on the conference's attributes.
     * @param element The view's element.
     */
    @Override
    public void setElement(Conference element) {
        this.element = element;
        nameLabel.setText(element.getName());
    }
}
