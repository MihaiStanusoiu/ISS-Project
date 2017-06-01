package manager;

import checker.UserPermissionChecker;
import domain.UserEntity;
import protocol.UserProtocol;
import service.UserService;
import transfarable.Edition;
import transfarable.Session;
import transfarable.Submission;
import transfarable.User;
import translator.EditionTranslator;
import translator.SessionTranslator;
import translator.SubmissionTranslator;
import translator.UserTranslator;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class UserManager extends GenericManager<User, Integer, UserEntity> implements UserService {

    private final EditionTranslator editionTranslator;
    private final SessionTranslator sessionTranslator;
    private final SubmissionTranslator submissionTranslator;

    public UserManager(UserProtocol model) throws RemoteException {
        super(model);
        super.translator = new UserTranslator();
        super.checker = new UserPermissionChecker();
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
}
