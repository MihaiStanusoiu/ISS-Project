package repository;

/**
 * Name:         RepositoryException
 * Effect:       Defines the behavior of a repository exception.
 * Date:         4/16/2017
 * Tested:       False
 *
 * @author      Alexandru Stoica
 * @version     1.0
 */

public abstract class RepositoryException extends Throwable {

    /**
     * @return The exception's message.
     */
    public abstract String getMessage();

}
