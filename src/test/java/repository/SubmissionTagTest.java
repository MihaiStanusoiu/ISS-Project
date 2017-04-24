package repository;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.SubmissionEntity;
import domain.SubmissionTagEntity;
import domain.SubmissionTagId;
import domain.TagEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 * Name:         SubmissionTagTest
 * Effect:       Test for SubmissionTag repository.
 *               <p>
 *                   Requires mySql server to run in the background.
 *               </p>
 * Date:         4/24/2017
 *
 * @author       Tanasie Luiza Maria
 * @version      1.0
 */
public class SubmissionTagTest {
    private RepositoryInterface<TagEntity, Integer> repositoryTag;
    private RepositoryInterface<SubmissionEntity, Integer> repositorySubmission;
    private RepositoryInterface<SubmissionTagEntity, SubmissionTagId> repositorySubmissionTag;
    private DatabaseLoaderInterface loader;

    @Before
    public void setUp() throws Exception {
        loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        repositoryTag = new RepositoryEntity<>(TagEntity.class, loader);
        repositorySubmission = new RepositoryEntity<>(SubmissionEntity.class,loader);
        repositorySubmissionTag = new RepositoryEntity<>(SubmissionTagEntity.class,loader);
        SubmissionEntity submission = new SubmissionEntity("test","status","abstract.doc","full.doc");
        TagEntity tag = new TagEntity("word");
        repositorySubmission.add(submission);
        repositoryTag.add(tag);
    }

    @After
    public void tearDown() throws Exception {
        loader.getFactory().close();
    }

    @Test
    @Transactional
    public void add() throws Exception {
        SubmissionTagEntity submissionTag = new SubmissionTagEntity(repositorySubmission.getElementById(1),
                repositoryTag.getElementById(1)
        );
        submissionTag.setId(new SubmissionTagId(1,1));
        try {
            SubmissionTagId idSubmissionTag = repositorySubmissionTag.add(submissionTag);
            Assert.assertTrue(idSubmissionTag.getIdSubmission().equals(1) &&
                    idSubmissionTag.getIdTag().equals(1) &&
                    submissionTag.getTag().getWord().equals("word") &&
                    submissionTag.getSubmission().getName().equals("test") &&
                    submissionTag.getSubmission().getAbstractUrl().equals("abstract.doc") &&
                    submissionTag.getSubmission().getFullPaperUrl().equals("full.doc") &&
                    submissionTag.getSubmission().getId().equals(1)
            );
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void update() throws Exception {
        SubmissionTagEntity submissionTag = new SubmissionTagEntity(repositorySubmission.getElementById(1),
                repositoryTag.getElementById(1)
        );
        submissionTag.setId(new SubmissionTagId(1,1));
        TagEntity tagUpdate = new TagEntity("updated");
        repositoryTag.add(tagUpdate);
        SubmissionTagEntity submissionTagUpdate = new SubmissionTagEntity(repositorySubmission.getElementById(1),
                repositoryTag.getElementById(2)
        );
        submissionTagUpdate.setId(new SubmissionTagId(2,1));

        try {
            repositorySubmissionTag.add(submissionTag);
            repositorySubmissionTag.update(submissionTag, submissionTagUpdate);
            SubmissionTagEntity result = repositorySubmissionTag.getElementById(submissionTag.getId());
            Assert.assertTrue(result.getId().getIdTag().equals(submissionTagUpdate.getId().getIdTag()) &&
                    result.getTag().getWord().equals("updated")
            );
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }


    @Test
    public void getElementById() throws Exception {
        SubmissionTagEntity submissionTag = new SubmissionTagEntity(repositorySubmission.getElementById(1),
                repositoryTag.getElementById(1)
        );
        submissionTag.setId(new SubmissionTagId(1,1));
        try {
            repositorySubmissionTag.add(submissionTag);
            SubmissionTagEntity result = repositorySubmissionTag.getElementById(new SubmissionTagId(1,1));
            Assert.assertTrue(result.getId().getIdTag().equals(submissionTag.getId().getIdTag()) &&
                    result.getId().getIdSubmission().equals((submissionTag.getId().getIdSubmission())) &&
                    result.getTag().getWord().equals("word") &&
                    result.getSubmission().getName().equals("test")
            );
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }


    @Test
    public void getAll() throws Exception {
        SubmissionTagEntity submissionTag = new SubmissionTagEntity(repositorySubmission.getElementById(1),
                repositoryTag.getElementById(1)
        );
        submissionTag.setId(new SubmissionTagId(1,1));
        TagEntity tagUpdate = new TagEntity("updated");
        repositoryTag.add(tagUpdate);
        SubmissionTagEntity submissionTagUpdate = new SubmissionTagEntity(repositorySubmission.getElementById(1),
                repositoryTag.getElementById(2)
        );
        submissionTagUpdate.setId(new SubmissionTagId(2,1));

        try {
            repositorySubmissionTag.add(submissionTag);
            repositorySubmissionTag.add(submissionTagUpdate);
            ArrayList<SubmissionTagEntity> result = new ArrayList<>(repositorySubmissionTag.getAll());
            Assert.assertTrue(result.get(0).getId().getIdTag().equals(submissionTag.getId().getIdTag()) &&
                    result.get(0).getId().getIdSubmission().equals(submissionTag.getId().getIdSubmission()) &&
                    result.get(1).getId().getIdTag().equals(submissionTagUpdate.getId().getIdTag()) &&
                    result.get(1).getId().getIdSubmission().equals(submissionTagUpdate.getId().getIdSubmission())
            );
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void delete() throws Exception {
        SubmissionTagEntity submissionTag = new SubmissionTagEntity(repositorySubmission.getElementById(1),
                repositoryTag.getElementById(1)
        );
        submissionTag.setId(new SubmissionTagId(1,1));
        try {
            repositorySubmissionTag.add(submissionTag);
            repositorySubmissionTag.delete(submissionTag.getId());
            Assert.assertTrue(repositorySubmissionTag.getAll().isEmpty());
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }
}