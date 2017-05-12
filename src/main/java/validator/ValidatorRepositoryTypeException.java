package validator;

import repository.RepositoryException;

/**
 * Name:         ValidatorRepositoryTypeException
 * Effect:       The exception thrown when validation behaviour cannot be retrieved for given object
 * Date:         06/05/2017
 *
 * @author Tanasie Luiza Maria
 * @version 1.0
 */
@SuppressWarnings("all")
public class ValidatorRepositoryTypeException extends RepositoryException {

    private String message;

    public ValidatorRepositoryTypeException(String message){
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
