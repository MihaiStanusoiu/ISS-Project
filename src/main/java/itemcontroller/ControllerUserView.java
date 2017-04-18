package itemcontroller;

import controller.ControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Name:        ControllerUserView
 * Effect:      Displays a user in a main view and shows
 *              all it's details to the application's user.
 * Date:        02/04/2017
 * Tested:      False
 *
 * @author      Alexandru Stoica
 * @version     1.0
 */

@Component
public class ControllerUserView<T>
        implements ControllerInterface, ControllerItemInterface<T> {

    /**
     * Displays the user's real name.
     */
    @FXML private Label nameLabel;

    /**
     * The base user of the view.
     */
    @SuppressWarnings("all")
    private T element;

    @SuppressWarnings("all")
    private StageManager manager;

    @Autowired @Lazy
    public ControllerUserView(StageManager manager) {
        this.manager = manager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize() { }

    /**
     * {@inheritDoc}
     * @param element The view's element.
     */
    @Override
    public void setElement(T element) {
      //  this.element = element;
      //  nameLabel.setText(element.getName());
    }

}
