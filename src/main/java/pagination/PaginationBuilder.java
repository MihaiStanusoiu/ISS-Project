package pagination;

import item_controller.PaginationControllerItemInterface;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import loader.ItemFXMLLoader;
import loader.LoaderException;
import view.ViewType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Name:        PaginationBuilder
 * Effect:      Builds the pagination's pages and manages the data.
 *              <p>
 *                  The pagination is organized in a grid-pane (similar to table-view).
 *                  We load the data in the grid-pane based on the item's view pane.
 *                  The grid-pane is organized in rows and columns, set at initialization.
 *
 *                  T : The data's type (example: Conference -- from domain package)
 *                  E : The view-controller's type, the controller that manages the item's view.
 *              </p>
 * @apiNote     In the next version of the class the number of rows and columns will be changeable.
 * Date:        08/04/2017
 * Tested:      False
 *
 * @author      Alexandru Stoica
 * @version     1.0
 */

public class PaginationBuilder<T, E extends PaginationControllerItemInterface<T>>{

    /**
     * The number of rows in page's grid-pane.
     */
    private final Integer rows;

    /**
     * The number of columns in page's grid-pane.
     */
    private final Integer columns;

    /**
     * The elements displayed in the grid-pane's pagination. [the data]
     */
    private ArrayList<T> elements;

    /**
     * The element's view [the view to display the data]
     */
    private ViewType view;

    /**
     * @param rows The pagination's number of rows.
     * @param columns The pagination's number of columns.
     * @apiNote Currently this is the only place where
     * you can set the number of rows and columns.
     */
    public PaginationBuilder(Integer rows, Integer columns) {
        this.rows = rows;
        this.columns = columns;
        this.elements = new ArrayList<>();
    }

    /**
     * Effect: Defines the item's view.
     * @param view The item's view.
     */
    public void setView(ViewType view) {
        this.view = view;
    }

    /**
     * Effect: Sets the data based on an ArrayList.
     * @param elements: The data we need to set in the pagination.
     */
    public void setElements(ArrayList<T> elements) {
        this.elements = elements;
    }

    /**
     * Effect: Sets the data based on a Collection.
     * @param elements: The data we need to set in the pagination.
     */
    public void setElements(Collection<T> elements) {
        elements.forEach(element -> this.elements.add(element));
    }

    /**
     * Effect: Sets the data based on an generic list of items.
     * @param elements: The data we need to set in the pagination.
     */
    public void setElements(T[] elements) {
        Collections.addAll(this.elements, elements);
    }

    /**
     * @return [Integer] The number of items per page.
     */
    private Integer getItemsPerPage() {
        return rows * columns;
    }

    /**
     * Effect: Sets the pane's column's constraints,
     * in order for the pane to be resizeable.
     * @param pane The page's grid-pane.
     */
    private void setColumnConstraints(GridPane pane) {
        ColumnConstraints columnConstraints =
                new ColumnConstraints(Double.MIN_VALUE, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE);
        columnConstraints.setHgrow(Priority.SOMETIMES);
        for (Integer indexColumn = 0; indexColumn < columns; indexColumn++) {
            pane.getColumnConstraints().add(columnConstraints);
        }
    }

    /**
     * Effect: Sets the pane's row's constraints,
     * in order for the pane to be resizeable.
     * @param pane The page's grid-pane.
     */
    private void setRowConstraints(GridPane pane) {
        RowConstraints rowConstraints =
                new RowConstraints(Double.MIN_VALUE, Control.USE_COMPUTED_SIZE, Double.MAX_VALUE);
        rowConstraints.setVgrow(Priority.ALWAYS);
        for (Integer indexRow = 0; indexRow < rows; indexRow++) {
            pane.getRowConstraints().add(rowConstraints);
        }
    }
    /**
     * Effect: Builds the page's pane and sets it's constraints.
     * @return [GridPane] the page's pane.
     */
    private GridPane getGrid() {
        GridPane pane = new GridPane();
        pane.setMinWidth(Double.MIN_VALUE);
        pane.setMinHeight(Double.MIN_VALUE);
        pane.setPrefWidth(Control.USE_COMPUTED_SIZE);
        pane.setPrefHeight(Control.USE_COMPUTED_SIZE);
        pane.setMaxWidth(Double.MAX_VALUE);
        pane.setMaxHeight(Double.MAX_VALUE);
        setColumnConstraints(pane);
        setRowConstraints(pane);
        return pane;
    }

    /**
     * Effect: Adds an item in the page's pane based on indexes.
     * @param pane The page's pane.
     * @param startingPoint The page's starting point (the first indexPage on the current page)
     * @param indexData The data's index
     * @param indexColumn The column's index
     * @param indexRow The row's index
     */
    private void addItem(GridPane pane,
                         Integer startingPoint,
                         Integer indexData,
                         Integer indexColumn,
                         Integer indexRow) {
        try {
            ItemFXMLLoader<T, E> loader = new ItemFXMLLoader<>(view);
            loader.setElement(elements.get(indexData));
            pane.add(loader.getRootPane(), indexColumn - startingPoint, indexRow - 1);
        } catch (LoaderException error) {
            pane.add(new Label(error.getMessage()), indexColumn - startingPoint, indexRow - 1);
        }
    }

    /**
     * Effect: Creates the pagination's page based on index.
     * @param pageIndex The page's index.
     * @return [GridPane] The page's pane.
     */
    public GridPane createPage(Integer pageIndex) {
        GridPane pane = getGrid();
        Integer startingPoint = pageIndex * getItemsPerPage();
        Integer index = startingPoint;
        for (Integer indexRow = 1; indexRow <= rows; indexRow++) {
            for (Integer indexColumn = startingPoint;
                 indexColumn < startingPoint + columns && index < elements.size();
                 indexColumn++, index++) {
                addItem(pane, startingPoint, index, indexColumn, indexRow);
            }
        }
        return pane;
    }

}
