package itemcontroller;

import domain.ConferenceEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import view.GradientGenerator;
import view.ViewType;

/**
 * @author      Alexandru Stoica
 * @version     1.1
 */

@Lazy
@Component
public class ControllerConferenceItem
        implements PaginationControllerItemInterface<ConferenceEntity> {

    /**
     * Displays the conference's starting date.
     */
    @FXML private Label dateLabel;

    /**
     * Displays the conference's location.
     */

    @FXML private Label locationLabel;

    /**
     * Displays the conference's acronym.
     */

    @FXML private Label acronymLabel;

    /**
     * Displays the conference's name.
     */
    @FXML private Label nameLabel;

    /**
     * The item's root pane.
     */
    @FXML private BorderPane pane;

    /**
     * The manager allows the item to switch the main scene
     * 'ConferencesView' to the item's main scene 'ConferenceView'.
     */
    @Lazy
    @Autowired
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
     * Effect: The item that needs to be displayed inside the view. [ConferenceEntity]
     */
    private ConferenceEntity item;

    /**
     * Effect: Sets the element in order to be displayed
     * and builds the view ones the item is set.
     * @param element: The required element for item's view
     */
    @Override
    public void setElement(ConferenceEntity element) {
        this.item = element;
        build();
    }

    /**
     * Effect: Loads the ConferenceView based on item [ConferenceEntity]
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
        dateLabel.setText(item.getLatestEdition().getStartDate().toString());
        locationLabel.setText(item.getLatestEdition().getLocation());
        acronymLabel.setText(item.getAcronym());
        nameLabel.setText(item.getName());
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

}
