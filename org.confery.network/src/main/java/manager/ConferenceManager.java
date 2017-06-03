package manager;

import checker.ConferencePermissionChecker;
import domain.ConferenceEntity;
import protocol.ConferenceProtocol;
import protocol.LoginProtocol;
import service.ConferenceService;
import transfarable.Conference;
import transfarable.Edition;
import transfarable.NullUser;
import transfarable.User;
import translator.ConferenceTranslator;
import translator.EditionTranslator;
import translator.UserTranslator;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

import static utils.Conditional.basedOn;
import static utils.Try.runFunction;

/**
 * @author Andreea Tiron
 * @version 1.0
 */

public class ConferenceManager
        extends GenericManager<Conference, Integer, ConferenceEntity>
        implements ConferenceService {

    private final UserTranslator userTranslator;
    private EditionTranslator editionTranslator;
    protected ConferenceProtocol model;

    public ConferenceManager(ConferenceProtocol model, LoginProtocol loginProtocol) throws RemoteException {
        super(model, loginProtocol);
        this.model = model;
        checker = new ConferencePermissionChecker();
        translator = new ConferenceTranslator();
        userTranslator = new UserTranslator();
        editionTranslator = new EditionTranslator();
    }

    @Override
    public List<Edition> getEditionsOf(Conference conference) throws RemoteException {
        return getConferenceFromDatabase(conference).getEditions().stream()
                .map(entity -> editionTranslator.translate(entity))
                .collect(Collectors.toList());
    }

    @Override
    public Edition getLatestEdition(Conference conference) throws RemoteException {
        return editionTranslator.translate(getConferenceFromDatabase(conference).getLatestEdition());
    }

    @Override
    public Conference removeEditionFromConference(Conference conference, Edition edition) throws RemoteException {
        checkActiveUserPermissions(conference);
        return translator.translate(runFunction(model::removeEditionFrom, getConferenceFromDatabase(conference),
                editionTranslator.translate(edition)).orThrow(thrower));
    }

    private void checkActiveUserPermissions(Conference conference) throws RemoteException {
        checkActiveUserPermissions(Boolean.FALSE, conference);
    }

    private void checkActiveUserPermissions(Boolean alternative, Conference conference) throws RemoteException {
        basedOn(alternative || checker.isAllowed(getActiveUser()).toUpdate().theObject(getConferenceFromDatabase(conference)))
                .orThrow(new RemoteException("You don't have the required permissions to perform this action!"));
    }

    @Override
    public List<Conference> getConferencesOf(User user) throws RemoteException {
        return runFunction(model::getConferencesOf, userTranslator.translate(user))
                .orThrow(thrower).stream().map(entity -> translator.translate(entity)).collect(Collectors.toList());
    }

    @Override
    public User getChairOf(Conference conference) throws RemoteException {
        User chair = getConferenceChair(conference);
        return (chair == null) ? new NullUser() : chair;
    }

    private User getConferenceChair(Conference conference) throws RemoteException {
        return userTranslator.translate(runFunction(model::getChairOf,
                getConferenceFromDatabase(conference)).orThrow(thrower));
    }

    @Override
    public Conference addEditionToConference(Conference conference, Edition edition) throws RemoteException {
        checkActiveUserPermissions(getConferenceFromDatabase(conference).getEditions().size() == 0, conference);
        return translator.translate(runFunction(model::addEditionTo, translator.translate(conference),
                editionTranslator.translate(edition)).orThrow(thrower));
    }

    private ConferenceEntity getConferenceFromDatabase(Conference conference) throws RemoteException {
        return runFunction(model::getElementById, conference.getId()).orThrow(thrower);
    }

}
