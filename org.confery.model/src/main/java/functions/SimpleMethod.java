package functions;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@FunctionalInterface
public interface SimpleMethod<T> {
    void accept(T element);
}