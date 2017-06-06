package domain;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import model.ConferenceModel;
import org.junit.Test;
import protocol.ConferenceProtocol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
        Integer idConference = conferenceModel.add(conference);
        edition = conferenceModel.addEditionTo(conference, edition);
        // then:
        assertEquals(conferenceModel.getElementById(idConference).getLatestEdition().getId(), edition.getId());
    }

    @Test
    public void testEquals(){
        ConferenceEntity conference = new ConferenceEntity("Conference in biology");
        ConferenceEntity conference2 = new ConferenceEntity("Conference in biology");
        assertEquals(conference,conference2);
        ConferenceEntity conference3 = new ConferenceEntity("Conference in science");
        assertNotEquals(conference,conference3);
    }

}