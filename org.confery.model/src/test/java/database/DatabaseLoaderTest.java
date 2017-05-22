package database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

import static org.junit.Assert.assertTrue;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class DatabaseLoaderTest {

    private DatabaseLoaderInterface loader;

    @Before
    public void setUp() throws Exception {
        Properties properties = new Properties();
        String databaseUrl = ResourceBundle
                .getBundle("database_url")
                .getString("test_database");
        InputStream stream = getClass().getResourceAsStream(databaseUrl);
        properties.load(stream);
        Configuration configuration = new Configuration().addProperties(properties);
        loader = new DatabaseLoader(configuration);
    }

    @Test
    public void setConfiguration() throws Exception {
        // declaration:
        Properties properties = new Properties();
        String databaseUrl = ResourceBundle
                .getBundle("database_url")
                .getString("test_database");
        InputStream stream = getClass().getResourceAsStream(databaseUrl);
        // preconditions:
        properties.load(stream);
        Configuration configuration = new Configuration().addProperties(properties);
        // when:
        loader.setConfiguration(configuration);
        // then:
        assertTrue(loader.getConfiguration().equals(configuration));
    }

    @Test
    public void getConfiguration() throws Exception {
        // declaration:
        Configuration configuration = loader.getConfiguration();
        // when:
        assertTrue(configuration != null);
    }

    @Test
    public void getFactory() throws Exception {
        // declaration:
        SessionFactory factory = loader.getFactory();
        // when:
        assertTrue(factory != null);
    }

}