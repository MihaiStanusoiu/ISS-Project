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
public class ControllerConferencesView implements ControllerInterface {

    @SuppressWarnings("all")
    private StageManager manager;

    @Autowired @Lazy
    public ControllerConferencesView(StageManager manager) {
        this.manager = manager;
    }

}
