package repository;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.SubmissionEntity;
import domain.SubmissionTagEntity;
import domain.TagEntity;
import exception.SystemException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 * Name:         {ClassName}
 * Effect:       {ClassEffect}
 * Date:         12/05/2017
 * @author       Tiron Andreea- Ecaterina
 * @version      1.0
 */
public class SubmissionTagTest {
    private RepositoryInterface<SubmissionEntity, Integer> repositorySubmission;
    private RepositoryInterface<TagEntity, Integer> repositoryTag;
    private RepositoryInterface<SubmissionTagEntity, Integer> repositoryTagSubmission;
    private DatabaseLoaderInterface loader;

    @Before
    public void setUp() throws Exception {
        loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        repositoryTagSubmission = new RepositoryEntity<>(SubmissionTagEntity.class, loader);
        repositoryTag = new RepositoryEntity<>(TagEntity.class, loader);
        repositorySubmission = new RepositoryEntity<>(SubmissionEntity.class, loader);
    }

    @After
    public void tearDown() throws Exception {
        loader.getFactory().close();
    }

    @Test
    @Transactional
    public void add() throws Exception {
        SubmissionTagEntity tagSubmission = new SubmissionTagEntity();
        try {
            Integer id = repositoryTagSubmission.add(tagSubmission);
            Assert.assertTrue(id.equals(1)
            );
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void delete() throws Exception {
        SubmissionTagEntity tagSubmission = new SubmissionTagEntity();
        try {
            repositoryTagSubmission.add(tagSubmission);
            // This test is here only to make sure that we have something in repository in order to delete.
            repositoryTagSubmission.delete(tagSubmission.getId());
            Assert.assertTrue(repositoryTagSubmission.getAll().isEmpty());
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAll() throws Exception {
        SubmissionTagEntity tagSubmission = new SubmissionTagEntity();
        SubmissionTagEntity tagSubmission2 = new SubmissionTagEntity();
        try {
            repositoryTagSubmission.add(tagSubmission);
            repositoryTagSubmission.add(tagSubmission2);
            ArrayList<SubmissionTagEntity> result = new ArrayList<>(repositoryTagSubmission.getAll());
            Assert.assertTrue(result.get(0).getId().equals(tagSubmission.getId()) &&
                    result.get(1).getId().equals(tagSubmission2.getId()));
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getElementById() throws Exception {
        SubmissionTagEntity tagSubmission = new SubmissionTagEntity();
        SubmissionTagEntity tagSubmission2 = new SubmissionTagEntity();
        try {
            repositoryTagSubmission.add(tagSubmission);
            repositoryTagSubmission.add(tagSubmission2);
            SubmissionTagEntity result = repositoryTagSubmission.getElementById(1);
            Assert.assertTrue(result.getId().equals(tagSubmission.getId()));
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAllFromTags() throws Exception {
        SubmissionTagEntity tagSubmission1 = new SubmissionTagEntity();
        SubmissionTagEntity tagSubmission2 = new SubmissionTagEntity();
        SubmissionTagEntity tagSubmission3 = new SubmissionTagEntity();
        TagEntity topic = new TagEntity("topic");

        try{
            repositoryTag.add(topic);
            tagSubmission1.setTag(repositoryTag.getElementById(1));
            tagSubmission2.setTag(repositoryTag.getElementById(1));
            tagSubmission3.setTag(repositoryTag.getElementById(1));
            repositoryTagSubmission.add(tagSubmission1);
            repositoryTagSubmission.add(tagSubmission2);
            repositoryTagSubmission.add(tagSubmission3);
            ArrayList<SubmissionTagEntity> submissionTopics = new ArrayList<>(repositoryTag.getElementById(1).getSubmissionTags());
            Assert.assertTrue(submissionTopics.size() == 3);
        }catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAllFromSubmissions() throws Exception{
        SubmissionTagEntity tagSubmission1 = new SubmissionTagEntity();
        SubmissionTagEntity tagSubmission2 = new SubmissionTagEntity();
        SubmissionTagEntity tagSubmission3 = new SubmissionTagEntity();
        SubmissionEntity submission = new SubmissionEntity("thesis","status","abstractUrl","fullPaperUrl");
        try{
            repositorySubmission.add(submission);
            tagSubmission1.setSubmission(repositorySubmission.getElementById(1));
            tagSubmission2.setSubmission(repositorySubmission.getElementById(1));
            tagSubmission3.setSubmission(repositorySubmission.getElementById(1));
            repositoryTagSubmission.add(tagSubmission1);
            repositoryTagSubmission.add(tagSubmission2);
            repositoryTagSubmission.add(tagSubmission3);
            ArrayList<SubmissionTagEntity> submissionTopics = new ArrayList<>(repositorySubmission.getElementById(1).getSubmissionTags());
            Assert.assertTrue(submissionTopics.size() == 3);
        }catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

}