package manager;

import checker.EditionPermissionChecker;
import domain.EditionEntity;
import domain.UserEntity;
import protocol.EditionProtocol;
import protocol.LoginProtocol;
import service.EditionService;
import transfarable.*;
import translator.*;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

import static utils.Conditional.basedOn;
import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class EditionManager extends GenericManager<Edition, Integer, EditionEntity> implements EditionService {

    private UserTranslator userTranslator;
    private MemberRoleTranslator memberRoleTranslator;
    protected EditionProtocol model;
    private SessionTranslator sessionTranslator;
    private SubmissionTranslator submissionTranslator;

    public EditionManager(EditionProtocol model, LoginProtocol loginProtocol) throws RemoteException {
        super(model, loginProtocol);
        this.model = model;
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
    public Edition addMemberToEdition(Edition edition, User user, MemberRoleTransferable memberRole) throws RemoteException {
        UserEntity active = getActiveUser();
        basedOn(checker.isAllowed(active).toUpdate().theObject(translator.translate(edition)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
        return translator.translate(runFunction(model::addMemberTo,
                translator.translate(edition),
                userTranslator.translate(user),
                memberRoleTranslator.translate(memberRole))
                .orThrow(thrower));
    }

    @Override
    public Edition deleteMemberOfEdition(Edition edition, User user) throws RemoteException {
        UserEntity active = getActiveUser();
        basedOn(checker.isAllowed(active).toUpdate().theObject(translator.translate(edition)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
        return translator.translate(runFunction(model::deleteMemberOf, translator.translate(edition), userTranslator.translate(user))
                .orThrow(thrower));
    }

    @Override
    public Edition addSessionToEdition(Edition edition, Session session) throws RemoteException {
        UserEntity active = getActiveUser();
        basedOn(checker.isAllowed(active).toUpdate().theObject(translator.translate(edition)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
        return translator.translate(runFunction(model::addSessionTo, translator.translate(edition), sessionTranslator.translate(session))
                .orThrow(thrower));
    }

    @Override
    public Edition deleteSessionOfEdition(Edition edition, Session session) throws RemoteException {
        UserEntity active = getActiveUser();
        basedOn(checker.isAllowed(active).toUpdate().theObject(translator.translate(edition)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
        return translator.translate(runFunction(model::deleteSessionOf, translator.translate(edition), sessionTranslator.translate(session))
                .orThrow(thrower));

    }

    @Override
    public Edition addSubmissionToEdition(Edition edition, Submission submission) throws RemoteException {
        UserEntity active = getActiveUser();
        basedOn(checker.isAllowed(active).toUpdate().theObject(translator.translate(edition)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
        return translator.translate(runFunction(model::addSubmissionTo, translator.translate(edition), submissionTranslator.translate(submission))
                .orThrow(thrower));
    }

    @Override
    public Edition deleteSubmissionOfEdition(Edition edition, Submission submission) throws RemoteException {
        UserEntity active = getActiveUser();
        basedOn(checker.isAllowed(active).toUpdate().theObject(translator.translate(edition)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
        return translator.translate(runFunction(model::deleteSubmissionOf, translator.translate(edition), submissionTranslator.translate(submission))
                .orThrow(thrower));
    }
}
