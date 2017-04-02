package model;

/**
 * Name:         HasId
 * Effect:       Interface class for id.
 * Date:         02.04.2017
 * Tested:       True
 * @author       Tiron Andreea - Ecaterina
 * @version      1.0
 */
public interface HasId<T>{
    /**
     * Effect: Return the id.
     * @return [T] : returns the id.
     */
     T getId();

    /**
     * Effect: Set the id.
     * @param id : [T]  the value of the new id.
     */
     void setId(T id);
}
