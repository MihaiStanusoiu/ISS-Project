package pagination;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

class PaginationFactory {

    private static PaginationManager getGridPagination(Integer rows, Integer columns) {
        return new PaginationManager(rows, columns);
    }

    /**
     * @param type    The pane's class
     * @param rows    The number of rows used in pagination (for GridPane)
     * @param columns The number of columns used in pagination (for GridPane)

     * @return The wanted manager for your pane.
     */
    static PaginationManagerInterface getPagination(Class<?> type, Integer rows, Integer columns) {
        switch (type.getName()) {
            case "javafx.scene.layout.GridPane":
                return getGridPagination(rows, columns);
            default:
                throw new IllegalStateException("Pane not supported yet!");
        }
    }
}
