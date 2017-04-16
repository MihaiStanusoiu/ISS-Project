package database;

import org.hibernate.cfg.Configuration;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * Name:         DatabaseLoaderFactoryTest
 * Effect:       Test for database loader factory.
 * Date:         4/16/2017
 *
 * @author      Alexandru Stoica
 * @version     1.0
 */
public class DatabaseLoaderFactoryTest {

    @Test
    public void getLoader() throws Exception {
        DatabaseLoaderInterface defaultLoader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.DEFAULT);
        Configuration defaultConfiguration = defaultLoader.getConfiguration();
        DatabaseLoaderInterface testLoader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        Configuration testConfiguration = testLoader.getConfiguration();
        assertFalse(testConfiguration.equals(defaultConfiguration));
    }

}