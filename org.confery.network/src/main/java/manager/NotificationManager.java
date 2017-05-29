package manager;

import domain.NotificationEntity;
import protocol.NotificationProtocol;
import service.NotificationService;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class NotificationManager extends GenericManager<NotificationEntity, Integer> implements NotificationService {

    public NotificationManager(NotificationProtocol model) throws RemoteException {
        super(model);
    }

}
