package method;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */



@FunctionalInterface
public interface ThrowQuadrupleMethod<T1, T2, T3, T4, T5, E extends Throwable> {
    void accept(T1 param1, T2 param2, T3 param3, T4 param4, T5 param5) throws E;
}