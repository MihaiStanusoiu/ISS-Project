package controller.pagination;

import controller.item.ControllerConferenceView;
import itemcontroller.PaginationControllerItemInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import manager.StageManager;
import method.SimpleMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import transfarable.Session;
import view.ViewType;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */



@Lazy
@Component
@Scope("prototype")
public class ControllerPaginationSessionItem
        implements PaginationControllerItemInterface<Session> {

    @FXML
    private Label seatsLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private BorderPane rootPane;

    @Lazy
    @Autowired
    private StageManager manager;

    private Session item;

    private static Logger logger;

    private SimpleMethod<RemoteException> handler;

    @Override
    public void initialize() {
        logger = Logger.getLogger(ControllerConferenceView.class);
        handler = exception -> logger.error(exception.getCause());
    }

    @Override
    public void setElement(Session element) {
        this.item = element;
        build();
    }

    @FXML
    public void onItemClick() {
        manager.switchScene(ViewType.SESSION, item);
    }

    private void build() {
        nameLabel.setText(item.getName());
        locationLabel.setText(item.getLocation());
        seatsLabel.setText(item.getSeats().toString());
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
