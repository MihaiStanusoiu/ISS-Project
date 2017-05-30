package database;

import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Builder for database loader.
 *
 * <p>
 *      The builder returns the loader based on the configuration's URL.
 *      We set this URL by giving the builder a file of properties
 *      and a key (the wanted property value in the file).
 * </p>
 * <p>
 *     The builder creates the wanted configuration and builds a loader using that configuration.
 * </p>
 * <p>
 *      You can also use the factory if it's more convenient,
 *      but the factory is also using this builder.
 *      -- because the factory just returns a loader based on type.
 * </p>
 *
 * Tested:       True
 *
 * @author       Alexandru Stoica
 * @version      1.0
 */

public class DatabaseLoaderBuilder {


    /**
     * The properties file's name where the database configuration file url is stored.
     */
    private String propertiesFileName;

    /**
     * The property's key in the properties file. (example: test_database)
     */
    private String databaseUrlKey;

    /**
     * @param propertiesFileName The name of the properties file
     *                           where we store the database configurations url.
     * @return The builder for later chaining.
     */
    @SuppressWarnings("all")
    public DatabaseLoaderBuilder setPropertiesFileName(String propertiesFileName) {
        this.propertiesFileName = propertiesFileName;
        return this;
    }

    /**
     * @param databaseUrlKey The key for the configuration's url (for the wanted database).
     * @return The builder for later chaining.
     */
    @SuppressWarnings("all")
    public DatabaseLoaderBuilder setDatabaseUrlKey(String databaseUrlKey) {
        this.databaseUrlKey = databaseUrlKey;
        return this;
    }

    /**
     * @return The loader that is based on the wanted configuration.
     * @throws IOException If the properties file is not found.
     */
    public DatabaseLoaderInterface build() throws IOException {
        Properties properties = new Properties();
        Configuration configuration;
        String databaseUrl = ResourceBundle
                .getBundle(propertiesFileName)
                .getString(databaseUrlKey);
        InputStream stream = getClass().getResourceAsStream(databaseUrl);
        properties.load(stream);
        configuration = new Configuration().addProperties(properties);
        return new DatabaseLoader(configuration);
    }

}
