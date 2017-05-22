package function;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

@FunctionalInterface
public interface ThrowEmptyFunction<R, E extends Throwable> {
    R apply() throws E;
}