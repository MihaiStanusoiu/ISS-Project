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
 * Name:         DatabaseLoaderTest
 * Effect:       Generic test for Database Loader.
 * Date:         4/16/2017
 *
 * @author      Alexandru Stoica
 * @version     1.0
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
        Properties properties = new Properties();
        String databaseUrl = ResourceBundle
                .getBundle("database_url")
                .getString("test_database");
        InputStream stream = getClass().getResourceAsStream(databaseUrl);
        properties.load(stream);
        Configuration configuration = new Configuration().addProperties(properties);
        loader.setConfiguration(configuration);
        assertTrue(loader.getConfiguration().equals(configuration));
    }

    @Test
    public void getConfiguration() throws Exception {
        Configuration configuration = loader.getConfiguration();
        assertTrue(configuration != null);
    }

    @Test
    public void getFactory() throws Exception {
        SessionFactory factory = loader.getFactory();
        assertTrue(factory != null);
    }

}