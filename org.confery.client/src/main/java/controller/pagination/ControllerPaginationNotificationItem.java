package controller.pagination;

import itemcontroller.PaginationControllerItemInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import manager.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import transfarable.Notification;
import view.GradientGenerator;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.1
 */

@Lazy
@Component
public class ControllerPaginationNotificationItem
        implements PaginationControllerItemInterface<Notification> {

    @FXML
    private Label textLabel;

    @FXML
    private Button payButton;

    @FXML
    private BorderPane rootPane;

    @Lazy
    @Autowired
    @SuppressWarnings("all")
    private StageManager manager;

    @FXML
    private Button background;

    private GradientGenerator generator;

    private Notification item;

    @Override
    public void initialize() throws RemoteException {
        generator = new GradientGenerator();
    }

    @Override
    public void setElement(Notification element) {
        this.item = element;
        build();
    }

    @SuppressWarnings("EmptyMethod")
    @FXML
    public void onItemClick() { }

    private void build() {
        if (item.getPaymentType().equals(Boolean.TRUE)) {   /* hides the payment button */
            payButton.setOpacity(0);                    /* if it's not required */
            payButton.setMaxWidth(0);
            payButton.setMaxHeight(0);
        }
        textLabel.setText(item.getText());
        background.setStyle(String.format("-fx-background-color : %s",
                generator.getGradient().getValue()));   /* sets a random gradient */
    }

    @FXML
    public void onPayButtonClick() {
        System.out.println("Pay Button Click");
    }

}
