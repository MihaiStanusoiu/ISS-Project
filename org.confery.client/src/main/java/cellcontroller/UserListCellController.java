package cellcontroller;

import itemcontroller.PaginationControllerItemInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import transfarable.User;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class UserListCellController
        implements PaginationControllerItemInterface<User> {

    @FXML
    private BorderPane rootPane;

    @FXML
    private Label nameLabel;

    private User user;

    @Lazy
    @Autowired
    private StageManager manager;

    @Override
    public void initialize() throws RemoteException { }

    @Override
    public void setElement(User element) {
        user = element;
        build();
    }

    private void build() {
        nameLabel.setText(user.getName());
    }

    public BorderPane getRootPane() {
        return rootPane;
    }

}
