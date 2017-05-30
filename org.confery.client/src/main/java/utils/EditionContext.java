package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import transfarable.Edition;
import transfarable.Session;
import transfarable.User;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class EditionContext {

    private Edition edition;
    private ObservableList<User> coChairs;
    private ObservableList<User> pcMembers;
    private ObservableList<Session> sessions;

    public EditionContext(Edition edition) {
        this.edition = edition;
        coChairs = FXCollections.observableArrayList();
        pcMembers = FXCollections.observableArrayList();
        sessions = FXCollections.observableArrayList();
    }

    public void updateEdition(Edition edition){
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
}
