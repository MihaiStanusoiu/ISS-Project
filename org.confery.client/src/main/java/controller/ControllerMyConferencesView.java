package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import listener.ListenerHelper;
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
public class ControllerMyConferencesView
        implements ControllerInterface {

    @FXML private TextField searchTextField;
    @FXML private Pagination pagination;

    @Lazy
    @Autowired
    private StageManager manager;

    @Lazy
    @Autowired
    private ListenerHelper listener;

    /**
     * Effect: Builds the pagination and it's data.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void initialize() {
        // TODO : Add pagination view for my conferences.
    }

    /**
     * Effect: Search function for conferences.
     * @implNote status: Unavailable at the moment.
     */
    @FXML private void onSearchButtonClick() {
        String searchTerm = searchTextField.getText();
        System.out.println(searchTerm);
    }

}
