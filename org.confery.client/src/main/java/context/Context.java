package context;

import java.lang.annotation.*;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Context {

    ContextType[] value();

}
