package manager;

import checker.SubmissionPermissionChecker;
import domain.SubmissionEntity;
import domain.UserEntity;
import protocol.SubmissionProtocol;
import service.SubmissionService;
import transfarable.Submission;
import transfarable.Tag;
import transfarable.Topic;
import transfarable.User;
import translator.SubmissionTranslator;
import translator.TagTranslator;
import translator.TopicTranslator;
import translator.UserTranslator;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class SubmissionManager extends GenericManager<Submission, Integer, SubmissionEntity> implements SubmissionService {

    private UserTranslator userTranslator;
    private TopicTranslator topicTransalor;
    private TagTranslator tagTransalor;

    public SubmissionManager(SubmissionProtocol model) throws RemoteException {
        super(model);
        this.translator = new SubmissionTranslator();
        this.checker = new SubmissionPermissionChecker();
    }

    @Override
    public List<User> getReviewers(Submission submission) throws RemoteException {
        return getSubmissionEntity(submission).getReviewers().stream()
                .map(entity -> userTranslator.translate(entity))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getAuthors(Submission submission) throws RemoteException {
        return getSubmissionEntity(submission).getAuthors().stream()
                .map(entity -> userTranslator.translate(entity))
                .collect(Collectors.toList());
    }

    @Override
    public User getOwner(Submission submission) throws RemoteException {
        return userTranslator.translate(getSubmissionOwner(submission));
    }

    private UserEntity getSubmissionOwner(Submission submission) throws RemoteException {
        return getSubmissionEntity(submission).getOwner();
    }
    
    @Override
    public List<Topic> getTopics(Submission submission) throws RemoteException {
        return getSubmissionEntity(submission).getTopics().stream()
                .map(entity -> topicTransalor.translate(entity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Tag> getTags(Submission submission) throws RemoteException {
        return getSubmissionEntity(submission).getTags().stream()
                .map(entity -> tagTransalor.translate(entity))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getBindingReviewers(Submission submission) throws RemoteException {
        return getSubmissionEntity(submission).getBindingReviewers().stream()
                .map(entity -> userTranslator.translate(entity))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getAllowedReviewers(Submission submission) throws RemoteException {
        return getSubmissionEntity(submission).getAllowedReviewers().stream()
                .map(entity -> userTranslator.translate(entity))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getRejectedReviewers(Submission submission) throws RemoteException {
        return getSubmissionEntity(submission).getRejectedReviewers().stream()
                .map(entity -> userTranslator.translate(entity))
                .collect(Collectors.toList());
    }

    private SubmissionEntity getSubmissionEntity(Submission submission) throws RemoteException {
        return runFunction(model::getElementById, submission.getId()).orThrow(thrower);
    }

}
