package domain;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import model.ConferenceModel;
import org.junit.Test;
import protocol.ConferenceProtocol;

import static org.junit.Assert.assertEquals;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ConferenceEntityTest {

    @Test
    public void isGettingTheLatestEdition() throws Exception {
        // declarations:
        ConferenceEntity conference = new ConferenceEntity("Test", "test");
        EditionEntity edition = new EditionEntity("Test");
        DatabaseLoaderInterface loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        ConferenceProtocol conferenceModel = new ConferenceModel(loader);
        // preconditions:
        conferenceModel.add(conference);
        conference = conferenceModel.addEditionTo(conference, edition);
        // then:
        assertEquals(conference.getLatestEdition().getId(), edition.getId());
    }
}