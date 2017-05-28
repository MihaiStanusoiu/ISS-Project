package manager;

import domain.EditionEntity;
import protocol.EditionProtocol;
import service.EditionService;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class EditionManager extends GenericManager<EditionEntity, Integer> implements EditionService {

    public EditionManager(EditionProtocol model) throws RemoteException {
        super(model);
    }

}
