package method;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

@FunctionalInterface
public interface ThrowEmptyMethod<E extends Throwable> {
    void accept() throws E;
}