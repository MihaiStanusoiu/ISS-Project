package list;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import org.jetbrains.annotations.NotNull;
import view.Icon;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ListViewBuilder<T> {

    private ListView<T> listView;
    private Function<T, String> provider;
    private BiFunction<List<T>, T, Boolean> action;
    private List<T> list;
    private Icon icon;

    public ListViewBuilder(ListView<T> listView) {
        this.listView = listView;
    }

    public ListViewBuilder<T> visibleText(Function<T, String> provider) {
        this.provider = provider;
        return this;
    }

    public ListViewBuilder<T> setAction(BiFunction<List<T>, T, Boolean> action, List<T> list) {
        this.action = action;
        this.list = list;
        return this;
    }

    public ListView<T> build() {
        listView.setCellFactory(param -> generateListCell());
        return listView;
    }

    public ListViewBuilder<T> setIcon(Icon icon) {
        this.icon = icon;
        return this;
    }

    @NotNull
    private ListCell<T> generateListCell() {
        GeneralCell<T> cell = new GeneralCell<>();
        cell.setTextProvider(provider);
        cell.setAction(action, list);
        cell.setIcon(icon);
        return cell;
    }
}
