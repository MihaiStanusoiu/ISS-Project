package database;

import java.io.IOException;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

@SuppressWarnings("all")
public class DatabaseLoaderFactory {


    /**
     * Effect: Returns the required database loader.
     *
     * @param type The database loader type. (default or test)
     * @return The requested database loader based on type.
     * @throws IOException If the database configuration file
     *                     (the default one or the one for testing) is missing from resources.
     */
    public DatabaseLoaderInterface getLoader(DatabaseLoaderType type) throws IOException {
        switch (type) {
            case TEST:
                return new DatabaseLoaderBuilder()
                        .setPropertiesFileName("database_url")
                        .setDatabaseUrlKey("test_database")
                        .build();
            default:
                return new DatabaseLoader();
        }
    }

}
