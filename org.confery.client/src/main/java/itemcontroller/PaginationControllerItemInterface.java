package itemcontroller;

import controller.main.ControllerInterface;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Any view-controller that controls an item view (example: ConferenceItem)
 * needs to offer a couple of function to PaginationManager in order for the builder
 * to work and the showcase the items in order in a grid-pane
 * <p>
 * <p>
 * Any view-controller that controls an item view must implement those methods
 * in other to merge with the PaginationManager.
 * </p>
 * <p>
 * <p>
 * Similar Example: TableView needs a TableCell class in order to work.
 * </p>
 *
 * @author Alexandru Stoica
 * @version 1.1
 */

@Lazy
@Component
public interface PaginationControllerItemInterface<T> extends ControllerItemInterface<T>, ControllerInterface { }
