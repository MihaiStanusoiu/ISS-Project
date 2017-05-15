package utils;

import domain.UserEntity;
import exception.RepositoryException;
import exception.ValidatorRepositoryException;
import javassist.tools.rmi.RemoteException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class TryTest {

    private Try<UserEntity, RepositoryException> tryUser;
    private Try<UserEntity, RepositoryException> tryException;
    private UserEntity user;
    private RepositoryException exception;

    @Before
    public void setUp() throws Exception {
        user = new UserEntity("username", "password");
        exception = new ValidatorRepositoryException("Test");
        tryUser = new Try<>(user);
        tryException = new Try<>(exception);
    }

    @Test
    public void isGetting() throws Exception {
        tryUser = new Try<>(user);
        tryException = new Try<>(exception);
        // Test my Try objects for both cases.
        Assert.assertTrue(tryUser.getElement().equals(user)
                && tryUser.hasException().equals(Boolean.FALSE));
        Assert.assertTrue(tryException.getException().equals(exception) &&
                tryException.hasElement().equals(Boolean.FALSE));
    }

    @Test
    public void isGettingElementOrReturnsException() throws Exception {
        Assert.assertTrue(tryUser.get().equals(user));
        Assert.assertTrue(tryException.get().equals(exception));
        Assert.assertTrue(tryUser.orThrow(exception ->
                new RemoteException(exception.getMessage())).getUsername().equals("username"));
        try {
            Assert.assertTrue(tryException.orThrow(exception ->
                    new RemoteException(exception.getMessage())).getUsername().equals("username"));
        } catch (RemoteException exception) {
            Assert.assertTrue(exception.getMessage().equals(this.exception.getMessage()));
        }
    }
}