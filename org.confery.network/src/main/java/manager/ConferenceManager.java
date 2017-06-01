package manager;

import checker.ConferencePermissionChecker;
import domain.ConferenceEntity;
import domain.EditionEntity;
import protocol.ConferenceProtocol;
import protocol.LoginProtocol;
import service.ConferenceService;
import transfarable.Conference;
import transfarable.Edition;
import transfarable.User;
import translator.ConferenceTranslator;
import translator.EditionTranslator;
import translator.UserTranslator;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

import static utils.Try.runFunction;

/**
 * Created by Mike on 5/30/2017.
 */
public class ConferenceManager
        extends GenericManager<Conference, Integer, ConferenceEntity>
        implements ConferenceService {

    private final UserTranslator userTranslator;
    private EditionTranslator editionTranslator;

    public ConferenceManager(ConferenceProtocol model, LoginProtocol loginProtocol) throws RemoteException {
        super(model, loginProtocol);
        checker = new ConferencePermissionChecker();
        translator = new ConferenceTranslator();
        userTranslator = new UserTranslator();
        editionTranslator = new EditionTranslator();
    }

    @Override
    public List<Edition> getEditionsOf(Conference conference) throws RemoteException {
        return runFunction(model::getElementById, conference.getId()).orThrow(thrower)
                .getEditions().stream()
                .map(entity -> editionTranslator.translate(entity))
                .collect(Collectors.toList());
    }

    @Override
    public Edition getLatestEdition(Conference conference) throws RemoteException {
        EditionEntity entity = runFunction(model::getElementById, conference.getId()).orThrow(thrower)
                .getLatestEdition();
        return editionTranslator.translate(entity);
    }

    public List<Conference> getConferencesOf(User user) throws RemoteException {
        // TODO
        return null;
    }

    private Boolean isChair(User user, EditionEntity edition) throws RemoteException {
        return runFunction(edition::getChair).orThrow(thrower).equals(userTranslator.translate(user));
    }

    @Override
    public Conference addEditionToConference(Conference conference, Edition edition) throws RemoteException {
        // TODO
        return null;
    }

}
