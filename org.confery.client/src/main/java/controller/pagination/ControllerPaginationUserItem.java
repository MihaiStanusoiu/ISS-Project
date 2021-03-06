package controller.pagination;

import itemcontroller.PaginationControllerItemInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import transfarable.User;
import view.ViewType;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.1
 */

@Lazy
@Component
@Scope("prototype")
public class ControllerPaginationUserItem
        implements PaginationControllerItemInterface<User> {

    @FXML
    private Label locationLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private BorderPane rootPane;

    @Lazy
    @Autowired
    private StageManager manager;

    private User item;

    @Override
    public void initialize() throws RemoteException { }

    @Override
    public void setElement(User element) {
        this.item = element;
        build();
    }

    @FXML
    public void onItemClick() {
        manager.switchScene(ViewType.USER, item);
    }

    private void build() {
        locationLabel.setText(item.getLocation());
        nameLabel.setText(item.getName());
        usernameLabel.setText("@" + item.getUsername());
    }

    public Pane getRootPane() {
        return rootPane;
    }

    @Override
    public double getWidth() {
        return rootPane.getWidth();
    }

    @Override
    public double getHeight() {
        return rootPane.getHeight();
    }

}
