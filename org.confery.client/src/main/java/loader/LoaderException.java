package loader;

/**
 * @author      Alexandru Stoica
 * @version     1.0
 */

public class LoaderException extends Throwable {

    /**
     * The exception's message.
     */
    private String message;

    /**
     * @param message [String]: The exception's message.
     */
    LoaderException(String message) {
        this.message = message;
    }

    /**
     * @return [String] The exception's message.
     */
    @Override
    public String getMessage() {
        return message;
    }

}
