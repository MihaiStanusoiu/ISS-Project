package pagination;

import itemcontroller.PaginationControllerItemInterface;
import javafx.scene.layout.Pane;

/**
 * Name:        PaginationFactory
 * Effect:      Factory for pagination managers based on the pane's class.
 *
 * @author Alexandru Stoica
 * @version 1.0
 * @apiNote Needs improvements.
 * Date:        14/04/2017
 * Tested:      False
 */

class PaginationFactory {

    /**
     * @param type    The pane's class
     * @param rows    The number of rows used in pagination (for GridPane)
     * @param columns The number of columns used in pagination (for GridPane)
     * @param <T>     The domain object class
     * @param <E>     The item's view controller class
     * @param <U>     The page's pane class
     * @return The wanted manager for your pane.
     */
    @SuppressWarnings("unchecked")
    static <T, E extends PaginationControllerItemInterface<T>, U extends Pane> PaginationManagerInterface<T, E, U>
    getPagination(Class type, Integer rows, Integer columns) {
        switch (type.getName()) {
            case "javafx.scene.layout.GridPane":
                return new PaginationManager(rows, columns);
            default:
                return null;
        }
    }

}
