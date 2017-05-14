package utils;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ThrowPipe {

    @SuppressWarnings("all")
    private static <T, E extends Exception> T rethrow(Exception exception) throws E {
        throw (E) exception;
    }

    public static <R> R wrap(Functional.SimpleFunction<R> function) {
        try {
            return function.apply();
        } catch (Exception exception) {
            return rethrow(exception);
        }
    }

    public static <R> R wrap(Functional.Function<R> function, Object... parameters) {
        try {
            return function.apply(parameters);
        } catch (Exception exception) {
            return rethrow(exception);
        }
    }


    public static void wrap(Functional.SimpleMethod method) {
        try {
            method.accept();
        } catch (Exception exception) {
            rethrow(exception);
        }
    }

    public static void wrap(Functional.Method method, Object... parameters) {
        try {
            method.accept(parameters);
        } catch (Exception exception) {
            rethrow(exception);
        }
    }
}