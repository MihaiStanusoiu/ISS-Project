package list;

import javafx.collections.ListChangeListener;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import transfarable.IdableTransfer;
import view.Icon;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ListViewBuilder<T extends IdableTransfer<?>> {

    private ListView<T> listView;
    private Function<T, String> provider;
    private Icon icon;
    private BiFunction<Object, T, ?> action;
    private Object object;
    private String color;
    private static Logger logger;
    private final Integer HEIGHT_CELL = 80;

    public ListViewBuilder(ListView<T> listView) {
        logger = Logger.getLogger(ListViewBuilder.class);
        this.listView = listView;
    }

    public ListViewBuilder<T> setColor(String color) {
        this.color = color;
        return this;
    }

    public ListViewBuilder<T> textProvider(Function<T, String> provider) {
        this.provider = provider;
        return this;
    }

    public ListViewBuilder<T> setIcon(Icon icon) {
        this.icon = icon;
        return this;
    }

    public ListViewBuilder<T> setAction(BiFunction<Object, T, ?> action, Object object) {
        this.action = action;
        this.object = object;
        return this;
    }

    public ListView<T> build() {
        listView.setCellFactory(param -> generateListCell());
        listView.setPrefHeight(listView.getItems().size() + HEIGHT_CELL);
        listView.getItems().addListener((ListChangeListener<T>) change ->
                listView.resize(listView.getWidth(),
                        listView.getItems().size() + HEIGHT_CELL));
        return listView;
    }

    @NotNull
    private ListCell<T> generateListCell() {
        return new GeneralCell<T>().setProvider(provider)
                .setAction(action)
                .setObject(object)
                .setMarkerColor(color)
                .setIcon(icon);
    }
}
