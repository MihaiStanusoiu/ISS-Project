package manager;

import checker.SessionPermissionChecker;
import domain.SessionEntity;
import protocol.LoginProtocol;
import protocol.SessionProtocol;
import service.SessionService;
import transfarable.MemberRoleTransferable;
import transfarable.Session;
import transfarable.User;
import translator.MemberRoleTranslator;
import translator.SessionTranslator;
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

public class SessionManager
        extends GenericManager<Session, Integer, SessionEntity>
        implements SessionService {

    protected SessionProtocol model;
    private UserTranslator userTranslator;
    private MemberRoleTranslator memberRoleTranslator;

    public SessionManager(SessionProtocol model, LoginProtocol loginProtocol) throws RemoteException {
        super(model, loginProtocol);
        this.model = model;
        checker = new SessionPermissionChecker();
        userTranslator = new UserTranslator();
        memberRoleTranslator = new MemberRoleTranslator();
        translator = new SessionTranslator();
    }

    @Override
    public User getChair(Session session) throws RemoteException {
        return userTranslator.translate(getSessionFromDatabase(session).getChair());
    }

    @Override
    public List<User> getSpeakers(Session session) throws RemoteException {
        return getSessionFromDatabase(session).getSpeakers().stream()
                .map(userTranslator::translate)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getListeners(Session session) throws RemoteException {
        return getSessionFromDatabase(session).getListeners().stream()
                .map(userTranslator::translate)
                .collect(Collectors.toList());
    }

    @Override
    public Session addMemberTo(Session session, User user, MemberRoleTransferable role) throws RemoteException {
        checkUserPermissions(role.equals(MemberRoleTransferable.SESSION_LISTENER), session);
        return translator.translate(runFunction(model::addMemberTo, getSessionFromDatabase(session),
                userTranslator.translate(user), memberRoleTranslator.translate(role)).orThrow(thrower));
    }

    @Override
    public Session removeMemberFrom(Session session, User user) throws RemoteException {
        checkUserPermissions(session);
        return translator.translate(runFunction(model::removeMemberFrom, getSessionFromDatabase(session),
                userTranslator.translate(user)).orThrow(thrower));
    }

    private void checkUserPermissions(Session session) throws RemoteException {
        checkUserPermissions(Boolean.FALSE, session);
    }

    private void checkUserPermissions(Boolean alternative, Session session) throws RemoteException {
        basedOn(alternative || checker.isAllowed(getActiveUser()).toUpdate().theObject(translator.translate(session)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
    }

    private SessionEntity getSessionFromDatabase(Session session) throws RemoteException {
        return runFunction(model::getElementById, session.getId()).orThrow(thrower);
    }

}
