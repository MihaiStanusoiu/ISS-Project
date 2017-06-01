package manager;

import checker.SessionPermissionChecker;
import domain.SessionEntity;
import domain.UserEntity;
import protocol.LoginProtocol;
import protocol.ModelInterface;
import service.SessionService;
import transfarable.Session;
import transfarable.User;
import translator.SessionTranslator;
import translator.UserTranslator;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class SessionManager extends GenericManager<Session, Integer, SessionEntity> implements SessionService {

    private UserTranslator userTranslator;

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

    private UserEntity getSessionChair(Session session) throws RemoteException {
        return runFunction(this.getSessionEntity(session)::getChair).orThrow(thrower);
    }


    private SessionEntity getSessionEntity(Session session) throws RemoteException {
        return runFunction(model::getElementById, session.getId()).orThrow(thrower);
    }
}
