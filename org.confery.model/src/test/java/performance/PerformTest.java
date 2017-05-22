package performance;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.UserEntity;
import org.junit.Test;
import repository.RepositoryEntity;
import repository.RepositoryInterface;

import java.util.stream.IntStream;

import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class PerformTest {

    @Test
    public void isRunningSlow() throws Exception {
        // declaration:
        DatabaseLoaderInterface loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        RepositoryInterface<UserEntity, Integer> repository = new RepositoryEntity<>(UserEntity.class, loader);
        UserEntity user = new UserEntity("username", "password");
        // when:
        IntStream.range(0, 10000).forEach(index -> runFunction(repository::add, user).or(0));
        // then:
        repository.getAll().forEach(item -> System.out.print(item.getUsername() + System.lineSeparator()));
    }

}
