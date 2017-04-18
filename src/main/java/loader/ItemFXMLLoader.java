package loader;

import item_controller.PaginationControllerItemInterface;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import view.ViewType;
import java.io.IOException;

/**
 * Name:        ItemFXMLLoader
 * Effect:      Loads the view of an item for PaginationBuilder.
 * Date:        09/04/2017
 * Tested:      False
 *
 * @author      Alexandru Stoica
 * @version     1.0
 */

public class ItemFXMLLoader<T, Controller extends PaginationControllerItemInterface<T>> {

    private Controller controller;

    /**
     * Effect: Loads a view based on type and keeps
     * details about it's view-controller and root pane.
     * @param view [ViewType] The view's type.
     * @throws LoaderException: If we are unable to load the view.
     */
    public ItemFXMLLoader(ViewType view) throws LoaderException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(view.getFXMLFile()));
        try {
            loader.load();
        } catch (IOException error) {
            throw new LoaderException("Unable to load the fxml file.");
        }
        controller = loader.getController();
    }

    /**
     * @return [Controller] The view's controller.
     */
    public Controller getController() {
        return controller;
    }

    /**
     * Effect: Sets an element in the view-controller.
     * @apiNote Used for item view controllers.
     * @param element [T]: The required element.
     */
    public void setElement(T element) {
        controller.setElement(element);
    }

    /**
     * @return [Pane] The view's root pane.
     */
    public Pane getRootPane() {
        return controller.getPane();
    }

}
