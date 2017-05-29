package manager;

import domain.UserEntity;
import exception.SystemException;
import notification.NotificationCenter;
import protocol.UserProtocol;
import service.LoginService;
import translator.UserTranslator;

import java.rmi.RemoteException;
import java.util.Optional;

import static utils.Try.runFunction;

/**
 * Tested: True
 * @author Alexandru Stoica
 * @version 1.0
 */

public class LoginManager implements LoginService {

    private NotificationCenter notificationCenter;
    private UserProtocol userModel;
    private UserEntity active;

    public LoginManager(NotificationCenter notificationCenter, UserProtocol userModel) throws RemoteException {
        this.notificationCenter = notificationCenter;
        this.userModel = userModel;
    }

    private Optional<UserEntity> findUser(String username, String password) throws SystemException {
        return userModel.getAll().stream().filter(user ->
                user.getUsername().equals(username) && user.getPassword().equals(password)).findFirst();
    }

    @Override
    public User login(String username, String password) throws RemoteException {
        return UserTranslator.translate(
                runFunction(this::findUser, username, password)
                .orThrow(exception -> new RemoteException("Wrong Username or Password!"))
                .orElseThrow(() -> new RemoteException("Wrong Username or Password!"))
        );
    }

    @Override
    public void activeUser(User user) throws RemoteException {
        UserEntity userEntity = UserTranslator.translate(user);
        active = userEntity;
    }
}
