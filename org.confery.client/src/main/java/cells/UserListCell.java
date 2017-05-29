package cells;


import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Pane;
import loader.LoaderException;
import manager.StageManager;
import transfarable.User;
import view.ViewType;

import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class UserListCell extends ListCell<User> {

    private StageManager manager;

    public UserListCell(StageManager stageManager) {
        manager = stageManager;
    }

    private Pane getGraphic(User item) throws LoaderException {
        return (Pane)manager.getRootNode(ViewType.USER_CELL_LIST_ITEM.getFXMLFile(), item);
    }

    protected void updateItem(User item, boolean empty) {
        super.updateItem(item, empty);
        setGraphic((item != null && !empty) ?
                runFunction(() -> this.getGraphic(item)).or(new Pane(new Label("ana"))) : null);
    }

}
