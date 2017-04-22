package database;

import domain.*;
import domaintest.NotificationEntityMock;
import domaintest.UserEntityMock;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Name:         DatabaseLoader
 * Effect:       Loads the database using a Hibernate configuration.
 *               <p>
 *                   The configuration is preset by default
 *                   if you're using the empty constructor.
 *
 *                   If you need to test the system the loader needs
 *                   another configuration (for testing) and you can
 *                   set it up using the constructor with configuration DI
 *                   or by using the setConfiguration method.
 *
 *                   Recommended: Use the constructor when you test the database.
 *
 *                   If you use the setConfiguration method, by default you will
 *                   force the loader to use the default configuration and the whole
 *                   loading process will slow down your test / code.
 *               </p>
 * Date:         4/16/2017
 * Tested:       True
 *
 * @author      Alexandru Stoica
 * @version     1.0
 */

public class DatabaseLoader
        implements DatabaseLoaderInterface {

    /**
     * The hibernate configuration.
     */
    private Configuration configuration;

    /**
     * The session factory
     * -- creates a session that allows you to edit the database.
     */
    protected SessionFactory factory;

    DatabaseLoader(Configuration configuration) {
        this.configuration = configuration;
        load();
    }

    /**
     * Effect: Creates the database loader with a default configuration for your system.
     * @throws IOException If you don't have the default database
     * configuration file set up. (as a property file in your resources)
     */
    @SuppressWarnings("unused")
    DatabaseLoader() throws IOException {
        Properties properties = new Properties();
        String databaseUrl = ResourceBundle
                .getBundle("database_url")
                .getString("default_database");
        InputStream stream = getClass().getResourceAsStream(databaseUrl);
        properties.load(stream);
        configuration = new Configuration().addProperties(properties);
        load();
    }

    /**
     * Effect: Sets up the configuration with the domain tables.
     */
    private void setUpConfiguration() {
        configuration.addAnnotatedClass(UserEntityMock.class);
        configuration.addAnnotatedClass(NotificationEntityMock.class);
        configuration.addAnnotatedClass(ConfigurationEditionMemberEntity.class);
        configuration.addAnnotatedClass(ConfigurationSessionMemberEntity.class);
        configuration.addAnnotatedClass(ConferenceEntity.class);
        configuration.addAnnotatedClass(EditionEntity.class);
        configuration.addAnnotatedClass(EditionMemberEntity.class);
        configuration.addAnnotatedClass(SessionEntity.class);
        configuration.addAnnotatedClass(UserEntity.class);
        configuration.addAnnotatedClass(SessionMemberEntity.class);
    }

    /**
     * Effect: Loads the session factory based on the current configuration.
     */
    private void load() {
        setUpConfiguration();
        factory = configuration.buildSessionFactory();
    }

    /**
     * {@inheritDoc}
     * @param configuration The hibernate configuration
     */
    @Override
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
        load();
    }

    /**
     * Effect: Returns the hibernate configuration of our loader.
     * Useful if we need to test the current configuration.
     * @return The hibernate configuration.
     */
    @Override
    public Configuration getConfiguration() {
        return this.configuration;
    }
    /**
     * Effect: Returns the hibernate's factory.
     * Useful is we need to work with multiple databases in the same time.
     * @return The hibernate's database factory.
     */
    @Override
    public SessionFactory getFactory() {
        return factory;
    }

}
