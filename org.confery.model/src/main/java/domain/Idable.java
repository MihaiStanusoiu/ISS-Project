package domain;

import java.io.Serializable;

/**
 * Name:         Idable
 * Effect:       Defines the id property for a concrete domain class.
 * Date:         4/17/2017
 * Tested:       True
 * @author       Alexandru Stoica
 * @version      1.0
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
