package manager;

import domain.UserEntity;
import exception.SystemException;
import protocol.ModelInterface;
import service.UserService;
import transfarable.User;
import translator.UserTranslator;
import utils.Try;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class UserManager implements UserService {

    protected ModelInterface<UserEntity, Integer> model;
    protected UserEntity active;
    private Function<SystemException, RemoteException> thrower;

    public UserManager(ModelInterface<UserEntity, Integer> model) throws RemoteException {
        this.model = model;
        thrower = exception -> new RemoteException(exception != null ? exception.getMessage() : null);
    }

    @Override
    public void activeUser(User user) throws RemoteException {
        active = UserTranslator.translate(user);
    }

    @Override
    public Integer add(User element) throws RemoteException {
        return runFunction(model::add, UserTranslator.translate(element)).orThrow(thrower);
    }

    @Override
    public User update(User element, User with) throws RemoteException {
        Try.runMethod(model::update, UserTranslator.translate(element), UserTranslator.translate(with))
                .orThrow(thrower);
        // Check Here
        return with;
    }

    @Override
    public User delete(User element) throws RemoteException {
        return UserTranslator.translate(runFunction(model::delete, UserTranslator.translate(element))
                .orThrow(thrower));
    }

    @Override
    public User getElementById(Integer id) throws RemoteException {
        return UserTranslator.translate(runFunction(model::getElementById, id).orThrow(thrower));
    }

    @Override
    public List<User> getAll() throws RemoteException {
        List<User> transferableUsers = new ArrayList<>();
        List<UserEntity> Users = model.getAll();

        for (UserEntity UserEntity : Users) {
            transferableUsers.add(UserTranslator.translate(UserEntity));
        }

        return transferableUsers;
    }

}
