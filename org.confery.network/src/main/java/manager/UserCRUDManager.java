package manager;

import domain.UserEntity;
import notification.NotificationCenter;
import protocol.UserProtocol;
import service.UserCRUDService;

import java.rmi.RemoteException;
import java.util.List;

import static utils.Try.runFunction;

/**
 * Tested: True
 *
 * @author Teodorescu Vlad
 * @version 1.0
 */
public class UserCRUDManager implements UserCRUDService {
    private UserProtocol userModel;
    private UserEntity activeUser;
    private NotificationCenter notificationCenter;

    public UserCRUDManager(UserProtocol userModel, NotificationCenter notificationCenter) {
        this.userModel = userModel;
        this.notificationCenter = notificationCenter;
    }

    @Override
    public void setActiveUser(UserEntity user) {
        activeUser = user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer add(UserEntity element) throws RemoteException {
        return runFunction(userModel::add, element)
                .orThrow(exception -> new RemoteException(exception.getMessage()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserEntity delete(UserEntity element) throws RemoteException {
        return runFunction(userModel::delete, element)
                .orThrow(exception -> new RemoteException(exception.getMessage()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(UserEntity element, UserEntity with) throws RemoteException {
        runFunction(userModel::update, element, with)
                .orThrow(exception -> new RemoteException(exception.getMessage()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserEntity getElementById(Integer integer) throws RemoteException {
        return runFunction(userModel::getElementById, integer)
                .orThrow(exception -> new RemoteException(exception.getMessage()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserEntity> getAll() {
        return userModel.getAll();
    }
}
