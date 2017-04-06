package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Name:        ControllerUsersView
 * Effect:      Lists all the available users.
 * Date:        05/04/2017
 * Tested:      False
 *
 * @author      Alexandru Stoica
 * @version     1.0
 */

@Component
public class ControllerUsersView implements ControllerInterface {

    @SuppressWarnings("all")
    private StageManager manager;

    @FXML private TextField searchTextField;

    @Autowired @Lazy
    public ControllerUsersView(StageManager manager) {
        this.manager = manager;
    }

    /**
     * Effect: Search function for users.
     * @implNote status: Unavailable at the moment.
     */
    @FXML private void onSearchButtonClick() {
        String searchTerm = searchTextField.getText();
        System.out.println(searchTerm);
    }

}