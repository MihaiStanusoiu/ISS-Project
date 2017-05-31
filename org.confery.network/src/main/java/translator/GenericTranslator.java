package translator;

import domain.Idable;
import org.jetbrains.annotations.NotNull;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface GenericTranslator<T extends Idable<?>, TransferT> {

    T translate(@NotNull TransferT transferable);
    TransferT translate(@NotNull T entity);

}
