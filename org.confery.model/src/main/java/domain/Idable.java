
package domain;

import java.io.Serializable;

/**
 * Tested True
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface Idable<T> extends Serializable {

    /**
     * Returns the object's id.
     * @return The object's id
     */
    T getId();

    /**
     * Sets the object's id.
     * @param id The object's id.
     */
    void setId(T id);
}
