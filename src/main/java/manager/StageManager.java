package manager;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import loader.SpringFXMLLoader;
import view.ViewType;
import java.util.Objects;


/**
 * Name:        StageManager
 * Effect:      Manages switching scenes on the primary stage.
 * Date:        31/03/2017
 * Tested:      False
 * @author      Alexandru Stoica
 * @version     1.0
 */


public class StageManager {

    private final Stage primaryStage;           // the application's primary stage
    private final SpringFXMLLoader loader;      // the fxml loader with DI

    /**
     * Effect: Manages the load process of the scenes (fxml files).
     * @param loader: The fxml loader with DI.
     * @param stage: The stage of the FX Application.
     */
    public StageManager(SpringFXMLLoader loader, Stage stage) {
        this.loader = loader;
        this.primaryStage = stage;
    }

    /**
     * Effect: Switches the current scene of the application with another scene provided by type.
     * @param type: The view's type (provides information about the title and the fxml file) [ViewType]
     * @see ViewType Documentation.
     */
    public void switchScene(final ViewType type) {
        Parent root = getRootNode(type.getFXMLFile());
        show(root, type.getTitle());
    }

    /**
     * Effect: Shows the new scene.
     * @param root: The parent node of the fxml file. [Parent]
     * @param title: The scene's title. [String]
     */
    private void show(final Parent root, String title) {
        Scene scene = getNewScene(root);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        try {
            primaryStage.show();
        } catch (Exception error) {
            handleErrors(error);
        }
    }

    /**
     * Effect: Uses the root to get the current scene or creates a new one if it's the case.
     * @param root: The parent node of the fxml file. [Parent]
     * @return scene: the new current scene [Scene]
     */
    private Scene getNewScene(Parent root) {
        Scene scene = primaryStage.getScene();
        if (scene == null) {
            scene = new Scene(root);
        }
        scene.setRoot(root);
        return scene;
    }

    /**
     * Effect: Loads the provided fxml file using the Spring FXML Loader object.
     * @param fxmlFilePath: the fxml file's path  [String]
     * @return root: the root node of the fxml file [Parent]
     */
    private Parent getRootNode(String fxmlFilePath) {
        Parent root = null;
        try {
            root = loader.load(fxmlFilePath);
            Objects.requireNonNull(root, "The root FXML should not be null!");
        } catch (Exception error) {
            handleErrors(error);
        }
        return root;
    }


    /**
     * Effect: Handle for errors. (that occurred in this class) [open for improvements]
     * @param error: the error that occurred in the loading process. [Exception]
     */
    private void handleErrors(Exception error) {
        System.out.println(error.getMessage());
    }
}
