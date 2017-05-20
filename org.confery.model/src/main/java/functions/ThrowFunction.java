package functions;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

@FunctionalInterface
public interface ThrowFunction<T, R, E extends Throwable> {
    R apply(T element) throws E;
}