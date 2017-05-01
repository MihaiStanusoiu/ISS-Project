package data;

/**
 * Base class for id-able objects.
 * @author Alexandru Stoica
 * @version 1.0
 */

public class Idable<T> implements HasId<T> {

    protected T id;

    @Override
    public T getId() {
        return id;
    }
}
