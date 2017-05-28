package item.pagination.controller;

import itemcontroller.PaginationControllerItemInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import transfarable.Submission;
import view.ViewType;

/**
 * @author      Alexandru Stoica
 * @version     1.1
 */

@Lazy
@Component
public class ControllerPaginationSubmissionItem
        implements PaginationControllerItemInterface<Submission> {

    @FXML private Label acronymLabel;
    @FXML private Label statusLabel;
    @FXML private Label nameLabel;
    @FXML private BorderPane pane;
    @FXML private Pane statusIndicator;

    /**
     * Effect: The manager allows the item to switch the
     * main scene 'UsersView' to the item's main scene 'UserView'.
     */
    @Lazy
    @Autowired
    private StageManager manager;

    /**
     * Effect: The item that needs to be displayed inside the view. [UserEntity]
     */
    private Submission item;

    /**
     * Effect: Sets the element in order to be displayed
     * and builds the view ones the item is set.
     * @param element: The required element for item's view
     */
    @Override
    public void setElement(Submission element) {
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
        nameLabel.setText(item.getName());
        statusLabel.setText(item.getStatus());
        acronymLabel.setText(item.getName());
        statusIndicator.setStyle(statusIndicator.getStyle() +
                "-fx-background-color: " + getStatusColorValue());
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

    private String getStatusColorValue() {
        return item.getStatus().toLowerCase().equals("reviewed") ? "#40FD8A" : "#FA7A92";
    }
}
