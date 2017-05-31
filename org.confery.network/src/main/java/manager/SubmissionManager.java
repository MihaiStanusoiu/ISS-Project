package manager;

import checker.SubmissionPermissionChecker;
import domain.SubmissionEntity;
import protocol.SubmissionProtocol;
import service.SubmissionService;
import transfarable.Submission;
import translator.SubmissionTranslator;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class SubmissionManager extends GenericManager<Submission, Integer, SubmissionEntity> implements SubmissionService {

    public SubmissionManager(SubmissionProtocol model) throws RemoteException {
        super(model);
        this.translator = new SubmissionTranslator();
        this.checker = new SubmissionPermissionChecker();
    }

}
