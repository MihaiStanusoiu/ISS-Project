package utils;

import method.ThrowBiMethod;
import method.ThrowEmptyMethod;
import method.ThrowMethod;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class Conditional {

    private Boolean condition;

    private Conditional(Boolean condition) {
        this.condition = condition;
    }

    public static Conditional basedOn(Boolean condition) {
        return new Conditional(condition);
    }

    public <X extends Throwable> Boolean orThrow(X exception) throws X {
        if (condition == Boolean.FALSE) {
            throw exception;
        }
        return true;
    }


    @SuppressWarnings("unchecked")
    public <E extends Throwable> Conditional runTrue(ThrowEmptyMethod<E> method) throws E {
        try {
            if (condition.equals(Boolean.TRUE)) {
                method.accept();
            }
            return this;
        } catch (Throwable exception) {
            throw (E) exception;
        }
    }

    @SuppressWarnings("unchecked")
    public <E extends Throwable> void
    runFalse(ThrowEmptyMethod<E> method) throws E {
        try {
            if (condition.equals(Boolean.FALSE)) {
                method.accept();
            }
        } catch (Throwable exception) {
            throw (E) exception;
        }
    }

    @SuppressWarnings("unchecked")
    public <T, E extends Throwable> Conditional
    runTrue(ThrowMethod<T, E> method, T param) throws E {
        try {
            if (condition.equals(Boolean.TRUE)) {
                method.accept(param);
            }
            return this;
        } catch (Throwable exception) {
            throw (E) exception;
        }
    }

    @SuppressWarnings("unchecked")
    public <T, E extends Throwable> void
    runFalse(ThrowMethod<T, E> method, T param) throws E {
        try {
            if (condition.equals(Boolean.FALSE)) {
                method.accept(param);
            }
        } catch (Throwable exception) {
            throw (E) exception;
        }
    }

    @SuppressWarnings("unchecked")
    public <T1, T2, E extends Throwable> Conditional
    runTrue(ThrowBiMethod<T1, T2, E> method, T1 param1, T2 param2) throws E {
        try {
            if (condition.equals(Boolean.TRUE)) {
                method.accept(param1, param2);
            }
            return this;
        } catch (Throwable exception) {
            throw (E) exception;
        }
    }

    @SuppressWarnings("unchecked")
    public <T1, T2, E extends Throwable> void
    runFalse(ThrowBiMethod<T1, T2, E> method, T1 param1, T2 param2) throws E {
        try {
            if (condition.equals(Boolean.FALSE)) {
                method.accept(param1, param2);
            }
        } catch (Throwable exception) {
            throw (E) exception;
        }
    }

}
