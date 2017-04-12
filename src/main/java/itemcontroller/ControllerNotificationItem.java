package itemcontroller;

import domain.Notification;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import manager.StageManager;
import view.GradientGenerator;

/**
 * Name:        ControllerNotificationItem
 * Effect:      Controls a notification item view.
 * Date:        08/04/2017
 * Tested:      False
 *
 * @author      Alexandru Stoica
 * @version     1.1
 */

public class ControllerNotificationItem
        implements PaginationControllerItemInterface<Notification> {

    /**
     * Displays the notification's text.
     */
    @FXML private Label textLabel;

    /**
     * Button to perform payment action.
     */
    @FXML private Button payButton;

    /**
     * Effect: The item's root pane.
     */
    @FXML private BorderPane pane;

    /**
     * Support for future development.
     */
    @SuppressWarnings("all")
    private StageManager manager;

    /**
     * Effect: Defines the background of the item. (gradient)
     */
    @FXML private Button background;

    /**
     * Effect: Generates a gradient color for our element.
     */
    private GradientGenerator generator = new GradientGenerator();

    /**
     * Effect: The item that needs to be displayed inside the view. [Notification]
     */
    private Notification item;

    /**
     * Effect: Sets the element in order to be displayed
     * and builds the view ones the item is set.
     * @param element: The required element for item's view
     */
    @Override
    public void setElement(Notification element) {
        this.item = element;
        build();
    }

    /**
     * Support for future development.
     */
    @FXML
    @SuppressWarnings("all")
    public void onItemClick() { }

    /**
     * Effect: Builds the view with the set item.
     */
    private void build() {
        if (item.getPayment().equals(Boolean.TRUE)) {   /* hides the payment button */
            payButton.setOpacity(0);                    /* if it's not required */
            payButton.setMaxWidth(0);
            payButton.setMaxHeight(0);
        }
        textLabel.setText(item.getText());
        background.setStyle(String.format("-fx-background-color : %s",
                generator.getGradient().getValue()));   /* sets a random gradient */
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

    /**
     * Effect: Starts the payment process. [In development]
     */
    @FXML public void onPayButtonClick() {
        System.out.println("Pay Button Click");
    }
}
