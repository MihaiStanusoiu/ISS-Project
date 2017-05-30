package method;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */


@FunctionalInterface
public interface ThrowBiMethod<T1, T2, E extends Throwable> {
    void accept(T1 param1, T2 param2) throws E;
}