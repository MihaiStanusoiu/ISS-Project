package manager;

import checker.SubmissionPermissionChecker;
import domain.SubmissionEntity;
import domain.UserEntity;
import protocol.LoginProtocol;
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

import static utils.Conditional.basedOn;
import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class SubmissionManager extends GenericManager<Submission, Integer, SubmissionEntity> implements SubmissionService {

    private UserTranslator userTranslator;
    private TopicTranslator topicTransalor;
    private TagTranslator tagTransalor;
    protected SubmissionProtocol model;

    public SubmissionManager(SubmissionProtocol model, LoginProtocol loginProtocol) throws RemoteException {
        super(model, loginProtocol);
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

    @Override
    public Submission addTagTo(Submission submission, Tag tag) throws RemoteException {
        UserEntity active = getActiveUser();
        basedOn(checker.isAllowed(active).toUpdate().theObject(translator.translate(submission)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
        return translator.translate(runFunction(model::addTagTo, translator.translate(submission),tagTransalor.translate(tag))
                .orThrow(thrower));
    }

    @Override
    public Submission addTopicTo(Submission submission, Topic topic) throws RemoteException {
        UserEntity active = getActiveUser();
        basedOn(checker.isAllowed(active).toUpdate().theObject(translator.translate(submission)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
        return translator.translate(runFunction(model::addTopicTo, translator.translate(submission),topicTransalor.translate(topic))
                .orThrow(thrower));
    }

    @Override
    public Submission addAuthorTo(Submission submission, User author) throws RemoteException {
        UserEntity active = getActiveUser();
        basedOn(checker.isAllowed(active).toUpdate().theObject(translator.translate(submission)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
        return translator.translate(runFunction(model::addAuthorTo, translator.translate(submission),userTranslator.translate(author))
                .orThrow(thrower));
    }

    @Override
    public Submission addOwnerTo(Submission submission, User owner) throws RemoteException {
        UserEntity active = getActiveUser();
        basedOn(checker.isAllowed(active).toUpdate().theObject(translator.translate(submission)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
        return translator.translate(runFunction(model::addOwnerTo, translator.translate(submission),userTranslator.translate(owner))
                .orThrow(thrower));
    }


    @Override
    public Submission removeAuthorFrom(Submission submission, User author) throws RemoteException {
        UserEntity active = getActiveUser();
        basedOn(checker.isAllowed(active).toUpdate().theObject(translator.translate(submission)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
        return translator.translate(runFunction(model::removeAuthorFrom, translator.translate(submission),userTranslator.translate(author))
                .orThrow(thrower));
    }

    @Override
    public Submission removeTagFrom(Submission submission, Tag tag) throws RemoteException {
        UserEntity active = getActiveUser();
        basedOn(checker.isAllowed(active).toUpdate().theObject(translator.translate(submission)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
        return translator.translate(runFunction(model::removeTagFrom, translator.translate(submission),tagTransalor.translate(tag))
                .orThrow(thrower));
    }

    @Override
    public Submission removeTopicFrom(Submission submission, Topic topic) throws RemoteException {
        UserEntity active = getActiveUser();
        basedOn(checker.isAllowed(active).toUpdate().theObject(translator.translate(submission)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
        return translator.translate(runFunction(model::removeTopicFrom, translator.translate(submission),topicTransalor.translate(topic))
                .orThrow(thrower));
    }

    private SubmissionEntity getSubmissionEntity(Submission submission) throws RemoteException {
        return runFunction(model::getElementById, submission.getId()).orThrow(thrower);
    }

}
