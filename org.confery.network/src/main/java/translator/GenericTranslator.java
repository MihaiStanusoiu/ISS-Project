package translator;

import domain.Idable;

/**
 * Created by Mike on 5/30/2017.
 */

public abstract class GenericTranslator<T extends Idable<Id>, Id, TransferableT> {

    protected GenericTranslator() {}

    public abstract T translate(TransferableT transferable);

    public abstract TransferableT translate(T type);
}
