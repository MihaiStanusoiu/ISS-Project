package function;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */


@FunctionalInterface
public interface ThrowQuintupleFunction<T1, T2, T3, T4, R, E extends Throwable> {
    R apply(T1 param1, T2 param2, T3 param3, T4 param4) throws E;
}