package exception;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ModelException extends SystemException {

    private String message;

    public ModelException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void addException(Exception exception) {
        this.message += System.lineSeparator() + exception.getMessage();
    }

}
