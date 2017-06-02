package manager;

import domain.UserEntity;
import exception.SystemException;
import notification.NotificationCenter;
import org.jetbrains.annotations.NotNull;
import protocol.LoginProtocol;
import protocol.UserProtocol;
import service.LoginService;
import transfarable.User;
import translator.GenericTranslator;
import translator.UserTranslator;

import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.UnicastRemoteObject;
import java.util.Optional;

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

    private NotificationCenter notificationCenter;
    private UserProtocol userModel;
    private LoginProtocol provider;
    private GenericTranslator<UserEntity, User> translator;

    public LoginManager(NotificationCenter notificationCenter, UserProtocol userModel, LoginProtocol loginProtocol) throws RemoteException {
        this.notificationCenter = notificationCenter;
        this.provider = loginProtocol;
        this.userModel = userModel;
        translator = new UserTranslator();
    }

    private Optional<User> findUser(String username, String password) throws SystemException {
        return userModel.getAll().stream()
                .map(entity -> translator.translate(entity))
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst();
    }

    private UserEntity getActiveUser() throws RemoteException {
        String host = runFunction(RemoteServer::getClientHost)
                .orThrow(exception -> new RemoteException(exception.getMessage()));
        return runFunction(provider::getUserByIp, host).getElement();
    }

    @Override
    public User login(@NotNull String username, @NotNull String password) throws RemoteException {
        UserEntity active =  getActiveUser();
        basedOn(active.getId().equals(0)).orThrow(new RemoteException("You're already logged with another account!"));
        return runFunction(this::findUser, username, password)
                .orThrow(exception -> new RemoteException("Wrong Username or Password!"))
                .orElseThrow(() -> new RemoteException("Wrong Username or Password!"));
    }
}
