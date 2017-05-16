package functions;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@FunctionalInterface
public interface ThrowEmptyMethod<E extends Throwable> {
    void accept() throws E;
}