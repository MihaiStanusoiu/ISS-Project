package controller;

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
public class ControllerUsersView implements ControllerInterface {

    @SuppressWarnings("all")
    private StageManager manager;

    @Autowired @Lazy
    public ControllerUsersView(StageManager manager) {
        this.manager = manager;
    }

}