package manager;

import domain.SubmissionEntity;
import domain.UserEntity;
import notification.NotificationCenter;
import protocol.SubmissionProtocol;
import service.SubmissionService;

import java.rmi.RemoteException;
import java.util.List;

import static utils.Try.runFunction;

/**
 * Tested: True
 *
 * @author Teodorescu Vlad
 * @version 1.0
 */
public class SubmissionManager implements SubmissionService {
    private SubmissionProtocol submissionModel;
    private UserEntity activeUser;
    private NotificationCenter notificationCenter;

    public SubmissionManager(SubmissionProtocol submissionModel, NotificationCenter notificationCenter) {
        this.submissionModel = submissionModel;
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
    public Integer add(SubmissionEntity element) throws RemoteException {
        return runFunction(submissionModel::add, element)
                .orThrow(exception -> new RemoteException(exception.getMessage()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubmissionEntity delete(SubmissionEntity element) throws RemoteException {
        return runFunction(submissionModel::delete, element)
                .orThrow(exception -> new RemoteException(exception.getMessage()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(SubmissionEntity element, SubmissionEntity with) throws RemoteException {
        runFunction(submissionModel::update, element, with)
                .orThrow(exception -> new RemoteException(exception.getMessage()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubmissionEntity getElementById(Integer integer) throws RemoteException {
        return runFunction(submissionModel::getElementById, integer)
                .orThrow(exception -> new RemoteException(exception.getMessage()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SubmissionEntity> getAll() {
        return submissionModel.getAll();
    }
}