package repository;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.ConferenceEntity;
import domain.EditionEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;

/**
 * Name:         {ClassName}
 * Effect:       {ClassEffect}
 * Date:         22/04/2017
 * @author       Tiron Andreea- Ecaterina
 * @version      1.0
 */

public class ConferenceTest {
    private RepositoryInterface<EditionEntity, Integer> repositoryEdition;
    private RepositoryInterface<ConferenceEntity, Integer> repositoryEditionConfiguration;
    private DatabaseLoaderInterface loader;
    private Date date;

    @Before
    public void setUp() throws Exception {
        loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        repositoryEditionConfiguration = new RepositoryEntity<>(ConferenceEntity.class, loader);
        repositoryEdition = new RepositoryEntity<>(EditionEntity.class, loader);
    }

    @After
    public void tearDown() throws Exception {
        loader.getFactory().close();
    }

    @Test
    @Transactional
    public void add() throws Exception {
        ConferenceEntity conference = new ConferenceEntity("International Engineering Conference", "IEC");
        try {
            Integer idConference = repositoryEditionConfiguration.add(conference);
            Assert.assertTrue(idConference.equals(1) &&
                    conference.getName().equals("International Engineering Conference") &&
                    conference.getAcronym().equals("IEC")
            );
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void update() throws Exception {
        ConferenceEntity conference = new ConferenceEntity("International Engineering Conference", "IEC");
        ConferenceEntity update = new ConferenceEntity("Artificial Intelligence", "AI");
        try {
            repositoryEditionConfiguration.add(conference);
            repositoryEditionConfiguration.update(conference, update);
            ConferenceEntity result = repositoryEditionConfiguration.getElementById(conference.getId());
            Assert.assertTrue(result.getAcronym().equals("AI"));
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void delete() throws Exception {
        ConferenceEntity conference = new ConferenceEntity("International Engineering Conference", "IEC");
        try {
            repositoryEditionConfiguration.add(conference);

            // This test is here only to make sure that we have something in repository in order to delete.
            Assert.assertTrue(conference.getAcronym().equals(repositoryEditionConfiguration.getElementById(1).getAcronym()));
            repositoryEditionConfiguration.delete(conference.getId());
            Assert.assertTrue(repositoryEditionConfiguration.getAll().isEmpty());
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAll() throws Exception {
        ConferenceEntity conference = new ConferenceEntity("International Engineering Conference", "IEC");
        ConferenceEntity test = new ConferenceEntity("Artificial Intelligence", "AI");
        try {
            repositoryEditionConfiguration.add(conference);
            repositoryEditionConfiguration.add(test);
            ArrayList<ConferenceEntity> result = new ArrayList<>(repositoryEditionConfiguration.getAll());
            Assert.assertTrue(result.get(0).getId().equals(conference.getId()) &&
                    result.get(1).getId().equals(test.getId()));
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getElementById() throws Exception {
        ConferenceEntity conference = new ConferenceEntity("International Engineering Conference", "IEC");
        ConferenceEntity test = new ConferenceEntity("Artificial Intelligence", "AI");
        try {
            repositoryEditionConfiguration.add(conference);
            repositoryEditionConfiguration.add(test);
            ConferenceEntity result = repositoryEditionConfiguration.getElementById(1);
            Assert.assertTrue(result.getId().equals(conference.getId()) &&
                    result.getName().equals(conference.getName()));
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAllEditions() throws Exception {
        ConferenceEntity conference = new ConferenceEntity("International Engineering Conference", "IEC");
        EditionEntity edition1 = new EditionEntity(date, date, "location1", "bio1", date, date, date, date);
        EditionEntity edition2 = new EditionEntity(date, date, "location2", "bio2", date, date, date, date);
        EditionEntity edition3 = new EditionEntity(date, date, "location3", "bio3", date, date, date, date);
        try{
            repositoryEditionConfiguration.add(conference);
            edition1.setConference(conference);
            repositoryEdition.add(edition1);
            edition2.setConference(conference);
            repositoryEdition.add(edition2);
            edition3.setConference(conference);
            repositoryEdition.add(edition3);

            Boolean isNote = false;
            Boolean isNote2 = false;
            Boolean isNote3 = false;
            ArrayList<EditionEntity> editions = new ArrayList<>(repositoryEditionConfiguration.getElementById(1).getEditions());
            for(Integer index = 0; index < editions.size(); index++) {
                if (editions.get(index).getBio().equals("bio1"))
                    isNote = true;
                if (editions.get(index).getBio().equals("bio2"))
                    isNote2 = true;
                if (editions.get(index).getBio().equals("bio3"))
                    isNote3 = true;
            }
            Assert.assertTrue(isNote.equals(true) && isNote2.equals(true) && isNote3.equals(true));
            Assert.assertTrue(editions.size() == 3);
        }catch(RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }
}