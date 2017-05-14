package exception;

import java.util.List;

/**
 * Name:         ValidatorRepositoryException
 * Effect:       The exception thrown when object to be validated is invalid.
 * Date:         06/05/2017
 *
 * @author Tanasie Luiza Maria
 * @version 1.0
 */
@SuppressWarnings("all")
public class ValidatorRepositoryException extends RepositoryException {
    private List<String> messages;

    public ValidatorRepositoryException(List<String> messages) {
        this.messages = messages;
    }

    @Override
    public String getMessage() {
        return messages.stream().reduce("", (accumulator, message) -> accumulator + System.lineSeparator() + message);
    }
}
