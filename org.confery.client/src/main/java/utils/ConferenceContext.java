package utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import transfarable.Conference;
import transfarable.Edition;

import java.util.stream.Collectors;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ConferenceContext {

    private Conference conference;
    private ObservableList<EditionContext> editionContexts;
    private EditionContext selectedEditionContext;

    public ConferenceContext() {
        this(new Conference("", ""));
    }

    private ConferenceContext(Conference conference) {
        this.conference = conference;
        editionContexts = FXCollections.observableArrayList();
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public Conference getConference() {
        return conference;
    }

    public void addEdition(Edition edition) {;
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

    public void updateEdition(Edition edition) {
        editionContexts.remove(selectedEditionContext);
        selectedEditionContext = new EditionContext(edition);
        editionContexts.add(selectedEditionContext);
    }

    public EditionContext getEditionContext() {
        return selectedEditionContext;
    }

    public void updateEditionContext(EditionContext context) {
        editionContexts.remove(selectedEditionContext);
        selectedEditionContext = context;
        editionContexts.add(selectedEditionContext);
    }

}
