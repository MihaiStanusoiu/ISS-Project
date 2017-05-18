package checker;

import exception.SystemException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface Checker<T> {

    Boolean check(T element) throws SystemException;

}
