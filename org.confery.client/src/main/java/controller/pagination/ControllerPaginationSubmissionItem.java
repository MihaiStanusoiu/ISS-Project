package controller.pagination;

import itemcontroller.PaginationControllerItemInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import transfarable.Submission;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.1
 */

@Lazy
@Component
public class ControllerPaginationSubmissionItem
        implements PaginationControllerItemInterface<Submission> {

    @FXML
    private Label acronymLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private BorderPane rootPane;

    @FXML
    private Pane statusIndicator;

    @Lazy
    @Autowired
    @SuppressWarnings("unused")
    private StageManager manager;

    private Submission item;

    @Override
    public void initialize() throws RemoteException {

    }

    @Override
    public void setElement(Submission element) {
        this.item = element;
        build();
    }

    @SuppressWarnings("EmptyMethod")
    @FXML
    public void onItemClick() {

    }

    private void build() {
        nameLabel.setText(item.getName());
        statusLabel.setText(item.getStatus());
        acronymLabel.setText(item.getName());
        statusIndicator.setStyle(statusIndicator.getStyle() +
                "-fx-background-color: " + getStatusColorValue());
    }

    private String getStatusColorValue() {
        return item.getStatus().toLowerCase().equals("reviewed") ? "#40FD8A" : "#FA7A92";
    }

}
