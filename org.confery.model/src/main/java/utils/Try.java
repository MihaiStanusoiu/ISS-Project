package utils;

import functions.*;

import java.util.function.Function;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */


public class Try<T, E extends Throwable> {

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

    public <X extends Exception> T orThrow(Function<E, X> converter) throws X {
        if (element != null) {
            return element;
        }
        throw converter.apply(exception);
    }

    @SuppressWarnings("unchecked")
    public T orHandle(SimpleMethod<E> handler) {
        if (element != null) {
            return element;
        }
        handler.accept(exception);
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <E extends Throwable> Try<Boolean, E>
    runFunction(ThrowEmptyMethod<E> method) {
        try {
            method.accept();
            return new Try(true);
        } catch (Throwable exception) {
            return new Try(exception);
        }
    }
    @SuppressWarnings("unchecked")
    public static <T, E extends Throwable> Try<Boolean, E>
    runFunction(ThrowMethod<T, E> method, T param) {
        try {
            method.accept(param);
            return new Try(true);
        } catch (Throwable exception) {
            return new Try(exception);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T1, T2, E extends Throwable> Try<Boolean, E>
    runFunction(ThrowBiMethod<T1, T2, E> method, T1 param1, T2 param2) {
        try {
            method.accept(param1, param2);
            return new Try(true);
        } catch (Throwable exception) {
            return new Try(exception);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T, R, E extends Throwable> Try<R, E>
    runFunction(ThrowFunction<T, R, E> function, T param) {
        try {
            return new Try(function.apply(param));
        } catch (Throwable exception) {
            return new Try(exception);
        }
    }

    @SuppressWarnings("unchecked")
    public static <R, E extends Throwable> Try<R, E>
    runFunction(ThrowEmptyFunction<R, E> function) {
        try {
            return new Try(function.apply());
    } catch (Throwable exception) {
            return new Try(exception);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T1, T2, R, E extends Throwable> Try<R, E>
    runFunction(ThrowBiFunction<T1, T2, R, E> function, T1 param1, T2 param2) {
        try {
            return new Try(function.apply(param1, param2));
        } catch (Throwable exception) {
            return new Try(exception);
        }
    }

}
