package controller.pagination;

import itemcontroller.PaginationControllerItemInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import transfarable.Edition;
import view.GradientGenerator;
import view.ViewType;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.1
 */

@Lazy
@Component
@Scope("prototype")
public class ControllerPaginationEditionItem
        implements PaginationControllerItemInterface<Edition> {

    @FXML
    private Label locationLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private BorderPane rootPane;

    @Lazy
    @Autowired
    private StageManager manager;

    @FXML
    private Button background;

    private GradientGenerator generator;

    private Edition item;

    @Override
    public void initialize() throws RemoteException {
        generator = new GradientGenerator();
    }

    @Override
    public void setElement(Edition element) {
        item = element;
        build();
    }

    @FXML
    public void onItemClick() {
        manager.switchScene(ViewType.EDITION, item);
    }

    private void build() {
        locationLabel.setText(item.getLocation());
        dateLabel.setText(item.getStartDate().toString());
        background.setStyle(String.format("-fx-background-color : %s",
                generator.getGradient().getValue()));   /* sets a random gradient */
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
