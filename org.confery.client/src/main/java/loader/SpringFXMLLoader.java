package loader;

import itemcontroller.ControllerItemInterface;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * @author Alexandru Stoica
 * @version 1.1
 */

@Component
public class SpringFXMLLoader {

    /**
     * Local Resource Source
     */
    private final ResourceBundle resourceBundle;

    /**
     * The Spring Application Context.
     */
    private final ApplicationContext context;

    /**
     * @param context:        The spring's application context [ApplicationContext]
     * @param resourceBundle: The local resources. [ResourceBundle]
     */
    @Autowired
    public SpringFXMLLoader(ApplicationContext context, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
        this.context = context;
    }

    /**
     * Effect: Loads a given fxml file.
     *
     * @param fxmlFilePath: The fxml file's path [String]
     * @return root: The root node [Parent]
     * @throws IOException: If we are unable to load the fxml file.
     */
    public Parent load(String fxmlFilePath) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(context::getBean);
        loader.setResources(resourceBundle);
        loader.setLocation(getClass().getResource(fxmlFilePath));
        return loader.load();
    }

    /**
     * Effect: Loads a given fxml file.
     * and set's the fxml view's main item.
     *
     * @param fxmlFilePath: The fxml file's path [String]
     * @param element:      The view's main element [T]
     * @param <T>:          The main element's type
     * @return root: The root node [Parent]
     * @throws IOException: If we are unable to load the fxml file.
     */
    public <T> Parent load(String fxmlFilePath, T element) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(context::getBean);
        loader.setResources(resourceBundle);
        loader.setLocation(getClass().getResource(fxmlFilePath));
        loader.load();
        ControllerItemInterface<T> controller = loader.getController();
        controller.setElement(element);
        return loader.getRoot();
    }
}
