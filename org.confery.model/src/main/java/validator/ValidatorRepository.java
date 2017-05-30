package validator;

import exception.SystemException;
import exception.ValidatorSystemException;

/**
 * Tested: True
 *
 * @author Tanasie Luiza Maria
 * @version 1.0
 */


public class ValidatorRepository<T> {

    private ValidatorRepositoryBehaviour<T> behaviour;

    /**
     * @param entityClass : class of the object given to validate
     */
    @SuppressWarnings("all")
    public ValidatorRepository(Class<T> entityClass) {
        this.behaviour = ValidatorRepositoryBehaviourFactory.getBehaviour(entityClass);
    }

    /**
     * @param object : object to validate
     * @return [Boolean] : Returns true if given object is valid
     * @throws SystemException : When given object isn't valid
     */
    public Boolean validate(T object) throws SystemException {
        if (behaviour.check(object).size() == 0) {
            return true;
        }
        throw new ValidatorSystemException(behaviour.getAccumulator());
    }
}
