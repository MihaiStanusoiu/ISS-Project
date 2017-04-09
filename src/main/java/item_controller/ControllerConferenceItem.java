package item_controller;

import domain.Conference;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * Name:        ControllerConferenceItem
 * Effect:      Controls a conference item view.
 * Date:        08/04/2017
 * Tested:      False
 *
 * @author      Alexandru Stoica
 * @version     1.0
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

}
