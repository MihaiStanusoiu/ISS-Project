package utils;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.BiFunction;

/**
 * Tested: True
 * @author Alexandru Stoica
 * @version 1.0
 */

public class Comparator implements Serializable {

    private Comparator() { }

    public static <T> T checkClass(Object object, Class<?> objectClass) {
        return (Objects.nonNull(object) && object.getClass().equals(objectClass)) ? (T) object : null;
    }

    public static <T> Boolean checkObjects(BiFunction<T, T, Boolean> comparator, T left, T right) {
        return right != null ? comparator.apply(left, right) : false;
    }

}
