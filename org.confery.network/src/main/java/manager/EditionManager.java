package manager;

import checker.EditionPermissionChecker;
import domain.EditionEntity;
import domain.MemberRole;
import domain.UserEntity;
import model.UserModel;
import protocol.EditionProtocol;
import protocol.LoginProtocol;
import protocol.UserProtocol;
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

    private final ConferenceTranslator conferenceTranslator;
    private UserTranslator userTranslator;
    private MemberRoleTranslator memberRoleTranslator;
    protected EditionProtocol model;
    private SessionTranslator sessionTranslator;
    private SubmissionTranslator submissionTranslator;
    private UserProtocol userModel;

    public EditionManager(EditionProtocol model, LoginProtocol loginProtocol) throws RemoteException {
        super(model, loginProtocol);
        this.model = model;
        this.userModel = new UserModel(model.getLoader());
        checker = new EditionPermissionChecker();
        translator = new EditionTranslator();
        userTranslator = new UserTranslator();
        sessionTranslator = new SessionTranslator();
        memberRoleTranslator = new MemberRoleTranslator();
        submissionTranslator = new SubmissionTranslator();
        conferenceTranslator = new ConferenceTranslator();
    }

    @Override public Conference getConferenceOf(Edition edition) throws RemoteException {
        return conferenceTranslator.translate(getEditionFromDatabase(edition).getConference());
    }

    @Override
    public List<Session> getAllSessionsOf(Edition edition) throws RemoteException {
        return getEditionFromDatabase(edition).getSessions().stream()
                .map(entity -> sessionTranslator.translate(entity))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getAllMembersOf(Edition edition) throws RemoteException {
        return getEditionFromDatabase(edition).getUsers().stream()
                .map(entity -> userTranslator.translate(entity))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getPcMembersOf(Edition edition) throws RemoteException {
        return getEditionFromDatabase(edition).getPcMembers().stream()
                .map(entity -> userTranslator.translate(entity))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getCoChairsOf(Edition edition) throws RemoteException {
        return getEditionFromDatabase(edition).getCoChairs().stream()
                .map(entity -> userTranslator.translate(entity))
                .collect(Collectors.toList());
    }

    @Override
    public User getChair(Edition edition) throws RemoteException {
        return userTranslator.translate(getEditionFromDatabase(edition).getChair());
    }

    @Override
    public Edition addChairToEdition(Edition edition, User user) throws RemoteException {
        return translator.translate(runFunction(model::addMemberTo, getEditionFromDatabase(edition),
                userTranslator.translate(user), MemberRole.EDITION_CHAIR).orThrow(thrower));
    }

    @Override
    public Edition addMemberToEdition(Edition edition, User user, MemberRoleTransferable memberRole) throws RemoteException {
        checkUserPermissions(edition);
        return translator.translate(runFunction(model::addMemberTo, getEditionFromDatabase(edition), userTranslator.translate(user),
                memberRoleTranslator.translate(memberRole)).orThrow(thrower));
    }

    private void checkUserPermissions(Edition edition) throws RemoteException {
        checkUserPermissions(Boolean.FALSE, edition);
    }

    private void checkUserPermissions(Boolean alternative, Edition edition) throws RemoteException {
        basedOn(alternative || checker.isAllowed(getActiveUser()).toUpdate().theObject(getEditionFromDatabase(edition)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
    }

    private EditionEntity getEditionFromDatabase(Edition edition) throws RemoteException {
         return runFunction(model::getElementById, edition.getId()).orThrow(thrower);
    }

    @Override
    public Edition deleteMemberOfEdition(Edition edition, User user) throws RemoteException {
        checkUserPermissions(edition);
        return translator.translate(runFunction(model::deleteMemberOf, getEditionFromDatabase(edition),
                getUserFromDatabase(user)).orThrow(thrower));
    }

    private UserEntity getUserFromDatabase(User user) throws RemoteException {
        return runFunction(userModel::getElementById, user.getId()).orThrow(thrower);
    }

    @Override
    public Edition addSessionToEdition(Edition edition, Session session) throws RemoteException {
        checkUserPermissions(edition);
        return translator.translate(runFunction(model::addSessionTo, getEditionFromDatabase(edition),
                sessionTranslator.translate(session)).orThrow(thrower));
    }

    @Override
    public Edition deleteSessionOfEdition(Edition edition, Session session) throws RemoteException {
        checkUserPermissions(edition);
        return translator.translate(runFunction(model::deleteSessionOf, getEditionFromDatabase(edition),
                sessionTranslator.translate(session)).orThrow(thrower));
    }

    @Override
    public Edition addSubmissionToEdition(Edition edition, Submission submission) throws RemoteException {
        return translator.translate(runFunction(model::addSubmissionTo, getEditionFromDatabase(edition),
                submissionTranslator.translate(submission)).orThrow(thrower));
    }

    @Override
    public Edition deleteSubmissionOfEdition(Edition edition, Submission submission) throws RemoteException {
        checkUserPermissions(edition);
        return translator.translate(runFunction(model::deleteSubmissionOf, getEditionFromDatabase(edition),
                submissionTranslator.translate(submission)).orThrow(thrower));
    }
}
