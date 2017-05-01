package itemcontroller;

import data.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import manager.StageManager;
import view.ViewType;

/**
 * Name:        ControllerUserItem
 * Effect:      Controls a user item view.
 * Date:        08/04/2017
 * Tested:      False
 *
 * @author      Alexandru Stoica
 * @version     1.1
 */

public class ControllerUserItem
        implements PaginationControllerItemInterface<User> {

    /**
     * Displays the user's location.
     */
    @FXML private Label locationLabel;

    /**
     * Displays the user's real name.
     */
    @FXML private Label nameLabel;

    /**
     * Displays the user's username.
     */
    @FXML private Label usernameLabel;

    /**
     * Effect: The item's root pane.
     */
    @FXML private BorderPane pane;

    /**
     * Effect: The manager allows the item to switch the
     * main scene 'UsersView' to the item's main scene 'UserView'.
     */
    private StageManager manager;

    /**
     * Effect: The item that needs to be displayed inside the view. [UserEntity]
     */
    private User item;

    /**
     * Effect: Sets the element in order to be displayed
     * and builds the view ones the item is set.
     * @param element: The required element for item's view
     */
    @Override
    public void setElement(User element) {
        this.item = element;
        build();
    }

    /**
     * Effect: Loads the UserView based on item [UserEntity]
     * from the current object controller.
     */
    @FXML
    public void onItemClick() {
        manager.switchScene(ViewType.USER, item);
    }

    /**
     * Effect: Builds the view with the set item.
     */
    private void build() {
        locationLabel.setText(item.getLocation());
        nameLabel.setText(item.getName());
        usernameLabel.setText("@" + item.getUsername());
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
