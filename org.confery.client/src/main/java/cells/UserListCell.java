package cells;


import cellcontroller.UserListCellController;
import domain.UserEntity;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Pane;
import loader.ItemFXMLLoader;
import loader.LoaderException;
import manager.StageManager;
import view.ViewType;

import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class UserListCell extends ListCell<UserEntity> {

    private StageManager manager;

    public UserListCell(StageManager stageManager) {
        manager = stageManager;
    }

    private Pane getGraphic(UserEntity item) throws LoaderException {
        ItemFXMLLoader<UserEntity, UserListCellController> loader =
                new ItemFXMLLoader<>(ViewType.USER_CELL_LIST_ITEM);
        loader.setElement(item);
        loader.setStageManager(manager);
        return loader.getRootPane();
    }

    protected void updateItem(UserEntity item, boolean empty) {
        super.updateItem(item, empty);
        setGraphic((item != null && !empty) ?
                runFunction(() -> this.getGraphic(item)).or(new Pane(new Label("ana"))) : null);
    }

}
