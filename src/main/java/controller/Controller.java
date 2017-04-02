package controller;

import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Name:        Controller
 * Effect:      The main controller, just for testing.
 * Date:        02/04/2017
 * Tested:      False
 * @implNote    To delete in the next iteration.
 * @author      Alexandru Stoica
 * @version     1.0
 */

@Component
public class Controller implements ControllerInterface {

    private final StageManager manager;

    @Autowired
    @Lazy
    public Controller(StageManager manager) {
        this.manager = manager;
    }

}
