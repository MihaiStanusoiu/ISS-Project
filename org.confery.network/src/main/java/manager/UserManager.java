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
 * @author Tiron Andreea - Ecaterina
 * @version 1.0
 */

public class UserManager
        extends GenericManager<User, Integer, UserEntity>
        implements UserService {

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
        this.model = model;
        this.notificationTranslator = new NotificationTranslator();
        this.memberRoleTranslator = new MemberRoleTranslator();
        this.editionTranslator = new EditionTranslator();
        this.sessionTranslator = new SessionTranslator();
        this.submissionTranslator = new SubmissionTranslator();
    }

    @Override
    public List<User> findUsersByUsername(String username) throws RemoteException {
        return runFunction(model::findUsersByUsername, username).orThrow(thrower)
                .stream().map(translator::translate).collect(Collectors.toList());
    }

    @Override
    public User findUserByUsername(String username) throws RemoteException {
        return translator.translate(runFunction(model::findUserByUsername, username).orThrow(thrower));
    }

    @Override
    public List<Notification> getNotificationsFrom(User user) throws RemoteException {
        return getUserFromDatabase(user).getNotifications().stream()
                .map(notificationTranslator::translate)
                .collect(Collectors.toList());
    }

    @Override
    public List<Session> getSessionsForUser(User user) throws RemoteException {
        return getUserFromDatabase(user).getSessions().stream()
                .map(sessionTranslator::translate)
                .collect(Collectors.toList());
    }

    @Override
    public List<Edition> getEditionsForUser(User user) throws RemoteException {
        return getUserFromDatabase(user).getEditions().stream()
                .map(editionTranslator::translate)
                .collect(Collectors.toList());
    }

    @Override
    public List<Submission> getSubmissionsForUser(User user) throws RemoteException {
        return getUserFromDatabase(user).getSubmissions().stream()
                .map(submissionTranslator::translate)
                .collect(Collectors.toList());
    }

    @Override
    public List<Submission> getSubmissionsByOwnership(User user) throws RemoteException {
        return getUserFromDatabase(user).getSubmissionsByOwnership().stream()
                .map(submissionTranslator::translate)
                .collect(Collectors.toList());
    }

    @Override
    public List<Submission> getSubmissionsByAuthorship(User user) throws RemoteException {
        return getUserFromDatabase(user).getSubmissionsByAuthorship().stream()
                .map(submissionTranslator::translate)
                .collect(Collectors.toList());
    }

    @Override
    public List<Edition> getEditionsAsChair(User user) throws RemoteException {
        return getUserFromDatabase(user).getMyEditions().stream()
                .map(editionTranslator::translate)
                .collect(Collectors.toList());
    }

    @Override
    public User addEditionTo(User user, Edition edition, MemberRoleTransferable role) throws RemoteException {
        checkUserPermissions(user);
        return translator.translate(runFunction(model::addEditionTo, getUserFromDatabase(user),
                editionTranslator.translate(edition), memberRoleTranslator.translate(role)).orThrow(thrower));
    }

    @Override
    public User addSessionTo(User user, Session session, MemberRoleTransferable role) throws RemoteException {
        checkUserPermissions(user);
        return translator.translate(runFunction(model::addSessionTo, getUserFromDatabase(user),
                sessionTranslator.translate(session), memberRoleTranslator.translate(role)).orThrow(thrower));
    }

    @Override
    public User addSubmissionTo(User user, Submission submission, Boolean isOwner) throws RemoteException {
        checkUserPermissions(user);
        return translator.translate(runFunction(model::addSubmissionTo, getUserFromDatabase(user),
                submissionTranslator.translate(submission), isOwner).orThrow(thrower));
    }

    @Override
    public User addNotificationTo(User user, Notification notification) throws RemoteException {
        checkUserPermissions(user);
        return translator.translate(runFunction(model::addNotificationTo, getUserFromDatabase(user),
                notificationTranslator.translate(notification)).orThrow(thrower));
    }

    @Override
    public User removeEditionFrom(User user, Edition edition) throws RemoteException {
        checkUserPermissions(user);
        return translator.translate(runFunction(model::removeEditionFrom, getUserFromDatabase(user),
                editionTranslator.translate(edition)).orThrow(thrower));
    }

    @Override
    public User removeSessionFrom(User user, Session session) throws RemoteException {
        checkUserPermissions(user);
        return translator.translate(runFunction(model::removeSessionFrom, getUserFromDatabase(user),
                sessionTranslator.translate(session)).orThrow(thrower));
    }

    @Override
    public User removeSubmissionFrom(User user, Submission submission) throws RemoteException {
        checkUserPermissions(user);
        return translator.translate(runFunction(model::removeSubmissionFrom, getUserFromDatabase(user),
                submissionTranslator.translate(submission)).orThrow(thrower));
    }

    @Override
    public User removeNotificationFrom(User user, Notification notification) throws RemoteException {
        checkUserPermissions(user);
        return translator.translate(runFunction(model::removeNotificationFrom, getUserFromDatabase(user),
                notificationTranslator.translate(notification)).orThrow(thrower));
    }

    private UserEntity getUserFromDatabase(User user) throws RemoteException {
        return runFunction(model::getElementById, user.getId()).orThrow(thrower);
    }

    private void checkUserPermissions(Boolean alternative, User user) throws RemoteException {
        basedOn(alternative || checker.isAllowed(getActiveUser()).toUpdate().theObject(translator.translate(user)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
    }

    private void checkUserPermissions(User user) throws RemoteException {
        checkUserPermissions(Boolean.FALSE, user);
    }

}
