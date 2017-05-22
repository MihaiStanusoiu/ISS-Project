package database;

import org.hibernate.cfg.Configuration;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class DatabaseLoaderFactoryTest {

    @Test
    public void getLoader() throws Exception {
        // declaration:
        DatabaseLoaderInterface defaultLoader =
                new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.DEFAULT);
        Configuration defaultConfiguration = defaultLoader.getConfiguration();
        DatabaseLoaderInterface testLoader =
                new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        Configuration testConfiguration = testLoader.getConfiguration();
        // then:
        assertFalse(testConfiguration.equals(defaultConfiguration));
    }

}