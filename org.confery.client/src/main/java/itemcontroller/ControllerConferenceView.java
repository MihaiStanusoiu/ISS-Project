package itemcontroller;

import controller.ControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import transferable.Conference;

/**
 * @author      Alexandru Stoica
 * @version     1.0
 */

@Lazy
@Component
public class ControllerConferenceView
        implements ControllerInterface, ControllerItemInterface<Conference> {

    @FXML private Label nameLabel;

    private Conference element;

    @Lazy
    @Autowired
    private StageManager manager;

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
