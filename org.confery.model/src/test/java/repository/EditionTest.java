package repository;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.ConferenceEntity;
import domain.EditionEntity;
import exception.SystemException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

/**
 * @author Tiron Andreea-Ecaterina
 * @version 1.0
 */

public class EditionTest {

    private RepositoryInterface<EditionEntity, Integer> repositoryEdition;
    private RepositoryInterface<ConferenceEntity,Integer> repositoryConference;
    private DatabaseLoaderInterface loader;
    private Date date;

    @Before
    public void setUp() throws Exception {
        date = new Date();
        loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        repositoryEdition = new RepositoryEntity<>(EditionEntity.class, loader);
        repositoryConference = new RepositoryEntity<>(ConferenceEntity.class, loader);
        ConferenceEntity newConference = new ConferenceEntity("International Engineering Conference", "IEC");
        repositoryConference.add(newConference);
    }

    @After
    public void tearDown() throws Exception {
        loader.getFactory().close();
    }

    @Test
    @Transactional
    public void add() throws Exception {
        EditionEntity edition = new EditionEntity(date, date, "location", "bio", date, date, date, date);
        try {
            Integer idEdition = repositoryEdition.add(edition);
            edition.setConference(repositoryConference.getElementById(1));
            Assert.assertTrue(idEdition.equals(1) &&
                    edition.getLocation().equals("location") &&
                    edition.getBio().equals("bio") &&
                    edition.getAbstractDeadline().equals(date) &&
                    edition.getPaperDeadline().equals(date) &&
                    edition.getEvaluationDeadline().equals(date) &&
                    edition.getBiddingDeadline().equals(date) &&
                    edition.getStartDate().equals(date) &&
                    edition.getStartDate().equals(date) &&
                    edition.getConference().getAcronym().equals("IEC")
            );
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void update() throws Exception {
        EditionEntity edition = new EditionEntity(date, date, "location", "bio", date, date, date, date);
        EditionEntity update = new EditionEntity(date, date, "new location", "bio", date, date, date, date);
        try {
            repositoryEdition.add(edition);
            repositoryEdition.update(edition, update);
            EditionEntity result = repositoryEdition.getElementById(edition.getId());
            Assert.assertTrue(result.getLocation().equals("new location"));
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void delete() throws Exception {
        EditionEntity edition = new EditionEntity(date, date, "location", "bio", date, date, date, date);
        try {
            repositoryEdition.add(edition);
            // This test is here only to make sure that we have something in repository in order to delete.
            Assert.assertTrue(edition.getBio().equals(repositoryEdition.getElementById(1).getBio()));
            repositoryEdition.delete(edition.getId());
            Assert.assertTrue(repositoryEdition.getAll().isEmpty());
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAll() throws Exception {
        EditionEntity edition = new EditionEntity(date, date, "location", "bio", date, date, date, date);
        EditionEntity test = new EditionEntity(date, date, "new location", "bio", date, date, date, date);
        ConferenceEntity newConference = new ConferenceEntity("International Engineering Conference","IEC");
        try {
            repositoryConference.add(newConference);
            edition.setConference(repositoryConference.getElementById(1));
            test.setConference(repositoryConference.getElementById(1));
            repositoryEdition.add(edition);
            repositoryEdition.add(test);
            ArrayList<EditionEntity> result = new ArrayList<>(repositoryEdition.getAll());
            Assert.assertTrue(result.get(0).getId().equals(edition.getId()) &&
                    result.get(1).getId().equals(test.getId())
            );
            ConferenceEntity sameConference = repositoryConference.getElementById(1);
            Set<EditionEntity> editions = sameConference.getEditions();
            Assert.assertTrue(editions.size() == 2);
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getElementById() throws Exception {
        EditionEntity edition = new EditionEntity(date, date, "location", "bio", date, date, date, date);
        EditionEntity test = new EditionEntity(date, date, "new location", "bio", date, date, date, date);
        try {
            repositoryEdition.add(edition);
            repositoryEdition.add(test);
            EditionEntity result = repositoryEdition.getElementById(1);
            Assert.assertTrue(result.getId().equals(edition.getId()) &&
                    result.getLocation().equals(edition.getLocation()));
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }
}