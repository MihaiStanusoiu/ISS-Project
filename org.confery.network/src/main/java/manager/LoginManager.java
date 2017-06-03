package manager;

import domain.UserEntity;
import exception.SystemException;
import org.jetbrains.annotations.NotNull;
import protocol.LoginProtocol;
import protocol.UserProtocol;
import service.LoginService;
import transfarable.NullUser;
import transfarable.User;
import translator.GenericTranslator;
import translator.UserTranslator;

import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.UnicastRemoteObject;

import static utils.Conditional.basedOn;
import static utils.Try.runFunction;

/**
 * Tested: True
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class LoginManager
        extends UnicastRemoteObject
        implements LoginService {

    private UserProtocol userModel;
    private LoginProtocol provider;
    private GenericTranslator<UserEntity, User> translator;

    public LoginManager(UserProtocol userModel, LoginProtocol loginProtocol) throws RemoteException {
        this.userModel = userModel;
        provider = loginProtocol;
        translator = new UserTranslator();
    }

    private @NotNull User findUser(String username, String password) throws SystemException {
        return userModel.getAll().stream()
                .map(entity -> translator.translate(entity))
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst().orElse(new NullUser());
    }

    private UserEntity getActiveUser() throws RemoteException {
        String host = runFunction(RemoteServer::getClientHost)
                .orThrow(exception -> new RemoteException(exception.getMessage()));
        return runFunction(provider::getUserByIp, host).getElement();
    }

    @Override
    public @NotNull User login(@NotNull String username, @NotNull String password) throws RemoteException {
        basedOn(getActiveUser().getId().equals(0)).orThrow(new RemoteException("You're already logged with another account!"));
        return runFunction(this::findUser, username, password)
                .orThrow(exception -> new RemoteException("Wrong Username or Password!"));
    }
}
