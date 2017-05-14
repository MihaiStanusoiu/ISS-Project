package utils;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class Functional {

    @FunctionalInterface
    public interface SimpleFunction<R> {
        R apply() throws Exception;
    }

    @FunctionalInterface
    public interface Function<R> {
        R apply(Object... parameters) throws Exception;
    }

    @FunctionalInterface
    public interface SimpleMethod {
        void accept() throws Exception;
    }

    @FunctionalInterface
    public interface MethodProtocol {
        void accept() throws RemoteException;
    }

    @FunctionalInterface
    public interface Method {
        void accept(Object... parameters) throws Exception;
    }
}