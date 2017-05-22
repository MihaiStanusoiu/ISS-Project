package database;

import org.hibernate.cfg.Configuration;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class DatabaseLoaderBuilderTest {

    @Test
    public void build() throws Exception {
        // when:
        DatabaseLoaderInterface defaultLoader = new DatabaseLoaderBuilder()
                .setPropertiesFileName("database_url")
                .setDatabaseUrlKey("default_database")
                .build();
        Configuration defaultConfiguration = defaultLoader.getConfiguration();
        DatabaseLoaderInterface testLoader = new DatabaseLoaderBuilder()
                .setPropertiesFileName("database_url")
                .setDatabaseUrlKey("test_database")
                .build();
        Configuration testConfiguration = testLoader.getConfiguration();
        // then:
        assertFalse(testConfiguration.equals(defaultConfiguration));
    }

}