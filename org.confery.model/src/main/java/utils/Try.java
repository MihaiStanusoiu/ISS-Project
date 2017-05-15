package utils;

import java.util.function.Function;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */


public class Try<T, E extends Exception> {

    private T element;
    private E exception;

    public Try(T element) {
        this.element = element;
        this.exception = null;
    }

    public Try(E exception) {
        this.element = null;
        this.exception = exception;
    }

    public T getElement() {
        return element;
    }

    public E getException() {
        return exception;
    }

    public Boolean hasException() {
        return exception != null;
    }

    public Boolean hasElement() {
        return element != null;
    }

    public T or(T other) {
        return element != null ? element : other;
    }

    @SuppressWarnings("unchecked")
    public <X> X get() {
        return element != null ? (X) element: (X) exception;
    }

    public <X extends Exception>T orThrow(Function<E, X> converter) throws X {
        if (element != null) {
            return element;
        }
        throw converter.apply(exception);
    }


}
