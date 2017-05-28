package pagination;

import itemcontroller.PaginationControllerItemInterface;
import javafx.scene.control.Pagination;
import javafx.scene.layout.Pane;
import manager.StageManager;
import view.ViewType;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Allows alternatives to the grid-view used in the original/main pagination builder.
 * <p>
 * The pagination is organized in a U [Pane].
 * </p>
 * <p>
 * T : The data's type (example: ConferenceEntity -- from domain package)
 * E : The view-controller's type, the controller that manages the item's view.
 * U : The pane used to display a page.
 * </p>
 * @author Alexandru Stoica
 * @version 1.0
 */

@SuppressWarnings("unused")
public interface PaginationManagerInterface<T, E extends PaginationControllerItemInterface<T>, U extends Pane> {

    /**
     * Effect: Defines the item's view.
     *
     * @param view The item's view.
     */
    void setView(ViewType view);

    /**
     * Effect: The items in the pagination view may require
     * the stage manager in order to switch the scene.
     *
     * @param stageManager The main view's stage manager;
     */
    void setStageManager(StageManager stageManager);

    /**
     * Effect: Sets the data based on an ArrayList.
     *
     * @param elements: The data we need to set in the pagination.
     */
    void setElements(ArrayList<T> elements);

    /**
     * Effect: Sets the data based on a Collection.
     *
     * @param elements: The data we need to set in the pagination.
     */
    void setElements(Collection<T> elements);

    /**
     * Effect: Sets the data based on an generic list of items.
     *
     * @param elements: The data we need to set in the pagination.
     */
    void setElements(T[] elements);

    /**
     * Builds the view's pagination.
     *
     * @param pagination The view's pagination.
     * @return The pagination with the wanted configuration for pages.
     */
    Pagination buildPagination(Pagination pagination);

    /**
     * @return [Integer] The number of items per page.
     */
    Integer getItemsPerPage();

    /**
     * Effect: Creates the pagination's page based on index.
     *
     * @param pageIndex The page's index.
     * @return [U] The page's pane.
     */
    U createPage(Integer pageIndex);

}
