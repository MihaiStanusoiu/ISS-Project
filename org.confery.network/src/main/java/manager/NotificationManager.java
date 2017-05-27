package manager;

import domain.NotificationEntity;
import domain.UserEntity;
import notification.NotificationCenter;
import protocol.NotificationProtocol;
import service.NotificationService;

import java.rmi.RemoteException;
import java.util.List;

import static utils.Try.runFunction;

/**
 * Tested: True
 *
 * @author Teodorescu Vlad
 * @version 1.0
 */
public class NotificationManager implements NotificationService {

    private NotificationProtocol notificationModel;
    private UserEntity activeUser;
    private NotificationCenter notificationCenter;

    public NotificationManager(NotificationProtocol notificationModel, NotificationCenter notificationCenter) {
        this.notificationModel = notificationModel;
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
    public Integer add(NotificationEntity element) throws RemoteException {
        return runFunction(notificationModel::add, element)
                .orThrow(exception -> new RemoteException(exception.getMessage()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NotificationEntity delete(NotificationEntity element) throws RemoteException {
        return runFunction(notificationModel::delete, element)
                .orThrow(exception -> new RemoteException(exception.getMessage()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(NotificationEntity element, NotificationEntity with) throws RemoteException {
        runFunction(notificationModel::update, element, with)
                .orThrow(exception -> new RemoteException(exception.getMessage()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NotificationEntity getElementById(Integer integer) throws RemoteException {
        return runFunction(notificationModel::getElementById, integer)
                .orThrow(exception -> new RemoteException(exception.getMessage()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<NotificationEntity> getAll() {
        return notificationModel.getAll();
    }
}
