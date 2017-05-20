package domain;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import org.junit.Assert;
import org.junit.Test;
import repository.RepositoryEntity;
import repository.RepositoryInterface;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ConfigurationSessionFactoryTest {
    @Test
    public void isGettingConfiguration() throws Exception {
        ConfigurationSessionMemberEntity configuration =
                new ConfigurationSessionMemberEntity(true, false, false);
        DatabaseLoaderInterface loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        RepositoryInterface<ConfigurationSessionMemberEntity, Integer> repository =
                new RepositoryEntity<>(ConfigurationSessionMemberEntity.class, loader);
        ConfigurationSessionMemberEntity test = new ConfigurationSessionFactory(repository).get(configuration);
        System.out.print(repository.getAll().get(0));
        Assert.assertTrue(test.equals(configuration) &&
            repository.getAll().get(0).equals(test));
    }
}