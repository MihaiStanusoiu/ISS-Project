package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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

    @FXML private Button recentButton;
    @FXML private Button popularButton;
    @FXML private TextField searchTextField;

    @SuppressWarnings("all")
    private StageManager manager;

    @Autowired @Lazy
    public ControllerConferencesView(StageManager manager) {
        this.manager = manager;
    }

    @Override
    public void initialize() { }

    /**
     * Effect: Sorts conferences by popularity
     * based on the number of members that participate at the conference.
     * @implNote status: Unavailable at the moment.
     */
    @FXML private void onPopularButtonClick() {
        popularButton.setOpacity(1);
        recentButton.setOpacity(0.5);
    }

    /**
     * Effect: Sorts conferences by date.
     * @implNote status: Unavailable at the moment.
     */
    @FXML private void onRecentButtonClick() {
        popularButton.setOpacity(0.5);
        recentButton.setOpacity(1);
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
