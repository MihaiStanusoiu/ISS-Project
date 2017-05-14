package transferable;

import java.io.Serializable;

/**
 * Interface designed to provide a protocol for id-able objects.
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface HasId<T> extends Serializable {
    T getId();
}
