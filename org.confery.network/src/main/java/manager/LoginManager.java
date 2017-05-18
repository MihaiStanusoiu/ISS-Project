package manager;

import convertor.UserConverter;
import domain.UserEntity;
import exception.RepositoryException;
import model.UserProtocol;
import notification.NotificationCenter;
import service.LoginService;
import transferable.User;

import java.rmi.RemoteException;
import java.util.Optional;

/**
 * Tested: True
 * @author Alexandru Stoica
 * @version 1.0
 */

public class LoginManager implements LoginService {

    @SuppressWarnings("all")
    private NotificationCenter notificationCenter;
    private UserProtocol userModel;

    public LoginManager(NotificationCenter notificationCenter, UserProtocol userModel) throws RemoteException {
        this.notificationCenter = notificationCenter;
        this.userModel = userModel;
    }

    private Optional<UserEntity> findUser(String username, String password) throws RepositoryException {
        return userModel.getAll().stream().filter(user ->
                user.getUsername().equals(username) && user.getPassword().equals(password)).findFirst();
    }

    @Override
    public User login(String username, String password) throws RemoteException, RepositoryException {
        return UserConverter.convertUserEntity(findUser(username, password)
                .orElseThrow(() -> new RemoteException("Wrong Username or Password!")));
    }
}
