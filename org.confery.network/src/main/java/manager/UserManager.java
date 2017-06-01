package manager;

import checker.UserPermissionChecker;
import domain.UserEntity;
import protocol.LoginProtocol;
import protocol.UserProtocol;
import service.UserService;
import transfarable.*;
import translator.*;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

import static utils.Conditional.basedOn;
import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica & Tiron Andreea - Ecaterina
 * @version 1.0
 */

public class UserManager extends GenericManager<User, Integer, UserEntity> implements UserService {

    private MemberRoleTranslator memberRoleTranslator;
    private final EditionTranslator editionTranslator;
    private final SessionTranslator sessionTranslator;
    private final SubmissionTranslator submissionTranslator;
    private final NotificationTranslator notificationTranslator;

    protected UserProtocol model;

    public UserManager(UserProtocol model, LoginProtocol loginProtocol) throws RemoteException {
        super(model, loginProtocol);
        super.translator = new UserTranslator();
        super.checker = new UserPermissionChecker();
        this.notificationTranslator = new NotificationTranslator();
        this.editionTranslator = new EditionTranslator();
        this.sessionTranslator = new SessionTranslator();
        this.submissionTranslator = new SubmissionTranslator();
    }

    @Override
    public List<Session> getSessionsForUser(User user) throws RemoteException {
        return runFunction(model::getElementById, user.getId()).orThrow(thrower)
                .getSessions().stream()
                .map(sessionTranslator::translate)
                .collect(Collectors.toList());
    }

    @Override
    public List<Edition> getEditionsForUser(User user) throws RemoteException {
        return runFunction(model::getElementById, user.getId()).orThrow(thrower)
                .getEditions().stream()
                .map(editionTranslator::translate)
                .collect(Collectors.toList());
    }

    @Override
    public List<Submission> getSubmissionsForUser(User user) throws RemoteException {
        return runFunction(model::getElementById, user.getId()).orThrow(thrower)
                .getSubmissions().stream()
                .map(submissionTranslator::translate)
                .collect(Collectors.toList());
    }

    @Override
    public List<Submission> getSubmissionsByOwnership(User user) throws RemoteException {
        return runFunction(model::getElementById, user.getId()).orThrow(thrower)
                .getSubmissionsByOwnership().stream()
                .map(submissionTranslator::translate)
                .collect(Collectors.toList());
    }

    @Override
    public List<Submission> getSubmissionsByAuthorship(User user) throws RemoteException {
        return runFunction(model::getElementById, user.getId()).orThrow(thrower)
                .getSubmissionsByAuthorship().stream()
                .map(submissionTranslator::translate)
                .collect(Collectors.toList());
    }

    @Override
    public List<Edition> getEditionsAsChair(User user) throws RemoteException {
        return runFunction(model::getElementById, user.getId()).orThrow(thrower)
                .getMyEditions().stream()
                .map(editionTranslator::translate)
                .collect(Collectors.toList());
    }

    @Override
    public User addEditionTo(User user, Edition edition, MemberRoleTransferable role) throws RemoteException {
        UserEntity active = getActiveUser();
        basedOn(checker.isAllowed(active).toUpdate().theObject(translator.translate(user)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
        return translator.translate(runFunction(model::addEditionTo, translator.translate(user),editionTranslator.translate(edition),
                memberRoleTranslator.translate(role)).orThrow(thrower));
    }

    @Override
    public User addSessionTo(User user, Session session, MemberRoleTransferable role) throws RemoteException {
        UserEntity active = getActiveUser();
        basedOn(checker.isAllowed(active).toUpdate().theObject(translator.translate(user)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
        return translator.translate(runFunction(model::addSessionTo, translator.translate(user),sessionTranslator.translate(session),
                memberRoleTranslator.translate(role)).orThrow(thrower));
    }

    @Override
    public User addSubmissionTo(User user, Submission submission, Boolean isOwner) throws RemoteException {
        UserEntity active = getActiveUser();
        basedOn(checker.isAllowed(active).toUpdate().theObject(translator.translate(user)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
        return translator.translate(runFunction(model::addSubmissionTo,
                translator.translate(user), submissionTranslator.translate(submission), isOwner).orThrow(thrower));
    }

    @Override
    public User addNotificationTo(User user, Notification notification) throws RemoteException {
        UserEntity active = getActiveUser();
        basedOn(checker.isAllowed(active).toUpdate().theObject(translator.translate(user)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
        return translator.translate(runFunction(model::addNotificationTo,
                translator.translate(user), notificationTranslator.translate(notification)).orThrow(thrower));
    }

    @Override
    public User removeEditionFrom(User user, Edition edition) throws RemoteException {
        UserEntity active = getActiveUser();
        basedOn(checker.isAllowed(active).toUpdate().theObject(translator.translate(user)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
        return translator.translate(runFunction(model::removeEditionFrom,
                translator.translate(user), editionTranslator.translate(edition)).orThrow(thrower));
    }

    @Override
    public User removeSessionFrom(User user, Session session) throws RemoteException {
        UserEntity active = getActiveUser();
        basedOn(checker.isAllowed(active).toUpdate().theObject(translator.translate(user)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
        return translator.translate(runFunction(model::removeSessionFrom,
                translator.translate(user), sessionTranslator.translate(session)).orThrow(thrower));
    }

    @Override
    public User removeSubmissionFrom(User user, Submission submission) throws RemoteException {
        UserEntity active = getActiveUser();
        basedOn(checker.isAllowed(active).toUpdate().theObject(translator.translate(user)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
        return translator.translate(runFunction(model::removeSubmissionFrom,
                translator.translate(user), submissionTranslator.translate(submission)).orThrow(thrower));
    }

    @Override
    public User removeNotificationFrom(User user, Notification notification) throws RemoteException {
        UserEntity active = getActiveUser();
        basedOn(checker.isAllowed(active).toUpdate().theObject(translator.translate(user)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
        return translator.translate(runFunction(model::removeNotificationFrom,
                translator.translate(user), notificationTranslator.translate(notification)).orThrow(thrower));
    }
}
