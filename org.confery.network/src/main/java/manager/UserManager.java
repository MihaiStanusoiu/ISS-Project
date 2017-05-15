package manager;

import com.google.common.collect.Lists;
import convertor.UserConverter;
import exception.RepositoryException;
import model.UserProtocol;
import notification.NotificationCenter;
import service.UserService;
import transferable.User;

import java.rmi.RemoteException;
import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class UserManager implements UserService {

    @SuppressWarnings("all")
    private NotificationCenter notificationCenter;
    private UserProtocol userModel;

    public UserManager(NotificationCenter notificationCenter, UserProtocol userModel) throws RemoteException {
        this.notificationCenter = notificationCenter;
        this.userModel = userModel;
    }

    @Override
    public Integer add(User user) throws RemoteException, RepositoryException {
        return userModel.add(UserConverter.convertUser(user));
    }

    @Override
    public User delete(User user) throws RemoteException, RepositoryException {
        return UserConverter.convertUserEntity(userModel.delete(UserConverter.convertUser(user)));
    }

    @Override
    public void update(User user, User with) throws RemoteException, RepositoryException {
        userModel.update(UserConverter.convertUser(user), UserConverter.convertUser(with));
    }

    @Override
    public User getUserById(Integer id) throws RemoteException, RepositoryException {
        return UserConverter.convertUserEntity(userModel.getElementById(id));
    }

    @Override
    public List<User> getAll() throws RemoteException, RepositoryException {
        return Lists.transform(userModel.getAll(), UserConverter::convertUserEntity);
    }
}
