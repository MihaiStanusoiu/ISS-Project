package view;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface ViewTypeInterface {

    /**
     * Effect: Returns the title of the scene.
     */
    default String getTitle() {
        return ViewType.getDataFromBundle("application.title");
    }

    /**
     * Effect: Returns the fxml file's path for the scene.
     */
    String getFXMLFile();

}
