package utils;

import database.DatabaseLoader;
import database.DatabaseLoaderFactory;
import database.DatabaseLoaderType;
import domain.UserEntity;
import exception.SystemException;
import exception.ValidatorSystemException;
import javassist.tools.rmi.RemoteException;
import model.UserModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class TryTest {

    private Try<UserEntity, SystemException> tryUser;
    private Try<UserEntity, SystemException> tryException;
    private UserEntity user;
    private SystemException exception;
    private UserModel model;

    @Before
    public void setUp() throws Exception {
        user = new UserEntity("username", "password");
        exception = new ValidatorSystemException("Test");
        tryUser = new Try<>(user);
        tryException = new Try<>(exception);
        DatabaseLoader loader = (DatabaseLoader) new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        model = new UserModel(loader);
    }

    @Test
    public void isGetting() throws Exception {
        // then: [for element]
        assertEquals(tryUser.getElement(), user);
        assertEquals(tryUser.hasException(), Boolean.FALSE);
        // then: [for exception]
        assertEquals(tryException.getException(), exception);
        assertEquals(tryException.hasElement(), Boolean.FALSE);
    }

    @Test
    public void isGettingElementOrReturnsException() throws Exception {
        try {
            // when: [we know this will throw an exception]
            tryException.orThrow(exception -> new RemoteException(exception.getMessage()));
        } catch (RemoteException exception) {
            // then: [check the exception's type and message]
            assertTrue(exception.getMessage().equals(this.exception.getMessage()));
        }
    }

    private Integer function(Integer input) throws SystemException {
        if (input % 2 == 0) throw new ValidatorSystemException("Test");
        return input + 1;
    }

    @Test
    public void isRunning() throws Exception {
        // declarations:
        UserEntity user =  new UserEntity("username","password");
        UserEntity with =  new UserEntity("username2","password");
        // then: [test model user add]
        assertTrue(runFunction(model::add, user)
                .orThrow(exception -> new RemoteException(exception.getMessage())).equals(1));
        // then: [test model user update]
        assertTrue(Try.runMethod(model::update, user, with)
                .orThrow(exception -> new RemoteException(exception.getMessage())).equals(true));
        // then: [test model user getAll]
        assertTrue(runFunction(model::getAll)
                .orThrow(exception -> new RemoteException(exception.getMessage()))
                .get(0).getUsername().equals("username2"));
        // then: [test model user getElementById]
        assertTrue(runFunction(model::getElementById, user.getId())
                .orThrow(exception -> new RemoteException(exception.getMessage()))
                .getUsername().equals("username2"));
        try {
            // when: [we throw something]
            runFunction(this::function, 2)
                    .orThrow(exception -> new RemoteException(exception.getMessage()));
        } catch (RemoteException exception) {
            // then: [test to see if it's the right type and message]
            assertTrue(exception.getMessage().equals(this.exception.getMessage()));
        }
    }
}