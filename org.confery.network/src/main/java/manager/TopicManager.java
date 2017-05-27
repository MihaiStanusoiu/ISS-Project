package manager;

import domain.TopicEntity;
import domain.UserEntity;
import notification.NotificationCenter;
import protocol.TopicProtocol;
import service.TopicService;

import java.rmi.RemoteException;
import java.util.List;

import static utils.Try.runFunction;

/**
 * Tested: True
 *
 * @author Teodorescu Vlad
 * @version 1.0
 */
public class TopicManager implements TopicService {
    private TopicProtocol topicModel;
    private UserEntity activeUser;
    private NotificationCenter notificationCenter;

    public TopicManager(TopicProtocol topicModel, NotificationCenter notificationCenter) {
        this.topicModel = topicModel;
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
    public Integer add(TopicEntity element) throws RemoteException {
        return runFunction(topicModel::add, element)
                .orThrow(exception -> new RemoteException(exception.getMessage()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TopicEntity delete(TopicEntity element) throws RemoteException {
        return runFunction(topicModel::delete, element)
                .orThrow(exception -> new RemoteException(exception.getMessage()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(TopicEntity element, TopicEntity with) throws RemoteException {
        runFunction(topicModel::update, element, with)
                .orThrow(exception -> new RemoteException(exception.getMessage()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TopicEntity getElementById(Integer integer) throws RemoteException {
        return runFunction(topicModel::getElementById, integer)
                .orThrow(exception -> new RemoteException(exception.getMessage()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TopicEntity> getAll() {
        return topicModel.getAll();
    }
}