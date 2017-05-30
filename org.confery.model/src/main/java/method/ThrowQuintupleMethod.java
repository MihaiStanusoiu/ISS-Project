package method;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */



@FunctionalInterface
public interface ThrowQuintupleMethod<T1, T2, T3, T4, E extends Throwable> {
    void accept(T1 param1, T2 param2, T3 param3, T4 param4) throws E;
}