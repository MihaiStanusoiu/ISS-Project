package manager;

import domain.SessionEntity;
import protocol.SessionProtocol;
import service.SessionService;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class SessionManager extends GenericManager<SessionEntity, Integer> implements SessionService {

    public SessionManager(SessionProtocol model) throws RemoteException {
        super(model);
    }

}
