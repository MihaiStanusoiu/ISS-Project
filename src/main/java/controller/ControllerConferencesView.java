package controller;

import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Name:        ControllerConferencesView
 * Effect:      Lists all the available conferences.
 * Date:        02/04/2017
 * Tested:      False
 *
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
