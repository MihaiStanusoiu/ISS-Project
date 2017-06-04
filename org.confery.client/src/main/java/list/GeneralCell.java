package list;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import transfarable.IdableTransfer;
import view.Icon;

import java.util.function.BiFunction;
import java.util.function.Function;

import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class GeneralCell<T extends IdableTransfer<?>> extends ListCell<T> {

    private Function<T, String> provider;
    private Icon icon;
    private BiFunction<Object, T, ?> action;
    private Object object;
    private static Logger logger;
    private String markerColor;

    public GeneralCell() {
        logger = Logger.getLogger(GeneralCell.class);
        markerColor = "chartreuse";
        icon = Icon.EMPTY;
        provider = Object::toString;
        action = (object, item) ->  {
            logger.info(item.toString() + "'s button was clicked by the user!");
            return null;
        };
    }

    public GeneralCell<T> setMarkerColor(String color) {
        markerColor = color;
        return this;
    }

    public GeneralCell<T> setProvider(Function<T, String> provider) {
        this.provider = provider;
        return this;
    }

    public GeneralCell<T> setIcon(Icon icon) {
        this.icon = icon;
        return this;
    }

    public GeneralCell<T> setAction(BiFunction<Object, T, ?> action) {
        this.action = action;
        return this;
    }

    public GeneralCell<T> setObject(Object object) {
        this.object = object;
        return this;
    }

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        setGraphic((item != null && !empty) ?
                runFunction(() -> this.getItemGraphic(item)).or(new Pane(new Label(""))) : null);
    }

    private Node getItemGraphic(T item) {
        HBox pane = createItemPane();
        pane.getChildren().addAll(createCircle(), createSpace(10.0), createLabel(item), createButton(item));
        return pane;
    }

    @NotNull
    private Label createLabel(T item) {
        Label label = new Label(provider.apply(item));
        label.setFont(new Font("Proxima Nova Regular", 14.0));
        return label;
    }

    private HBox createItemPane() {
        return setUpPaneSize(setUpPaneStyle(new HBox()));
    }

    private HBox setUpPaneStyle(HBox pane) {
        pane.setStyle(getBackgroundBasedOnColor("white") + withRadius(5));
        pane.setAlignment(Pos.CENTER_LEFT);
        pane.setEffect(new DropShadow(20, new Color(0, 0, 0, 0.1)));
        return pane;
    }

    @NotNull
    private String withRadius(Integer radius) {
        return String.format("-fx-background-radius: %d %d %d %d", radius, radius, radius, radius);
    }

    @NotNull
    private String getBackgroundBasedOnColor(String color) {
        return String.format("-fx-background-color: %s;", color);
    }

    private Button createButton(T item) {
        Button button = new Button();
        button.setStyle(getBackgroundBasedOnColor("transparent"));
        button.setGraphic(getIcon());
        setUpActivity(item, button);
        return button;
    }

    private void setUpActivity(T item, Button button) {
        button.setOnMouseClicked(event -> action.apply(object, item));
    }

    @NotNull
    private ImageView getIcon() {
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(icon.getValue())));
        imageView.setFitHeight(20.0);
        imageView.setFitWidth(20.0);
        return imageView;
    }

    @NotNull
    private Pane createSpace(Double size) {
        Pane space = new Pane();
        setSizeForPane(space, size, size, size);
        return space;
    }

    private Pane createCircle() {
        Pane circle = createSpace(15.0);
        circle.setStyle(getBackgroundBasedOnColor(markerColor) + withRadius(100));
        return circle;
    }

    private HBox setUpPaneSize(HBox pane) {
        setSizeForPane(pane, Double.MIN_VALUE, USE_COMPUTED_SIZE, Double.MAX_VALUE);
        pane.setPadding(new Insets(10, 10, 10, 10));
        return pane;
    }

    private void setSizeForPane(Pane pane, Double min, Double pref, Double max) {
        pane.setMinSize(min, min);
        pane.setPrefSize(pref, pref);
        pane.setMaxSize(max, max);
    }

}