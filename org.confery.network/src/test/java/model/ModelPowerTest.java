package model;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.UserEntity;
import exception.RepositoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */


class ModelPowerTest {

    private Model<UserEntity, Integer> model;

    @BeforeEach
    public void setUp() throws Exception {
        DatabaseLoaderInterface loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        model = new Model<>(UserEntity.class, loader);
    }

    @Test
    public void isAddingObject() throws Exception {
        UserEntity user = new UserEntity("username", "password");
        UserEntity except = new UserEntity("username", "");
        try {
            assertTrue(runFunction(model::add, user)
                    .orThrow(exception -> new RemoteException(exception.getMessage())).equals(1));
            runFunction(model::add, except).orThrow(exception -> new RemoteException(exception.getMessage()));
        } catch (RemoteException exception) {
            assertTrue(exception.getMessage().equals("\nUser's password is invalid!"));
        }
    }

    @Test
    public void isUpdatingObject() throws Exception {
        UserEntity user = new UserEntity("username", "password");
        UserEntity with = new UserEntity("with", "password");
        UserEntity except = new UserEntity("with", null);
        runFunction(model::add, user).orThrow(exception -> new RemoteException(exception.getMessage()));
        assertTrue(runFunction(model::update, user, with)
                .orThrow(exception -> new RemoteException(exception.getMessage())));
        try {
            runFunction(model::update, user, except).orThrow(exception -> new RemoteException(exception.getMessage()));
        } catch (RemoteException exception) {
            assertTrue(exception.getMessage().equals("\nUser's password is invalid!"));
        }
    }
    @Test
    public void isDeletingObject() throws Exception {
        UserEntity user = new UserEntity("username", "password");
        UserEntity with = new UserEntity("with", "password");
        runFunction(model::add, user).orThrow(exception -> new RemoteException(exception.getMessage()));
        runFunction(model::delete, user).orThrow(exception -> new RemoteException(exception.getMessage()));
        assertThrows(RepositoryException.class, () -> model.delete(with));
    }

}