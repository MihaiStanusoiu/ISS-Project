package manager;

import checker.EditionPermissionChecker;
import domain.EditionEntity;
import domain.MemberRole;
import domain.UserEntity;
import protocol.EditionProtocol;
import protocol.LoginProtocol;
import service.EditionService;
import transfarable.Edition;
import transfarable.Session;
import transfarable.Submission;
import transfarable.User;
import translator.EditionTranslator;
import translator.UserTranslator;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class EditionManager extends GenericManager<Edition, Integer, EditionEntity> implements EditionService {

    private UserTranslator userTranslator;

    public EditionManager(EditionProtocol model, LoginProtocol loginProtocol) throws RemoteException {
        super(model, loginProtocol);
        checker = new EditionPermissionChecker();
        translator = new EditionTranslator();
        userTranslator = new UserTranslator();
    }

    @Override
    public List<User> getAllMembersOf(Edition edition) throws RemoteException {
        return getEditionEntity(edition).getUsers().stream()
                .map(entity -> userTranslator.translate(entity))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getPcMembersOf(Edition edition) throws RemoteException {
        return getEditionEntity(edition).getPcMembers().stream()
                .map(entity -> userTranslator.translate(entity))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getCoChairsOf(Edition edition) throws RemoteException {
        return getEditionEntity(edition).getCoChairs().stream()
                .map(entity -> userTranslator.translate(entity))
                .collect(Collectors.toList());
    }

    @Override
    public User getChair(Edition edition) throws RemoteException {
        return userTranslator.translate(getEditionChair(edition));
    }

    private UserEntity getEditionChair(Edition edition) throws RemoteException {
        return runFunction(this.getEditionEntity(edition)::getChair).orThrow(thrower);
    }

    private EditionEntity getEditionEntity(Edition edition) throws RemoteException {
        return runFunction(model::getElementById, edition.getId()).orThrow(thrower);
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
