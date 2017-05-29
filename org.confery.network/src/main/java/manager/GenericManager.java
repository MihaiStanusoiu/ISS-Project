package manager;

import com.google.common.base.Function;
import domain.Idable;
import domain.UserEntity;
import exception.SystemException;
import protocol.ModelInterface;
import service.Service;
import transferable.User;
import translator.UserTranslator;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class GenericManager<T extends Idable<Id>, Id extends Serializable>
        extends UnicastRemoteObject
        implements Service<T, Id> {

    protected ModelInterface<T, Id> model;
    protected UserEntity active;
    private Function<SystemException, RemoteException> thrower;

    public GenericManager(ModelInterface<T, Id> model) throws RemoteException{
        this.model = model;
        thrower = exception -> new RemoteException(exception != null ? exception.getMessage() : null);
    }

    @Override
    public Id add(T element) throws RemoteException {
        return runFunction(model::add, element).orThrow(thrower);
    }

    @Override
    public T update(T element, T with) throws RemoteException {
        runFunction(model::update, element, with).orThrow(thrower);
        return runFunction(model::getElementById, element.getId()).orThrow(thrower);
    }

    @Override
    public T delete(T element) throws RemoteException {
        return runFunction(model::delete, element).orThrow(thrower);
    }

    @Override
    public T getElementById(Id id) throws RemoteException {
        return runFunction(model::getElementById, id).orThrow(thrower);
    }

    @Override
    public List<T> getAll() throws RemoteException {
        return model.getAll();
    }

    @Override
    public void activeUser(User user) throws RemoteException {
        UserEntity userEntity = UserTranslator.translate(user);
        active = userEntity;
    }
}
