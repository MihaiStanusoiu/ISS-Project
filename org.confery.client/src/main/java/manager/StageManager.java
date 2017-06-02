package manager;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jdk.nashorn.internal.objects.annotations.Getter;
import loader.SpringFXMLLoader;
import method.SimpleMethod;
import org.apache.log4j.Logger;
import view.ViewType;

import java.io.IOException;
import java.io.Serializable;

import static utils.Try.runFunction;

/**
 * Manages switching scenes on the primary stage.
 *
 * @author Alexandru Stoica
 * @version 1.1
 */

public class StageManager implements Serializable {

    private static Logger logger;
    private SimpleMethod<IOException> handler;
    private SimpleMethod<RuntimeException> explorer;

    private final Stage primaryStage;           // the application's primary stage
    private final SpringFXMLLoader loader;      // the fxml loader with DI
    private ViewType lastVisited;

    @Getter
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Effect: Manages the load process of the scenes (fxml files).
     *
     * @param loader: The fxml loader with DI.
     * @param stage:  The stage of the FX Application.
     */
    public StageManager(SpringFXMLLoader loader, Stage stage) {
        logger = Logger.getLogger(StageManager.class);
        handler = exception -> logger.error(exception.getCause());
        explorer = exception -> logger.error(exception.getCause());
        this.loader = loader;
        this.primaryStage = stage;
    }

    /**
     * Effect: Switches the current scene of the application with another scene provided by type.
     *
     * @param type: The view's type (provides information about the title and the fxml file) [ViewType]
     * @see ViewType Documentation.
     */
    public void switchScene(final ViewType type) {
        Parent root = getRootNode(type.getFXMLFile());
        show(root, type.getTitle());
        lastVisited = type;
    }

    /**
     * Effect: Switches the current scene of the application with another
     * scene provided by type and sets the view's main element.
     *
     * @param type:    The view's type (provides information about the title and the fxml file) [ViewType]
     * @param element: The view's main element [T]
     * @param <T>:     The main element's type.
     */
    public <T> void switchScene(final ViewType type, T element) {
        Parent root = getRootNode(type.getFXMLFile(), element);
        show(root, type.getTitle());
        lastVisited = type;
    }

    public <T> void refresh(T element) {
        switchScene(lastVisited, element);
    }

    public void refresh() {
        switchScene(lastVisited);
    }

    /**
     * Effect: Shows the new scene.
     *
     * @param root:  The parent node of the fxml file. [Parent]
     * @param title: The scene's title. [String]
     */
    private void show(final Parent root, String title) {
        Scene scene = getNewScene(root);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Effect: Uses the root to get the current scene or creates a new one if it's the case.
     *
     * @param root: The parent node of the fxml file. [Parent]
     * @return scene: the new current scene [Scene]
     */
    private Scene getNewScene(Parent root) {
        Scene scene = primaryStage.getScene();
        scene = (scene == null) ? new Scene(root) : scene;
        scene.setRoot(root);
        return scene;
    }

    /**
     * Effect: Loads the provided fxml file using the Spring FXML Loader object.
     *
     * @param fxmlFilePath: the fxml file's path  [String]
     * @return root: the root node of the fxml file [Parent]
     */
    private Parent getRootNode(String fxmlFilePath) {
        return runFunction(loader::load, fxmlFilePath).orHandle(handler);
    }

    /**
     * Effect: Loads the provided fxml file using the Spring FXML Loader object
     * and sets the view's main element.
     *
     * @param fxmlFilePath: the fxml file's path  [String]
     * @param element:      the view's main element [T]
     * @param <T>:          the main element's type.
     * @return root: the root node of the fxml file [Parent]
     */
    public <T> Parent getRootNode(String fxmlFilePath, T element) {
        return runFunction(loader::load, fxmlFilePath, element).orHandle(handler);
    }

}
