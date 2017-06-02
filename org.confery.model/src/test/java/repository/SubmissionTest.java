package repository;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.EditionEntity;
import domain.SubmissionEntity;
import exception.SystemException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Tiron Andreea- Ecaterina
 * @version 1.0
 */

public class SubmissionTest {

    private RepositoryInterface<EditionEntity, Integer> repositoryEdition;
    private RepositoryInterface<SubmissionEntity, Integer> repositorySubmission;
    private DatabaseLoaderInterface loader;
    private Date date = new Date();

    @Before
    public void setUp() throws Exception {
        loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        repositorySubmission = new RepositoryEntity<>(SubmissionEntity.class, loader);
        repositoryEdition = new RepositoryEntity<>(EditionEntity.class, loader);
    }

    @After
    public void tearDown() throws Exception {
        loader.getFactory().close();
    }

    @Test
    @Transactional
    public void add() throws Exception {
        SubmissionEntity submission = new SubmissionEntity("thesis", "status", "abstractUrl", "fullPaperUrl");
        Integer idSubmission = repositorySubmission.add(submission);
        assertEquals((long) idSubmission, 1L);
    }

    @Test
    public void update() throws Exception {
        SubmissionEntity submission = new SubmissionEntity("thesis", "status", "abstractUrl", "fullPaperUrl");
        SubmissionEntity update = new SubmissionEntity("math", "status2", "abstractUrl", "fullPaperUrl");
        repositorySubmission.add(submission);
        repositorySubmission.update(submission, update);
        SubmissionEntity result = repositorySubmission.getElementById(submission.getId());
        assertEquals(result.getName(), "math");
    }

    @Test
    public void delete() throws Exception {
        SubmissionEntity submission = new SubmissionEntity("thesis", "status", "abstractUrl", "fullPaperUrl");
        try {
            repositorySubmission.add(submission);

            // This test is here only to make sure that we have something in repository in order to delete.
            assertTrue(submission.getName().equals(repositorySubmission.getElementById(1).getName()));
            repositorySubmission.delete(submission.getId());
            assertTrue(repositorySubmission.getAll().isEmpty());
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAll() throws Exception {
        SubmissionEntity submission = new SubmissionEntity("thesis", "status", "abstractUrl", "fullPaperUrl");
        SubmissionEntity test = new SubmissionEntity("thesisTest", "status", "abstractUrl", "fullPaperUrl");
        try {
            repositorySubmission.add(submission);
            repositorySubmission.add(test);
            ArrayList<SubmissionEntity> result = new ArrayList<>(repositorySubmission.getAll());
            assertTrue(result.get(0).getId().equals(submission.getId()) &&
                    result.get(1).getId().equals(test.getId()));
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getElementById() throws Exception {
        SubmissionEntity submission = new SubmissionEntity("thesis", "status", "abstractUrl", "fullPaperUrl");
        SubmissionEntity test = new SubmissionEntity("thesisTest", "status", "abstractUrl", "fullPaperUrl");
        try {
            repositorySubmission.add(submission);
            repositorySubmission.add(test);
            SubmissionEntity result = repositorySubmission.getElementById(1);
            assertTrue(result.getId().equals(submission.getId()) &&
                    result.getName().equals(submission.getName()));
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAllSubmissionsFromEdition() throws Exception {
        EditionEntity newEdition = new EditionEntity(date, date, "location", "bio", date, date, date, date);
        SubmissionEntity submission1 = new SubmissionEntity("thesis1", "status1", "abstractUrl1", "fullPaperUrl1");
        SubmissionEntity submission2 = new SubmissionEntity("thesis2", "status2", "abstractUrl2", "fullPaperUrl2");
        SubmissionEntity submission3 = new SubmissionEntity("thesis3", "status3", "abstractUrl3", "fullPaperUrl3");
        try {
            repositoryEdition.add(newEdition);
            submission1.setEdition(repositoryEdition.getElementById(1));
            submission2.setEdition(repositoryEdition.getElementById(1));
            submission3.setEdition(repositoryEdition.getElementById(1));
            repositorySubmission.add(submission1);
            repositorySubmission.add(submission2);
            repositorySubmission.add(submission3);
            EditionEntity sameEdition = repositoryEdition.getElementById(1);
            Set<SubmissionEntity> submissions = sameEdition.getSubmissions();
            assertTrue(submissions.size() == 3);
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }
}