package manager;

import checker.ConferencePermissionChecker;
import domain.ConferenceEntity;
import protocol.ConferenceProtocol;
import service.ConferenceService;
import transfarable.Conference;
import transfarable.Edition;
import translator.ConferenceTranslator;
import translator.EditionTranslator;

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

    private EditionTranslator editionTranslator;

    public ConferenceManager(ConferenceProtocol model) throws RemoteException {
        super(model);
        checker = new ConferencePermissionChecker();
        translator = new ConferenceTranslator();
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
    public Conference addEditionToConference(Conference conference, Edition edition) throws RemoteException {
        // TODO
        return null;
    }

}
