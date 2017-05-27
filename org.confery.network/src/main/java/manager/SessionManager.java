package manager;

import domain.SessionEntity;
import domain.UserEntity;
import notification.NotificationCenter;
import protocol.SessionProtocol;
import service.SessionService;

import java.rmi.RemoteException;
import java.util.List;

import static utils.Try.runFunction;

/**
 * Tested: True
 *
 * @author Teodorescu Vlad
 * @version 1.0
 */
public class SessionManager implements SessionService {

    private SessionProtocol sessionModel;
    private UserEntity activeUser;
    private NotificationCenter notificationCenter;

    public SessionManager(SessionProtocol sessionModel, NotificationCenter notificationCenter) {
        this.sessionModel = sessionModel;
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
    public Integer add(SessionEntity element) throws RemoteException {
        return runFunction(sessionModel::add, element)
                .orThrow(exception -> new RemoteException(exception.getMessage()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SessionEntity delete(SessionEntity element) throws RemoteException {
        return runFunction(sessionModel::delete, element)
                .orThrow(exception -> new RemoteException(exception.getMessage()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(SessionEntity element, SessionEntity with) throws RemoteException {
        runFunction(sessionModel::update, element, with)
                .orThrow(exception -> new RemoteException(exception.getMessage()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SessionEntity getElementById(Integer integer) throws RemoteException {
        return runFunction(sessionModel::getElementById, integer)
                .orThrow(exception -> new RemoteException(exception.getMessage()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SessionEntity> getAll() {
        return sessionModel.getAll();
    }
}
