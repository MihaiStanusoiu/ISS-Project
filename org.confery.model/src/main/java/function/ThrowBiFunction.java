package function;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */


@FunctionalInterface
public interface ThrowBiFunction<T1, T2, R, E extends Throwable> {
    R apply(T1 element, T2 other) throws E;
}