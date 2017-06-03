package exception;

import java.util.ArrayList;
import java.util.List;

/**
 * Tested: True
 *
 * @author Tanasie Luiza Maria
 * @version 1.0
 */

public class ValidatorSystemException extends SystemException {

    private List<String> messages;

    public ValidatorSystemException(List<String> messages) {
        this.messages = messages;
    }

    public ValidatorSystemException(String message) {
        messages = new ArrayList<>();
        messages.add(message);
    }

    @Override
    public String getMessage() {
        return messages.stream().reduce("", (accumulator, message)
                -> accumulator + System.lineSeparator() + message);
    }
}
