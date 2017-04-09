package item_controller;

import javafx.scene.layout.Pane;

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
 * Date:        08/04/2017
 * Tested:      False
 *
 * @author      Alexandru Stoica
 * @version     1.0
 */


public interface PaginationControllerItemInterface<T> {

    /**
     * Effect: Adds an element to the item's view.
     *
     * <p>The element is used to populate the date the item's view,
     * but can also be passed forword from the item's view to
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

}
