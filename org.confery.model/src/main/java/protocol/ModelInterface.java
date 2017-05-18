package protocol;

import domain.Idable;
import exception.SystemException;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface ModelInterface<T extends Idable<Id>, Id extends Serializable> {

    Id add(T element) throws SystemException;
    T delete(T element) throws SystemException;
    void update(T element, T with) throws SystemException;
    T getElementById(Id id) throws SystemException;
    List<T> getAll();

}
