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
 * Date:        05/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

@Component
public class ControllerTopBar implements ControllerInterface {

    @SuppressWarnings("all")
    @FXML private Label usernameLabel;

    @SuppressWarnings("all")
    private StageManager manager;

    @Autowired @Lazy
    public ControllerTopBar(StageManager manager) {
        this.manager = manager;
    }

}
