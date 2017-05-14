package validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Name:         ValidatorRepositoryBehaviour
 * Effect:       Validates based on a behaviour an object
 * Date:         06/05/2017
 * Tested:       False
 *
 * @author Tanasie Luiza Maria
 * @version 1.0
 */
@SuppressWarnings("all")
public abstract class ValidatorRepositoryBehaviour<T> {

    protected List<String> accumulator;

    public ValidatorRepositoryBehaviour()
    {
        accumulator = new ArrayList<>();
    }

    public abstract List<String> check(T object);

    /**
     * Gets the accumulator
     * @return [List<String>] : returns accumulator
     */
    public List<String> getAccumulator(){
        return this.accumulator;
    }
}
