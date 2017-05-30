package checker;

import exception.SystemException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface Checker<T> {


    /**
     * Checks if an element if valid based on conditions
     *
     * @param element The target element
     * @return true if the element if valid
     * @throws SystemException if the element if not valid
     */
    Boolean check(T element) throws SystemException;

}
