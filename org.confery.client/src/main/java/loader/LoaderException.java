package loader;

/**
 * Name:        LoaderException
 * Effect:      Special exception for the fxml loader.
 * Date:        09/04/2017
 * Tested:      False
 *
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
