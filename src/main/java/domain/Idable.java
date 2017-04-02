package domain;

/**
 * Name:         Idable
 * Effect:       Interface class for id.
 * Date:         02.04.2017
 * Tested:       True
 * @author       Tiron Andreea - Ecaterina
 * @version      1.0
 */
public class Idable<T> implements HasId<T>
{
    protected T id;

    /**
     * Effect: Return the id.
     * @return [T] : returns the id.
     */
    @Override
    public T getId()
    {
        return id;
    }

    /**
     * Effect: Set the id.
     * @param Id : [T]  the value of the new id.
     */
    @Override
    public void setId(T Id)
    {
        this.id=Id;
    }
}
