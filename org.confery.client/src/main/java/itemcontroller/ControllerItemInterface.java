package itemcontroller;

/**
 * Name:        ControllerItemInterface
 * Effect:      Controls a view with is based on an item.
 *              <p>
 *                  Example: ConferenceView controlled by ControllerConferenceView
 *                  The view need an element (type ConferenceEntity) in order to display data in the view.
 *
 *                  This interface allows the SpringFXMLLoader to view the setElement function
 *                  in order to set the required item.
 *
 *                  Any view that is based on an element/item must implement this interface
 *                  in order to use the StageManger for view switching.
 *              </p>
 * Date:        11/04/2017
 * Tested:      False
 *
 * @author      Alexandru Stoica
 * @version     1.0
 */

public interface ControllerItemInterface<T> {

    /**
     * Effect: Sets the view's element.
     * Example: item ConferenceEntity for ConferenceView
     *          in ControllerConferenceView
     * @param element The view's element.
     */
    void setElement(T element);

}
