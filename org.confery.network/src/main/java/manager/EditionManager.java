package manager;

import checker.UserPermissionChecker;
import domain.*;
import exception.SystemException;
import protocol.EditionProtocol;
import service.EditionService;
import transfarable.Edition;
import transfarable.Session;
import transfarable.Submission;
import transfarable.User;
import translator.EditionTranslator;
import translator.SessionTranslator;
import translator.SubmissionTranslator;
import translator.UserTranslator;
import utils.Try;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class EditionManager implements EditionService {

    protected EditionProtocol model;
    protected UserEntity active;
    private Function<SystemException, RemoteException> thrower;
    private UserPermissionChecker checker;

    public EditionManager(EditionProtocol model) throws RemoteException {
        this.model = model;
        this.thrower = exception -> new RemoteException(exception != null ? exception.getMessage() : null);
    }

    @Override
    public void activeUser(User user) throws RemoteException {
        UserEntity userEntity = UserTranslator.translate(user);
        active = userEntity;
    }

    @Override
    public Integer add(Edition element) throws RemoteException {
        return Try.runFunction(model::add, EditionTranslator.translate(element)).orThrow(thrower);
    }


    @Override
    public void update(Edition element, Edition with) throws RemoteException {
        Try.runMethod(
                model::update,
                EditionTranslator.translate(element),
                EditionTranslator.translate(with)
        ).orThrow(thrower);
    }

    @Override
    public Edition delete(Edition element) throws RemoteException {
        return EditionTranslator.translate(
                Try.runFunction(model::delete, EditionTranslator.translate(element))
                        .orThrow(thrower)
        );
    }

    @Override
    public Edition getElementById(Integer id) throws RemoteException {
        return EditionTranslator.translate(
                Try.runFunction(model::getElementById, id)
                        .orThrow(thrower)
        );
    }

    @Override
    public List<Edition> getAll() throws RemoteException {
        List<Edition> transferableEditions = new ArrayList<>();
        List<EditionEntity> editions = model.getAll();

        for (EditionEntity editionEntity : editions) {
            transferableEditions.add(EditionTranslator.translate(editionEntity));
        }

        return transferableEditions;
    }

    private void checkUpdatePermission(EditionEntity editionEntity, UserEntity userEntity)
            throws RemoteException, SystemException {
        //  Cannot use Try.runFunction to properly handle
//        if (! checker.isAllowed(userEntity).toUpdate().theEdition(editionEntity)) {
//            throw new RemoteException("User is not permitted to update the edition");
//        }
    }

    @Override
    public Edition addMemberToEdition(Edition edition, User user, MemberRole memberRole) throws RemoteException {
        EditionEntity editionEntity = EditionTranslator.translate(edition);
        UserEntity userEntity = UserTranslator.translate(user);

        try {
            checkUpdatePermission(editionEntity, active);
            return EditionTranslator.translate(
                    model.addMemberTo(editionEntity, userEntity, memberRole)
            );
        }
        catch (SystemException ex) {
            throw new RemoteException(ex.getMessage());
        }
    }

    @Override
    public Edition deleteMemberOfEdition(Edition edition, User user) throws RemoteException {
        EditionEntity editionEntity = EditionTranslator.translate(edition);
        UserEntity userEntity = UserTranslator.translate(user);

        try {
            checkUpdatePermission(editionEntity, active);
            return EditionTranslator.translate(
                    model.deleteMemberOf(editionEntity, userEntity)
            );
        }
        catch (SystemException ex) {
            throw new RemoteException(ex.getMessage());
        }
    }

    @Override
    public Edition addSessionToEdition(Edition edition, Session session) throws RemoteException {
        EditionEntity editionEntity = EditionTranslator.translate(edition);
        SessionEntity sessionEntity = SessionTranslator.translate(session);

        try {
            checkUpdatePermission(editionEntity, active);
            return EditionTranslator.translate(
                    model.addSessionTo(editionEntity, sessionEntity)
            );
        }
        catch (SystemException ex) {
            throw new RemoteException(ex.getMessage());
        }
    }

    @Override
    public Edition deleteSessionOfEdition(Edition edition, Session session) throws RemoteException {
        EditionEntity editionEntity = EditionTranslator.translate(edition);
        SessionEntity sessionEntity = SessionTranslator.translate(session);

        try {
            checkUpdatePermission(editionEntity, active);
            return EditionTranslator.translate(
                    model.deleteSessionOf(editionEntity, sessionEntity)
            );
        }
        catch (SystemException ex) {
            throw new RemoteException(ex.getMessage());
        }
    }

    @Override
    public Edition addSubmissionToEdition(Edition edition, Submission submission) throws RemoteException {
        EditionEntity editionEntity = EditionTranslator.translate(edition);
        SubmissionEntity submissionEntity = SubmissionTranslator.translate(submission);

        try {
            checkUpdatePermission(editionEntity, active);
            return EditionTranslator.translate(
                    model.addSubmissionTo(editionEntity, submissionEntity)
            );
        }
        catch (SystemException ex) {
            throw new RemoteException(ex.getMessage());
        }
    }

    @Override
    public Edition deleteSubmissionOfEdition(Edition edition, Submission submission) throws RemoteException {
        EditionEntity editionEntity = EditionTranslator.translate(edition);
        SubmissionEntity submissionEntity = SubmissionTranslator.translate(submission);

        try {
            checkUpdatePermission(editionEntity, active);
            return EditionTranslator.translate(
                    model.deleteSubmissionOf(editionEntity, submissionEntity)
            );
        }
        catch (SystemException ex) {
            throw new RemoteException(ex.getMessage());
        }
    }
}
