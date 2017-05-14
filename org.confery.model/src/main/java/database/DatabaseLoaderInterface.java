package database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Name:         DatabaseLoaderInterface
 * Effect:       Loads the database using Hibernate configuration files.
 * Date:         4/16/2017
 * Tested:       True
 *
 * @author      Alexandru Stoica
 * @version     1.0
 */

public interface DatabaseLoaderInterface {

    /**
     * Effect: Sets the hibernate configuration and reloads the database.
     * Useful in case we need to switch the database.
     * @param configuration The hibernate configuration
     */
    void setConfiguration(Configuration configuration);

    /**
     * Effect: Returns the hibernate configuration of our loader.
     * Useful if we need to test the current configuration.
     * @return The hibernate configuration.
     */
    Configuration getConfiguration();

    /**
     * Effect: Returns the hibernate's factory.
     * Useful is we need to work with multiple databases in the same time.
     * @return The hibernate's database factory.
     */
    SessionFactory getFactory();

}
