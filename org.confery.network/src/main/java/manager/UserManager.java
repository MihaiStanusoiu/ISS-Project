package manager;

import domain.UserEntity;
import exception.SystemException;
import protocol.UserProtocol;
import service.UserService;
import transfarable.User;
import translator.UserTranslator;

import java.rmi.RemoteException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static translator.UserTranslator.translate;
import static utils.Try.runFunction;
import static utils.Try.runMethod;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class UserManager implements UserService {

    protected UserProtocol model;
    protected UserEntity active;
    private Function<SystemException, RemoteException> thrower;

    public UserManager(UserProtocol model) throws RemoteException {
        this.model = model;
        thrower = exception -> new RemoteException(exception.getMessage());
    }

    @Override
    public void activeUser(User user) throws RemoteException {
        active = translate(user);
    }

    @Override
    public Integer add(User element) throws RemoteException {
        return runFunction(model::add, translate(element)).orThrow(thrower);
    }

    @Override
    public void update(User element, User with) throws RemoteException {
        runMethod(model::update, translate(element), translate(with)).orThrow(thrower);
    }

    @Override
    public User delete(User element) throws RemoteException {
        return translate(runFunction(model::delete, translate(element)).orThrow(thrower));
    }

    @Override
    public User getElementById(Integer id) throws RemoteException {
        return translate(runFunction(model::getElementById, id).orThrow(thrower));
    }

    @Override
    public List<User> getAll() throws RemoteException {
        return model.getAll().stream().map(UserTranslator::translate).collect(Collectors.toList());
    }

}
