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
import org.springframework.stereotype.Component;
import transfarable.Conference;
import view.GradientGenerator;
import view.ViewType;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.1
 */

@Lazy
@Component
public class ControllerPaginationConferenceItem
        implements PaginationControllerItemInterface<Conference> {

    @FXML
    private Label editionNumberLabel;

    @FXML
    private Label acronymLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private BorderPane rootPane;

    @FXML
    private Button background;

    @Lazy
    @Autowired
    private StageManager manager;

    private GradientGenerator generator;

    private Conference item;

    @Override
    public void initialize() throws RemoteException {
        generator = new GradientGenerator();
    }

    @Override
    public void setElement(Conference element) {
        this.item = element;
        build();
    }

    @FXML
    public void onItemClick() {
        manager.switchScene(ViewType.CONFERENCE, item);
    }

    private void build() {
        editionNumberLabel.setText("1");
        acronymLabel.setText(item.getAcronym());
        nameLabel.setText(item.getName());
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
