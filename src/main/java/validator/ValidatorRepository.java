package validator;

import repository.RepositoryException;


/**
 * Name:         Validator for Repository
 * Effect:       Validates an object
 * Date:         06/05/2017
 * Tested:       False
 *
 * @author Tanasie Luiza Maria
 * @version 1.0
 */
@SuppressWarnings("all")
public class ValidatorRepository<T> {

    private ValidatorRepositoryBehaviour<T> behaviour;
    private final Class<T> entityClass;

    /**
     * @param entityClass : class of the object given to validate
     * @throws RepositoryException : When object of given class cannot be validated
     */
    public ValidatorRepository(Class<T> entityClass) throws RepositoryException {
        this.entityClass = entityClass;
        this.behaviour = ValidatorRepositoryBehaviourFactory.getBehaviour(entityClass);
    }

    /**
     * @param object : object to validate
     * @return [Boolean] : Returns true if given object is valid
     * @throws RepositoryException : When given object isn't valid
     */
    public Boolean validate(T object) throws RepositoryException {
        if(behaviour.check(object).size()==0){
            return true;
        }
        else{
            throw new ValidatorRepositoryException(behaviour.getAccumulator());
        }
    }
}
