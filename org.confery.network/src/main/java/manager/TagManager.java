package manager;

import domain.TagEntity;
import domain.UserEntity;
import notification.NotificationCenter;
import protocol.TagProtocol;
import service.TagService;

import java.rmi.RemoteException;
import java.util.List;

import static utils.Try.runFunction;

/**
 * Tested: True
 *
 * @author Teodorescu Vlad
 * @version 1.0
 */
public class TagManager implements TagService {
    private TagProtocol tagModel;
    private UserEntity activeUser;
    private NotificationCenter notificationCenter;

    public TagManager(TagProtocol tagModel, NotificationCenter notificationCenter) {
        this.tagModel = tagModel;
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
    public Integer add(TagEntity element) throws RemoteException {
        return runFunction(tagModel::add, element)
                .orThrow(exception -> new RemoteException(exception.getMessage()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TagEntity delete(TagEntity element) throws RemoteException {
        return runFunction(tagModel::delete, element)
                .orThrow(exception -> new RemoteException(exception.getMessage()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(TagEntity element, TagEntity with) throws RemoteException {
        runFunction(tagModel::update, element, with)
                .orThrow(exception -> new RemoteException(exception.getMessage()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TagEntity getElementById(Integer integer) throws RemoteException {
        return runFunction(tagModel::getElementById, integer)
                .orThrow(exception -> new RemoteException(exception.getMessage()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TagEntity> getAll() {
        return tagModel.getAll();
    }
}