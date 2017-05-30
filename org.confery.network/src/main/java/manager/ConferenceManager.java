package manager;

import domain.ConferenceEntity;
import domain.EditionEntity;
import domain.UserEntity;
import exception.SystemException;
import protocol.ConferenceProtocol;
import service.ConferenceService;
import transfarable.Conference;
import transfarable.Edition;
import transfarable.User;
import translator.ConferenceTranslator;
import translator.EditionTranslator;
import translator.UserTranslator;
import utils.Try;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Created by Mike on 5/30/2017.
 */
public class ConferenceManager implements ConferenceService {

    protected ConferenceProtocol model;
    protected UserEntity active;
    private Function<SystemException, RemoteException> thrower;

    public ConferenceManager(ConferenceProtocol model) throws RemoteException {
        this.model = model;
        thrower = exception -> new RemoteException(exception != null ? exception.getMessage() : null);
    }

    @Override
    public void activeUser(User user) throws RemoteException {
        UserEntity userEntity = UserTranslator.translate(user);
        active = userEntity;
    }

    @Override
    public Integer add(Conference element) throws RemoteException {
        return Try.runFunction(model::add, ConferenceTranslator.translate(element)).orThrow(thrower);
    }

    @Override
    public Conference update(Conference element, Conference with) throws RemoteException {
        Try.runMethod(
                model::update,
                ConferenceTranslator.translate(element),
                ConferenceTranslator.translate(with)
        ).orThrow(thrower);
        return with;
    }

    @Override
    public Conference delete(Conference element) throws RemoteException {
        return ConferenceTranslator.translate(
                Try.runFunction(model::delete, ConferenceTranslator.translate(element))
                        .orThrow(thrower)
        );
    }

    @Override
    public Conference getElementById(Integer id) throws RemoteException {
        return ConferenceTranslator.translate(
                Try.runFunction(model::getElementById, id)
                        .orThrow(thrower)
        );
    }

    @Override
    public List<Conference> getAll() throws RemoteException {
        List<Conference> transferableConferences = new ArrayList<>();
        List<ConferenceEntity> Conferences = model.getAll();

        for (ConferenceEntity ConferenceEntity : Conferences) {
            transferableConferences.add(ConferenceTranslator.translate(ConferenceEntity));
        }

        return transferableConferences;
    }

    @Override
    public Conference addEditionToConference(Conference conference, Edition edition) throws RemoteException {
        ConferenceEntity conferenceEntity = ConferenceTranslator.translate(conference);
        EditionEntity editionEntity = EditionTranslator.translate(edition);

        //  DOES NOT THROW EXCEPTION
        return ConferenceTranslator.translate(
                Try.runFunction(model::addEditionTo, conferenceEntity, editionEntity).getElement()
        );
    }
}
