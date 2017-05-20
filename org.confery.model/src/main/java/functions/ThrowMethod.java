package functions;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

@FunctionalInterface
public interface ThrowMethod<T, E extends Throwable> {
    void accept(T element) throws E;
}