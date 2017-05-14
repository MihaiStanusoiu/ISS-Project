package manager;

import convertor.UserConvertor;
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

    private NotificationCenter notificationCenter;
    private UserProtocol userModel;

    public LoginManager(NotificationCenter notificationCenter, UserProtocol userModel) throws RemoteException {
        this.notificationCenter = notificationCenter;
        this.userModel = userModel;
    }

    private Optional<UserEntity> findUser(String username, String password) throws RepositoryException {
        return userModel.getUsers().stream().filter(user ->
                user.getUsername().equals(username) && user.getPassword().equals(password)).findFirst();
    }

    @Override
    public User login(String username, String password) throws RemoteException, RepositoryException {
        return new UserConvertor().convertUserEntity(findUser(username, password)
                .orElseThrow(() -> new RemoteException("Wrong Username or Password!")));
    }
}
