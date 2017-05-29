package pagination;

import itemcontroller.PaginationControllerItemInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Pagination;
import javafx.scene.layout.Pane;
import manager.StageManager;
import view.ViewType;

import java.util.ArrayList;
import java.util.Collection;

import static utils.Conditional.basedOn;

/**
 * Special builder for pagination manager. [based on Builder Pattern]
 * <p>
 * Checks if all the manager's requirements are respected
 * and creates the wanted configuration for the view's pagination.
 * <p>
 * T: The domain element
 * E: The item's view controller
 * U: The pagination's pane (the page's pane)
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

@SuppressWarnings("SameParameterValue")
public class PaginationBuilder<T, E extends PaginationControllerItemInterface<T>, U extends Pane> {

    private ViewType view;
    private ObservableList<T> elements;
    private Integer rows = 2;
    private Integer columns = 4;
    private Pagination pagination;
    private StageManager stageManager;

    /**
     * Sets the item's view and returns the builder for later chaining.
     *
     * @param view The item's view.
     * @return the builder for later chaining.
     */
    public PaginationBuilder setView(ViewType view) {
        this.view = view;
        return this;
    }

    /**
     * Effect: Sets the pagination's elements and returns the builder for later chaining.
     *
     * @param elements The pagination's elements
     * @return the builder for later chaining.
     */
    @SuppressWarnings("unused")
    public PaginationBuilder setElements(ArrayList<T> elements) {
        this.elements = FXCollections.observableArrayList(elements);
        return this;
    }

    /**
     * Effect: Sets the pagination's elements and returns the builder for later chaining.
     *
     * @param elements The pagination's elements
     * @return the builder for later chaining.
     */
    @SuppressWarnings("unused")
    public PaginationBuilder setElements(Collection<T> elements) {
        this.elements.addAll(elements);
        return this;
    }

    public PaginationBuilder setElements(ObservableList<T> elements) {
        this.elements = elements;
        return this;
    }

    /**
     * Effect: Sets the pagination's number of rows and returns the builder for later chaining.
     *
     * @param rows The pagination's number of rows.
     * @return the builder for later chaining.
     */
    public PaginationBuilder setRows(Integer rows) {
        this.rows = rows;
        return this;
    }

    /**
     * Effect: Sets the pagination's number of columns and returns the builder for later chaining.
     *
     * @param columns The pagination's number of columns.
     * @return the builder for later chaining.
     */
    public PaginationBuilder setColumns(Integer columns) {
        this.columns = columns;
        return this;
    }

    /**
     * Effect: Sets the pagination's stage manager and returns the builder for later chaining.
     *
     * @param stageManager The main view's stage manager.
     * @return the builder for later chaining.
     */
    public PaginationBuilder setStageManager(StageManager stageManager) {
        this.stageManager = stageManager;
        return this;
    }

    /**
     * Effect: Sets the main view's pagination and returns the builder for later chaining.
     *
     * @param pagination The main view's pagination.
     * @return the builder for later chaining.
     */
    public PaginationBuilder setPagination(Pagination pagination) {
        this.pagination = pagination;
        return this;
    }

    @SuppressWarnings("SameReturnValue")
    private Boolean checkViewTypeState() {
        basedOn(view != null).orThrow(new IllegalStateException("Missing Pagination View!"));
        return Boolean.TRUE;
    }

    @SuppressWarnings("SameReturnValue")
    private Boolean checkStageManagerState() {
        basedOn(stageManager != null).orThrow(new IllegalStateException("Missing Stage Manager!"));
        return Boolean.TRUE;
    }

    @SuppressWarnings("SameReturnValue")
    private Boolean checkPaginationState() {
        basedOn(pagination != null).orThrow(new IllegalStateException("Missing Pagination!"));
        return Boolean.TRUE;
    }

    private Boolean checkCurrentState() {
        return checkViewTypeState() && checkStageManagerState() && checkPaginationState();
    }

    @SuppressWarnings("SameReturnValue")
    private Boolean checkCurrentPaginationManager(PaginationManagerInterface<T, E, U> manager) {
        basedOn(manager != null).orThrow(new IllegalStateException("Pane not supported yet!"));
        return Boolean.TRUE;
    }

    private Pagination setUpAndBuild(PaginationManagerInterface<T, E, U> manager) {
        manager.setElements(this.elements);
        manager.setStageManager(stageManager);
        manager.setView(this.view);
        return manager.buildPagination(pagination);
    }

    /**
     * Effect: Builds the pagination.
     *
     * @param paneType The base pane class for each page. [example: GridPane.class]
     * @return the wanted pagination.
     * @throws IllegalStateException If you forget an important element for the pagination's manager.
     */
    public <Y> Pagination build(Class<Y> paneType) throws IllegalStateException {
        PaginationManagerInterface<T, E, U> manager = checkCurrentState() ?
                PaginationFactory.getPagination(paneType, this.rows, this.columns) : null;
        return checkCurrentPaginationManager(manager) ? setUpAndBuild(manager) : null;
    }
}
