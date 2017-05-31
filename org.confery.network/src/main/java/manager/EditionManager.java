package manager;

import checker.EditionPermissionChecker;
import domain.EditionEntity;
import domain.MemberRole;
import protocol.EditionProtocol;
import service.EditionService;
import transfarable.Edition;
import transfarable.Session;
import transfarable.Submission;
import transfarable.User;
import translator.EditionTranslator;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class EditionManager extends GenericManager<Edition, Integer, EditionEntity> implements EditionService {

    public EditionManager(EditionProtocol model) throws RemoteException {
        super(model);
        checker = new EditionPermissionChecker();
        translator = new EditionTranslator();
    }

    @Override
    public Edition addMemberToEdition(Edition edition, User user, MemberRole memberRole) throws RemoteException {
        // TODO
        return null;
    }

    @Override
    public Edition deleteMemberOfEdition(Edition edition, User user) throws RemoteException {
        // TODO
        return null;
    }

    @Override
    public Edition addSessionToEdition(Edition edition, Session session) throws RemoteException {
        // TODO
        return null;
    }

    @Override
    public Edition deleteSessionOfEdition(Edition edition, Session session) throws RemoteException {
        // TODO
        return null;
    }

    @Override
    public Edition addSubmissionToEdition(Edition edition, Submission submission) throws RemoteException {
        // TODO
        return null;
    }

    @Override
    public Edition deleteSubmissionOfEdition(Edition edition, Submission submission) throws RemoteException {
        // TODO
        return null;
    }
}
