package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        08/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

@Component
public class ControllerConferenceItem implements ControllerInterface {

    @FXML private Label date;
    @FXML private Label location;
    @FXML private Label acronym;
    @FXML private Label name;

    @SuppressWarnings("all")
    private final StageManager manager;

    @Autowired
    @Lazy
    public ControllerConferenceItem(StageManager manager) {
        this.manager = manager;
    }

    /**
     * Effect: Adds width & height constraints on the
     * background image view in order to be responsive.
     */
    @Override
    public void initialize() {
        date.setText("Tomorrow 8:00 PM");
        location.setText("London, UK");
        acronym.setText("INSERT");
        name.setText("2017 2nd International Conference on Information Systems Engineering");
    }

}
