package manager;

import domain.EditionEntity;
import domain.UserEntity;
import exception.SystemException;
import notification.NotificationCenter;
import protocol.EditionProtocol;
import service.EditionService;

import java.rmi.RemoteException;
import java.util.List;

import static utils.Try.runFunction;

/**
 * Tested: True
 *
 * @author Teodorescu Vlad
 * @version 1.0
 */
public class EditionManager implements EditionService {

    private EditionProtocol editionModel;
    private UserEntity activeUser;
    private NotificationCenter notificationCenter;

    public EditionManager(EditionProtocol editionModel, NotificationCenter notificationCenter) {
        this.editionModel = editionModel;
        this.notificationCenter = notificationCenter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer add(EditionEntity element) throws RemoteException {
        return runFunction(editionModel::add, element)
                .orThrow(exception -> new RemoteException(exception.getMessage()));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public EditionEntity delete(EditionEntity element) throws RemoteException {
        return runFunction(editionModel::delete, element)
                .orThrow(exception -> new RemoteException(exception.getMessage()));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(EditionEntity element, EditionEntity with) throws RemoteException {
        runFunction(editionModel::update, element, with)
                .orThrow(exception -> new RemoteException(exception.getMessage()));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public EditionEntity getElementById(Integer integer) throws RemoteException {
        return runFunction(editionModel::getElementById, integer)
                .orThrow(exception -> new RemoteException(exception.getMessage()));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<EditionEntity> getAll() {
        return editionModel.getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setActiveUser(UserEntity user) {
        activeUser = user;
    }
}
