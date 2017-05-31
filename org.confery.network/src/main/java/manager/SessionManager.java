package manager;

import checker.SessionPermissionChecker;
import domain.SessionEntity;
import protocol.ModelInterface;
import service.SessionService;
import transfarable.Session;
import translator.SessionTranslator;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class SessionManager extends GenericManager<Session, Integer, SessionEntity> implements SessionService {

    public SessionManager(ModelInterface<SessionEntity, Integer> model) throws RemoteException {
        super(model);
        checker = new SessionPermissionChecker();
        translator = new SessionTranslator();
    }
}
