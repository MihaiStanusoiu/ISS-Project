package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import method.SimpleMethod;
import org.apache.log4j.Logger;
import service.ConferenceService;
import service.EditionService;
import service.SessionService;
import transfarable.Conference;
import transfarable.Edition;
import transfarable.User;

import java.rmi.RemoteException;
import java.util.stream.Collectors;

import static utils.Conditional.basedOn;
import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ConferenceContext {

    private Conference conference;
    private ObservableList<EditionContext> editionContexts;
    private EditionContext selectedEditionContext;
    private User chair;
    private ConferenceService conferenceService;
    private static Logger logger;
    private EditionService editionService;
    private SessionService sessionService;
    private SimpleMethod<RemoteException> handler;

    public ConferenceContext() {
        this(new Conference("", ""));
    }

    private ConferenceContext(Conference conference) {
        this.conference = conference;
        logger = Logger.getLogger(ConferenceContext.class);
        handler = exception -> logger.error(exception.getCause());
        editionContexts = FXCollections.observableArrayList();
    }

    public void setChair(User chair) {
        this.chair = chair;
    }

    public User getChair() {
        return chair;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public Conference getConference() {
        return conference;
    }

    public void addEdition(Edition edition) {
        selectedEditionContext = new EditionContext(edition);
        editionContexts.add(selectedEditionContext);
    }

    public ObservableList<Edition> getEditions() {
        return FXCollections.observableArrayList(editionContexts
                .stream().map(EditionContext::getEdition)
                .collect(Collectors.toList()));
    }

    public Edition getEdition() {
        return selectedEditionContext.getEdition();
    }

    @SuppressWarnings("unused")
    public void updateEdition(Edition edition) {
        editionContexts.remove(selectedEditionContext);
        selectedEditionContext = new EditionContext(edition);
        editionContexts.add(selectedEditionContext);
    }
    public EditionContext getEditionContext() {
        return selectedEditionContext;
    }

    public void updateEditionContext(EditionContext context) {
        editionContexts.remove(context);
        selectedEditionContext = context;
        editionContexts.add(selectedEditionContext);
    }

    public ObservableList<EditionContext> getEditionContexts() {
        return editionContexts;
    }

    public void setConferenceService(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }


    public void setEditionService(EditionService editionService) {
        this.editionService = editionService;
    }

    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public void publish() throws RemoteException {
        basedOn(chair != null).orThrow(new RemoteException("You need an account in order to create something!")) ;
        publishAllEditions(addConferenceToDatabase());
    }

    private void publishAllEditions(Conference item) {
        editionContexts.forEach(editionContext -> editionContext.setChair(chair));
        editionContexts.forEach(editionContext -> editionContext.setConferenceService(conferenceService));
        editionContexts.forEach(editionContext -> editionContext.setSessionService(sessionService));
        editionContexts.forEach(editionContext -> editionContext.setEditionService(editionService));
        editionContexts.forEach(editionContext -> editionContext.publish(item));
    }

    private Conference addConferenceToDatabase() {
        Integer id = runFunction(conferenceService::add, conference).orHandle(handler);
        return runFunction(conferenceService::getElementById, id).orHandle(handler);
    }

}
