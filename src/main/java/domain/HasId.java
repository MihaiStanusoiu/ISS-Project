package domain;
/**
 * Name:         HasId
 * Effect:       Interface class for id.
 * Date:         02.04.2017
 * Tested:       False
 * @author       Tiron Andreea - Ecaterina
 * @version      1.0
 */
public interface HasId<T>{
    /**
     * Effect    Return the id.
     * @return   T - returns the id.
     */
    T getId();
    /**
     * Effect    Set the id.
     * <p>{Details}
     * @param   id - set the new id.
     */
    void setID(T id);
}