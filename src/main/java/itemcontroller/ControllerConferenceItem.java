package itemcontroller;

import domain.Conference;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import manager.StageManager;
import view.ViewType;

/**
 * Name:        ControllerConferenceItem
 * Effect:      Controls a conference item view.
 * Date:        08/04/2017
 * Tested:      False
 *
 * @author      Alexandru Stoica
 * @version     1.1
 */

public class ControllerConferenceItem
        implements PaginationControllerItemInterface<Conference> {

    /**
     * Effect: Displays the conference's starting date.
     */

    @FXML private Label dateLabel;
    /**
     * Effect: Displays the conference's location.
     */

    @FXML private Label locationLabel;
    /**
     * Effect: Displays the conference's acronym.
     */

    @FXML private Label acronymLabel;
    /**
     * Effect: Displays the conference's name.
     */
    @FXML private Label nameLabel;

    /**
     * Effect: The item's root pane.
     */
    @FXML private BorderPane pane;

    /**
     * Effect: The manager allows the item to switch the
     * main scene 'ConferencesView' to the item's main scene 'ConferenceView'.
     */
    private StageManager manager;

    /**
     * Effect: The item that needs to be displayed inside the view. [Conference]
     */
    private Conference item;

    /**
     * Effect: Sets the element in order to be displayed
     * and builds the view ones the item is set.
     * @param element: The required element for item's view
     */
    @Override
    public void setElement(Conference element) {
        this.item = element;
        build();
    }

    /**
     * Effect: Loads the ConferenceView based on item [Conference]
     * from the current object controller.
     */
    @FXML
    public void onItemClick() {
        manager.switchScene(ViewType.CONFERENCE, item);
    }

    /**
     * Effect: Builds the view with the set item.
     */
    private void build() {
        dateLabel.setText(item.getStartDate().toString());
        locationLabel.setText(item.getLocation());
        acronymLabel.setText(item.getAcronym());
        nameLabel.setText(item.getName());
    }

    /**
     * @return [Pane] the root pane of the item view.
     */
    @Override
    public Pane getPane() {
        return pane;
    }

    /**
     * Effect: Adds support for scene switching.
     * @param stageManager The main view's stage manager.
     */
    @Override
    public void setStageManager(StageManager stageManager) {
        manager = stageManager;
    }

    /**
     * @return The width of the main pane [double]
     */
    @Override
    public double getWidth() {
        return pane.getWidth();
    }

    /**
     * @return The height of the main pane [double]
     */
    @Override
    public double getHeight() {
        return pane.getHeight();
    }

}
