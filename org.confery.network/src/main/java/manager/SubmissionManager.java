package manager;

import domain.SubmissionEntity;
import domain.UserEntity;
import exception.SystemException;
import protocol.ModelInterface;
import service.SubmissionService;
import transfarable.Submission;
import transfarable.User;
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

public class SubmissionManager implements SubmissionService {

    protected ModelInterface<SubmissionEntity, Integer> model;
    protected UserEntity active;
    private Function<SystemException, RemoteException> thrower;

    public SubmissionManager(ModelInterface<SubmissionEntity, Integer> model) throws RemoteException {
        this.model = model;
        thrower = exception -> new RemoteException(exception != null ? exception.getMessage() : null);
    }

    @Override
    public void activeUser(User user) throws RemoteException {
        UserEntity userEntity = UserTranslator.translate(user);
        active = userEntity;
    }

    @Override
    public Integer add(Submission element) throws RemoteException {
        return Try.runFunction(model::add, SubmissionTranslator.translate(element)).orThrow(thrower);
    }

    @Override
    public Submission update(Submission element, Submission with) throws RemoteException {
        Try.runMethod(
                model::update,
                SubmissionTranslator.translate(element),
                SubmissionTranslator.translate(with)
        ).orThrow(thrower);
        return with;
    }


    @Override
    public Submission delete(Submission element) throws RemoteException {
        return SubmissionTranslator.translate(
                Try.runFunction(model::delete, SubmissionTranslator.translate(element))
                        .orThrow(thrower)
        );
    }

    @Override
    public Submission getElementById(Integer id) throws RemoteException {
        return SubmissionTranslator.translate(
                Try.runFunction(model::getElementById, id)
                        .orThrow(thrower)
        );
    }

    @Override
    public List<Submission> getAll() throws RemoteException {
        List<Submission> transferableSubmissions = new ArrayList<>();
        List<SubmissionEntity> Submissions = model.getAll();

        for (SubmissionEntity SubmissionEntity : Submissions) {
            transferableSubmissions.add(SubmissionTranslator.translate(SubmissionEntity));
        }

        return transferableSubmissions;
    }

}
