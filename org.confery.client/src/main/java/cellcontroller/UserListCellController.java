package cellcontroller;

import itemcontroller.PaginationControllerItemInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import manager.StageManager;
import transferable.User;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class UserListCellController
        implements PaginationControllerItemInterface<User> {

    @FXML private BorderPane pane;
    @FXML private Label nameLabel;

    private User user;
    private StageManager manager;

    @Override
    public void setElement(User element) {
        user = element;
        build();
    }

    private void build() {
        nameLabel.setText(user.getName());
    }

    @Override
    public BorderPane getPane() {
        return pane;
    }

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