package manager;

import domain.SubmissionEntity;
import protocol.SubmissionProtocol;
import service.SubmissionService;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class SubmissionManager extends GenericManager<SubmissionEntity, Integer> implements SubmissionService {

    public SubmissionManager(SubmissionProtocol model) throws RemoteException {
        super(model);
    }

}
