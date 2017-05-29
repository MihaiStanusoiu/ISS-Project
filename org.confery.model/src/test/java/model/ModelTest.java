package model;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.UserEntity;
import org.junit.Before;
import org.junit.Test;
import utils.Try;

import java.rmi.RemoteException;

import static org.junit.Assert.assertTrue;
import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ModelTest {

    private Model<UserEntity, Integer> model;

    @Before
    public void setUp() throws Exception {
        DatabaseLoaderInterface loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        model = new Model<>(UserEntity.class, loader);
    }

    @Test(expected = RemoteException.class)
    public void isAddingObject() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("username", "password");
        UserEntity except = new UserEntity("username", "");
        // then:
        assertTrue(runFunction(model::add, user)
                .orThrow(exception -> new RemoteException(exception.getMessage())).equals(1));
        // when:
        runFunction(model::add, except).orThrow(exception -> new RemoteException(exception.getMessage()));
    }

    @Test(expected = RemoteException.class)
    public void isUpdatingObject() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("username", "password");
        UserEntity with = new UserEntity("with", "password");
        UserEntity except = new UserEntity("with", null);
        // preconditions:
        runFunction(model::add, user).orThrow(exception -> new RemoteException(exception.getMessage()));
        // then:
        assertTrue(Try.runMethod(model::update, user, with)
                .orThrow(exception -> new RemoteException(exception.getMessage())));
        // then: [test exceptions]
        Try.runMethod(model::update, user, except).orThrow(exception -> new RemoteException(exception.getMessage()));
    }
    @Test(expected = RemoteException.class)
    public void isDeletingObject() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("username", "password");
        UserEntity with = new UserEntity("with", "password");
        // preconditions:
        model.add(user);
        // then:
        runFunction(model::delete, user).orThrow(exception -> new RemoteException(exception.getMessage()));
        // then: [test exceptions]
        runFunction(model::delete, with).orThrow(exception -> new RemoteException(exception.getMessage()));
    }

}