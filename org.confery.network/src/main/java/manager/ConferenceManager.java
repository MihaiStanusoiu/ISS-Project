package manager;

import checker.ConferencePermissionChecker;
import domain.ConferenceEntity;
import protocol.ConferenceProtocol;
import service.ConferenceService;
import transfarable.Conference;
import transfarable.Edition;
import translator.ConferenceTranslator;

import java.rmi.RemoteException;

/**
 * Created by Mike on 5/30/2017.
 */
public class ConferenceManager extends GenericManager<Conference, Integer, ConferenceEntity> implements ConferenceService {

    public ConferenceManager(ConferenceProtocol model) throws RemoteException {
        super(model);
        checker = new ConferencePermissionChecker();
        translator = new ConferenceTranslator();
    }

    @Override
    public Conference addEditionToConference(Conference conference, Edition edition) throws RemoteException {
        // TODO
        return null;
    }

}
