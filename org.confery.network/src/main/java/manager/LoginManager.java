package manager;

import domain.UserEntity;
import exception.SystemException;
import notification.NotificationCenter;
import org.jetbrains.annotations.NotNull;
import protocol.UserProtocol;
import service.LoginService;
import transfarable.User;
import translator.GenericTranslator;
import translator.UserTranslator;

import java.rmi.RemoteException;
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
    private UserEntity active;
    private GenericTranslator<UserEntity, User> translator;

    public LoginManager(NotificationCenter notificationCenter, UserProtocol userModel) throws RemoteException {
        this.notificationCenter = notificationCenter;
        this.userModel = userModel;
        translator = new UserTranslator();
    }

    private Optional<User> findUser(String username, String password) throws SystemException {
        return userModel.getAll().stream()
                .map(entity -> translator.translate(entity))
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst();
    }

    @Override
    public User login(@NotNull String username, @NotNull String password) throws RemoteException {
        basedOn(active == null).orThrow(new RemoteException("You're already logged with another account!"));
        return runFunction(this::findUser, username, password)
                .orThrow(exception -> new RemoteException("Wrong Username or Password!"))
                .orElseThrow(() -> new RemoteException("Wrong Username or Password!"));
    }

    @Override
    public void activeUser(User user) throws RemoteException {
        active = translator.translate(user);
    }
}
