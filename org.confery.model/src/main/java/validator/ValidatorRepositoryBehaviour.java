package validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Tested:       False
 *
 * @author Tanasie Luiza Maria
 * @version 1.0
 */

public abstract class ValidatorRepositoryBehaviour<T> {

    protected List<String> accumulator;

    public ValidatorRepositoryBehaviour() {
        accumulator = new ArrayList<>();
    }

    public abstract List<String> check(T object);

    /**
     * Gets the accumulator
     *
     * @return [List<String>] : returns accumulator
     */
    public List<String> getAccumulator() {
        return this.accumulator;
    }
}
