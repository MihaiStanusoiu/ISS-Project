package repository;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.EditionEntity;
import domain.ConferenceEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

/**
 * Name:         {ClassName}
 * Effect:       {ClassEffect}
 * Date:         22/04/2017
 * Tested:       False
 *
 * @author Tiron Andreea- Ecaterina
 * @version 1.0
 */
public class EditionTest {

    private RepositoryInterface<EditionEntity, Integer> repositoryEdition;
    private RepositoryInterface<ConferenceEntity,Integer> repositoryConference;
    private DatabaseLoaderInterface loader;
    String dateString = "2017/03/13";
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    Date date;

    @Before
    public void setUp() throws Exception {
        loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        repositoryEdition = new RepositoryEntity<>(EditionEntity.class, loader);
        repositoryConference = new RepositoryEntity<>(ConferenceEntity.class,loader);
        ConferenceEntity new_conference = new ConferenceEntity("International Engineering Conference","IEC");
        try {
            repositoryConference.add(new_conference);
        }catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
        try{
            date = dateFormat.parse(dateString);
        }catch (ParseException ex){}
    }

    @After
    public void tearDown() throws Exception {
        loader.getFactory().close();
    }

    @Test
    @Transactional
    public void add() throws Exception {
        EditionEntity user = new EditionEntity(date,date,"location","bio",date,date,date,date);
        try {
            Integer idUser = repositoryEdition.add(user);
            user.setConference(repositoryConference.getElementById(1));
            Assert.assertTrue(idUser.equals(1) &&
                    user.getLocation().equals("location") &&
                    user.getBio().equals("bio") &&
                    user.getAbstractDeadline().equals(date) &&
                    user.getPaperDeadline().equals(date) &&
                    user.getEvaluationDeadline().equals(date) &&
                    user.getBiddingDeadline().equals(date) &&
                    user.getStartDate().equals(date) &&
                    user.getStartDate().equals(date) &&
                    user.getConference().getAcronym().equals("IEC")
            );
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void update() throws Exception {
        EditionEntity user = new EditionEntity(date,date,"location","bio",date,date,date,date);
        EditionEntity update = new EditionEntity(date,date,"new location","bio",date,date,date,date);
        try {
            repositoryEdition.add(user);
            repositoryEdition.update(user, update);
            EditionEntity result = repositoryEdition.getElementById(user.getId());
            Assert.assertTrue(result.getLocation().equals("new location"));
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void delete() throws Exception {
        EditionEntity user = new EditionEntity(date,date,"location","bio",date,date,date,date);
        try {
            repositoryEdition.add(user);

            // This test is here only to make sure that we have something in repository in order to delete.
            Assert.assertTrue(user.getBio().equals(repositoryEdition.getElementById(1).getBio()));
            repositoryEdition.delete(user.getId());
            Assert.assertTrue(repositoryEdition.getAll().isEmpty());
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAll() throws Exception {
        EditionEntity user = new EditionEntity(date,date,"location","bio",date,date,date,date);
        EditionEntity test = new EditionEntity(date,date,"new location","bio",date,date,date,date);
        ConferenceEntity new_conference = new ConferenceEntity("International Engineering Conference","IEC");

        try {
            repositoryConference.add(new_conference);
            user.setConference(repositoryConference.getElementById(1));
            test.setConference(repositoryConference.getElementById(1));
            repositoryEdition.add(user);
            repositoryEdition.add(test);
            ArrayList<EditionEntity> result = new ArrayList<>(repositoryEdition.getAll());
            Assert.assertTrue(result.get(0).getId().equals(user.getId()) &&
                    result.get(1).getId().equals(test.getId())
            );
            ConferenceEntity same_conference = repositoryConference.getElementById(1);
            Set<EditionEntity> editions = same_conference.getEditions();
            Assert.assertTrue(editions.size()==2);
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getElementById() throws Exception {
        EditionEntity user = new EditionEntity(date,date,"location","bio",date,date,date,date);
        EditionEntity test = new EditionEntity(date,date,"new location","bio",date,date,date,date);
        try {
            repositoryEdition.add(user);
            repositoryEdition.add(test);
            EditionEntity result = repositoryEdition.getElementById(1);
            Assert.assertTrue(result.getId().equals(user.getId()) &&
                    result.getLocation().equals(user.getLocation()));
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }


}