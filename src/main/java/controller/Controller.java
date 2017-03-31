package controller;

import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
public class Controller {

    private final StageManager manager;

    @Autowired
    @Lazy
    public Controller(StageManager manager) {
        this.manager = manager;
    }

}
