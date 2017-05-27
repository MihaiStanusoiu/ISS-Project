package itemcontroller;

import controller.ControllerInterface;
import domain.UserEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author      Alexandru Stoica
 * @version     1.0
 */

@Lazy
@Component
public class ControllerUserView
        implements ControllerInterface, ControllerItemInterface<UserEntity> {

    @FXML private Label nameLabel;
    private UserEntity element;

    @Lazy
    @Autowired
    private StageManager manager;

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
    public void setElement(UserEntity element) {
        this.element = element;
        nameLabel.setText(element.getName());
    }

}
