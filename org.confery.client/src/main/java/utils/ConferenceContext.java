package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import transfarable.Conference;
import transfarable.Edition;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ConferenceContext {

    private Conference conference;
    private ObservableList<Edition> editions;
    private Edition selectedEdition;

    public ConferenceContext() {
        this(new Conference("", ""));
    }

    private ConferenceContext(Conference conference) {
        this.conference = conference;
        editions = FXCollections.observableArrayList();
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public Conference getConference() {
        return conference;
    }

    public void addEdition(Edition edition) {
        editions.add(edition);
        selectedEdition = edition;
    }

    public ObservableList<Edition> getEditions() {
        return editions;
    }

    public Edition getEdition() {
        return selectedEdition;
    }

    public void updateEdition(Edition edition) {
        editions.remove(selectedEdition);
        selectedEdition = edition;
        editions.add(selectedEdition);
    }

}
