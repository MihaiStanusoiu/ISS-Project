package manager;

import checker.SubmissionPermissionChecker;
import domain.SubmissionEntity;
import domain.TagEntity;
import domain.TopicEntity;
import domain.UserEntity;
import model.TagModel;
import model.TopicModel;
import model.UserModel;
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

public class SubmissionManager
        extends GenericManager<Submission, Integer, SubmissionEntity>
        implements SubmissionService {

    private UserTranslator userTranslator;
    private TopicTranslator topicTranslator;
    private TagTranslator tagTranslator;
    protected SubmissionProtocol model;
    private TagModel tagModel;
    private UserModel userModel;
    private TopicModel topicModel;

    public SubmissionManager(SubmissionProtocol model, LoginProtocol loginProtocol) throws RemoteException {
        super(model, loginProtocol);
        this.model = model;
        tagModel = new TagModel(model.getLoader());
        topicModel = new TopicModel(model.getLoader());
        userModel = new UserModel(model.getLoader());
        translator = new SubmissionTranslator();
        checker = new SubmissionPermissionChecker();
        userTranslator = new UserTranslator();
        topicTranslator = new TopicTranslator();
        tagTranslator = new TagTranslator();
    }

    @Override
    public List<User> getReviewers(Submission submission) throws RemoteException {
        return getSubmissionFromDatabase(submission).getReviewers().stream()
                .map(userTranslator::translate)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getAuthors(Submission submission) throws RemoteException {
        return getSubmissionFromDatabase(submission).getAuthors().stream()
                .map(userTranslator::translate)
                .collect(Collectors.toList());
    }

    @Override
    public User getOwner(Submission submission) throws RemoteException {
        return userTranslator.translate(getSubmissionOwner(submission));
    }

    private UserEntity getSubmissionOwner(Submission submission) throws RemoteException {
        return getSubmissionFromDatabase(submission).getOwner();
    }

    @Override
    public List<Topic> getTopics(Submission submission) throws RemoteException {
        return getSubmissionFromDatabase(submission).getTopics().stream()
                .map(topicTranslator::translate)
                .collect(Collectors.toList());
    }

    @Override
    public List<Tag> getTags(Submission submission) throws RemoteException {
        return getSubmissionFromDatabase(submission).getTags().stream()
                .map(tagTranslator::translate)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getBindingReviewers(Submission submission) throws RemoteException {
        return getSubmissionFromDatabase(submission).getBindingReviewers().stream()
                .map(userTranslator::translate)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getAllowedReviewers(Submission submission) throws RemoteException {
        return getSubmissionFromDatabase(submission).getAllowedReviewers().stream()
                .map(userTranslator::translate)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getRejectedReviewers(Submission submission) throws RemoteException {
        return getSubmissionFromDatabase(submission).getRejectedReviewers().stream()
                .map(userTranslator::translate)
                .collect(Collectors.toList());
    }

    @Override
    public Submission addTagTo(Submission submission, Tag tag) throws RemoteException {
        checkUserPermissions(submission);
        return translator.translate(runFunction(model::addTagTo, getSubmissionFromDatabase(submission),
                getTagFromDatabase(tag)).orThrow(thrower));
    }

    private TagEntity getTagFromDatabase(Tag tag) throws RemoteException {
        return runFunction(tagModel::getElementById, tag.getId()).orThrow(thrower);
    }

    private void checkUserPermissions(Submission submission) throws RemoteException {
        checkUserPermissions(Boolean.FALSE, submission);
    }

    @Override
    public Submission addTopicTo(Submission submission, Topic topic) throws RemoteException {
        checkUserPermissions(submission);
        return translator.translate(runFunction(model::addTopicTo, getSubmissionFromDatabase(submission),
                topicTranslator.translate(topic)).orThrow(thrower));
    }

    private TopicEntity getTopicFromDatabase(Topic topic) throws RemoteException {
        return runFunction(topicModel::getElementById, topic.getId()).orThrow(thrower);
    }

    @Override
    public Submission addAuthorTo(Submission submission, User author) throws RemoteException {
        checkUserPermissions(submission);
        return translator.translate(runFunction(model::addAuthorTo, getSubmissionFromDatabase(submission),
                getAuthorFromDatabase(author)).orThrow(thrower));
    }

    private UserEntity getAuthorFromDatabase(User author) throws RemoteException {
        return runFunction(userModel::getElementById, author.getId()).orThrow(thrower);
    }

    @Override
    public Submission addOwnerTo(Submission submission, User owner) throws RemoteException {
        checkUserPermissions(submission);
        return translator.translate(runFunction(model::addOwnerTo, getSubmissionFromDatabase(submission),
                getAuthorFromDatabase(owner)).orThrow(thrower));
    }

    @Override
    public Submission removeAuthorFrom(Submission submission, User author) throws RemoteException {
        checkUserPermissions(submission);
        return translator.translate(runFunction(model::removeAuthorFrom, getSubmissionFromDatabase(submission),
                getAuthorFromDatabase(author)).orThrow(thrower));
    }

    @Override
    public Submission removeTagFrom(Submission submission, Tag tag) throws RemoteException {
        checkUserPermissions(submission);
        return translator.translate(runFunction(model::removeTagFrom, getSubmissionFromDatabase(submission),
                getTagFromDatabase(tag)).orThrow(thrower));
    }

    @Override
    public Submission removeTopicFrom(Submission submission, Topic topic) throws RemoteException {
        checkUserPermissions(submission);
        return translator.translate(runFunction(model::removeTopicFrom, getSubmissionFromDatabase(submission),
                getTopicFromDatabase(topic)).orThrow(thrower));
    }

    private SubmissionEntity getSubmissionFromDatabase(Submission submission) throws RemoteException {
        return runFunction(model::getElementById, submission.getId()).orThrow(thrower);
    }

    private void checkUserPermissions(Boolean alternative, Submission submission) throws RemoteException {
        basedOn(alternative || checker.isAllowed(getActiveUser()).toUpdate().theObject(getSubmissionFromDatabase(submission)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
    }

}
