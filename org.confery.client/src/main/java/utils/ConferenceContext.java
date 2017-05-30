package utils;

import transfarable.Conference;
import transfarable.Edition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ConferenceContext {

    private Conference conference;
    private List<Edition> editions;
    private Edition selectedEdition;

    public ConferenceContext() {
        this(new Conference("", ""));
    }

    private ConferenceContext(Conference conference) {
        this.conference = conference;
        editions = new ArrayList<>();
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

    public List<Edition> getEditions() {
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
