package manager;

import domain.SessionEntity;
import domain.UserEntity;
import exception.SystemException;
import protocol.ModelInterface;
import service.SessionService;
import transfarable.Session;
import transfarable.User;
import translator.SessionTranslator;
import translator.UserTranslator;
import utils.Try;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class SessionManager implements SessionService {

    protected ModelInterface<SessionEntity, Integer> model;
    protected UserEntity active;
    private Function<SystemException, RemoteException> thrower;

    public SessionManager(ModelInterface<SessionEntity, Integer> model) throws RemoteException {
        this.model = model;
        thrower = exception -> new RemoteException(exception != null ? exception.getMessage() : null);
    }

    @Override
    public void activeUser(User user) throws RemoteException {
        UserEntity userEntity = UserTranslator.translate(user);
        active = userEntity;
    }

    @Override
    public Integer add(Session element) throws RemoteException {
        return Try.runFunction(model::add, SessionTranslator.translate(element)).orThrow(thrower);
    }

    @Override
    public Session update(Session element, Session with) throws RemoteException {
        Try.runMethod(
                model::update,
                SessionTranslator.translate(element),
                SessionTranslator.translate(with)
        ).orThrow(thrower);
        return with;
    }


    @Override
    public Session delete(Session element) throws RemoteException {
        return SessionTranslator.translate(
                Try.runFunction(model::delete, SessionTranslator.translate(element))
                        .orThrow(thrower)
        );
    }

    @Override
    public Session getElementById(Integer id) throws RemoteException {
        return SessionTranslator.translate(
                Try.runFunction(model::getElementById, id)
                        .orThrow(thrower)
        );
    }

    @Override
    public List<Session> getAll() throws RemoteException {
        List<Session> transferableSessions = new ArrayList<>();
        List<SessionEntity> Sessions = model.getAll();

        for (SessionEntity SessionEntity : Sessions) {
            transferableSessions.add(SessionTranslator.translate(SessionEntity));
        }

        return transferableSessions;
    }

}
