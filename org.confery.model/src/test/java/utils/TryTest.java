package utils;

import database.DatabaseLoader;
import database.DatabaseLoaderFactory;
import database.DatabaseLoaderType;
import domain.UserEntity;
import exception.RepositoryException;
import exception.ValidatorRepositoryException;
import javassist.tools.rmi.RemoteException;
import model.UserModel;
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

    private Integer function(Integer input) throws RepositoryException {
        if (input % 2 == 0) throw new ValidatorRepositoryException("Test");
        return input + 1;
    }

    private Integer sum(Integer left, Integer right) throws RepositoryException {
        if (left + right == 0) throw new ValidatorRepositoryException("Test");
        return left + right;
    }

    @Test
    public void isRunning() throws Exception {
        DatabaseLoader loader = (DatabaseLoader) new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        UserModel model = new UserModel(loader);
        UserEntity user =  new UserEntity("username","password");
        UserEntity with =  new UserEntity("username2","password");

        Assert.assertTrue(Try.runFunction(model::add, user)
                .orThrow(exception -> new RemoteException(exception.getMessage())).equals(1));

        Assert.assertTrue(Try.runFunction(model::update, user, with)
                .orThrow(exception -> new RemoteException(exception.getMessage())).equals(true));

        Assert.assertTrue(Try.runFunction(model::getAll)
                .orThrow(exception -> new RemoteException(exception.getMessage()))
                .get(0).getUsername().equals("username2"));

        // Model Test Get Element By Id
        Assert.assertTrue(Try.runFunction(model::getElementById, user.getId())
                .orThrow(exception -> new RemoteException(exception.getMessage()))
                .getUsername().equals("username2"));
        try {
            Try.runFunction(this::function, 2).orThrow(exception ->
                    new RemoteException(exception.getMessage()));
        } catch (RemoteException exception) {
            Assert.assertTrue(exception.getMessage().equals(this.exception.getMessage()));
        }
    }
}