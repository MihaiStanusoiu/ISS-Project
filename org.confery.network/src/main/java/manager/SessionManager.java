package manager;

import checker.SessionPermissionChecker;
import domain.SessionEntity;
import domain.UserEntity;
import protocol.LoginProtocol;
import protocol.ModelInterface;
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

public class SessionManager extends GenericManager<Session, Integer, SessionEntity> implements SessionService {

    private UserTranslator userTranslator;
    private MemberRoleTranslator memberRoleTranslator;
    protected SessionProtocol model;

    public SessionManager(ModelInterface<SessionEntity, Integer> model, LoginProtocol loginProtocol) throws RemoteException {
        super(model, loginProtocol);
        checker = new SessionPermissionChecker();
        translator = new SessionTranslator();

    }

    @Override
    public List<User> getSpeakers(Session session) throws RemoteException {
        return getSessionEntity(session).getSpeakers().stream()
                .map(entity -> userTranslator.translate(entity))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getListeners(Session session) throws RemoteException {
        return getSessionEntity(session).getListeners().stream()
                .map(entity -> userTranslator.translate(entity))
                .collect(Collectors.toList());
    }

    @Override
    public User getChair(Session session) throws RemoteException {
        return userTranslator.translate(getSessionChair(session));
    }

    @Override
    public Session addMemberTo(Session session, User user, MemberRoleTransferable role) throws RemoteException {
        UserEntity active = getActiveUser();
        basedOn(checker.isAllowed(active).toUpdate().theObject(translator.translate(session)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
        return translator.translate(runFunction(model::addMemberTo, translator.translate(session),userTranslator.translate(user),
                memberRoleTranslator.translate(role)).orThrow(thrower));
    }

    @Override
    public Session removeMemberFrom(Session session, User user) throws RemoteException {
        UserEntity active = getActiveUser();
        basedOn(checker.isAllowed(active).toUpdate().theObject(translator.translate(session)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
        return translator.translate(runFunction(model::removeMemberFrom,
                translator.translate(session),userTranslator.translate(user)).orThrow(thrower));
    }


    private UserEntity getSessionChair(Session session) throws RemoteException {
        return runFunction(this.getSessionEntity(session)::getChair).orThrow(thrower);
    }


    private SessionEntity getSessionEntity(Session session) throws RemoteException {
        return runFunction(model::getElementById, session.getId()).orThrow(thrower);
    }
}
