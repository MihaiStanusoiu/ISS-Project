package itemcontroller;

import javafx.scene.layout.Pane;
import manager.StageManager;

/**
 * Name:        PaginationControllerItemInterface
 *
 * Effect:      Any view-controller that controls an item view (example: ConferenceItem)
 *              needs to offer a couple of functions to PaginationBuilder in order for the builder
 *              to work and the showcase the items in order in a grid-pane
 *
 *              Any view-controller that controls an item view must implement those methods
 *              in other to merge with the PaginationBuilder.
 *
 *              Similar Example: TableView needs a TableCell class in order to work.
 *
 *              Update 1.1: Support for Stage Manager
 *
 * Date:        08/04/2017
 * Tested:      False
 *
 * @author      Alexandru Stoica
 * @version     1.1
 */


public interface PaginationControllerItemInterface<T> {

    /**
     * Effect: Adds an element to the item's view.
     *
     * <p>The element is used to populate the date the item's view,
     * but can also be passed forward from the item's view to
     * another view.</p>
     *
     * @param element: The required element for item's view
     */
    void setElement(T element);

    /**
     * Effect: Returns the view's main pane in order for the
     * PaginationBuilder to add the main pane to the page's grid-pane.
     * @return [Pane]: The view's main pane.
     */
    Pane getPane();

    /**
     * Effect: The item controller may need the stage manager
     * in order to switch the main scene of the application.
     * <p>
     *      If the displayed item is actually a link/button to a view
     *      that shows details about the item, than this stage manager
     *      will allow the item's controller to switch the main scene with a new one.
     * </p>
     * @param stageManager The main view's stage manager.
     */
    void setStageManager(StageManager stageManager);

    /**
     * @return The main pane's width
     * or the main value of a double (by default)
     */
    @SuppressWarnings("unused")
    default double getWidth() {
        return Double.MIN_VALUE;
    }

    /**
     * @return The main pane's height
     * or the main value of a double (by default)
     */
    @SuppressWarnings("unused")
    default double getHeight() {
        return Double.MIN_VALUE;
    }

}
