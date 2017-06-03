package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import method.SimpleMethod;
import org.apache.log4j.Logger;
import service.ConferenceService;
import service.EditionService;
import service.SessionService;
import transfarable.*;

import java.rmi.RemoteException;

import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class EditionContext {

    private Edition edition;
    private EditionService editionService;
    private User chair;
    private ObservableList<User> coChairs;
    private ObservableList<User> pcMembers;
    private ObservableList<Session> sessions;

    public EditionContext(Edition edition) {
        this.edition = edition;
        logger = Logger.getLogger(ConferenceContext.class);
        handler = exception -> logger.error(exception.getCause());
        coChairs = FXCollections.observableArrayList();
        pcMembers = FXCollections.observableArrayList();
        sessions = FXCollections.observableArrayList();
    }

    public void setChair(User chair) {
        this.chair = chair;
    }

    public void updateEdition(Edition edition) {
        this.edition = edition;
    }

    public Edition getEdition() {
        return edition;
    }

    public void addCoChair(User user) {
        coChairs.add(user);
    }

    public void addPcMember(User user) {
        pcMembers.add(user);
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public ObservableList<User> getCoChairs() {
        return coChairs;
    }

    public ObservableList<User> getPcMembers() {
        return pcMembers;
    }

    public ObservableList<Session> getSessions() {
        return sessions;
    }

    private ConferenceService conferenceService;

    public void setConferenceService(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    public void setEditionService(EditionService editionService) {
        this.editionService = editionService;
    }

    private SessionService sessionService;

    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    private static Logger logger;
    private SimpleMethod<RemoteException> handler;

    public void publish(Conference conference) {
        addEditionToDatabase();
        addChairToDatabase();
        addCoChairsToDatabase();
        addPcMembersToDatabase();
        addSessionsToDatabase();
        addEditionToConference(conference);
    }

    private void addEditionToConference(Conference conference) {
        runFunction(conferenceService::addEditionToConference, conference, edition).orHandle(handler);
    }

    private void addChairToDatabase() {
        runFunction(editionService::addChairToEdition, edition, chair).orHandle(handler);
    }

    private void addEditionToDatabase() {
        Integer id = runFunction(editionService::add, edition).orHandle(handler);
        edition = runFunction(editionService::getElementById, id).orHandle(handler);
    }

    private void addCoChairsToDatabase() {
        coChairs.forEach(coChair ->
                runFunction(editionService::addMemberToEdition, edition, coChair, MemberRoleTransferable.EDITION_CO_CHAIR).orHandle(handler));
    }

    private void addPcMembersToDatabase() {
        pcMembers.forEach(pcMember ->
                runFunction(editionService::addMemberToEdition, edition, pcMember, MemberRoleTransferable.EDITION_PC_MEMBER).orHandle(handler));
    }

    private void addSessionsToDatabase() {
        sessions.forEach(session ->
                runFunction(editionService::addSessionToEdition, edition, addSessionToDatabase(session)).orHandle(handler));
    }

    private Session addSessionToDatabase(Session session) {
        Integer idSession = runFunction(sessionService::add, session).orHandle(handler);
        return runFunction(sessionService::getElementById, idSession).orHandle(handler);
    }

}
