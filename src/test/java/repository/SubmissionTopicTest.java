package repository;


import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.SubmissionEntity;
import domain.SubmissionTopicEntity;
import domain.TopicEntity;
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
public class SubmissionTopicTest {
    private RepositoryInterface<SubmissionEntity, Integer> repositorySubmission;
    private RepositoryInterface<TopicEntity, Integer> repositoryTopic;
    private RepositoryInterface<SubmissionTopicEntity, Integer> repositoryTopicSubmission;
    private DatabaseLoaderInterface loader;

    @Before
    public void setUp() throws Exception {
        loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        repositoryTopicSubmission = new RepositoryEntity<>(SubmissionTopicEntity.class, loader);
        repositoryTopic = new RepositoryEntity<>(TopicEntity.class, loader);
        repositorySubmission = new RepositoryEntity<>(SubmissionEntity.class, loader);
    }

    @After
    public void tearDown() throws Exception {
        loader.getFactory().close();
    }

    @Test
    @Transactional
    public void add() throws Exception {
        SubmissionTopicEntity tagSubmission = new SubmissionTopicEntity();
        try {
            Integer id = repositoryTopicSubmission.add(tagSubmission);
            Assert.assertTrue(id.equals(1)
            );
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void delete() throws Exception {
        SubmissionTopicEntity tagSubmission = new SubmissionTopicEntity();
        try {
            repositoryTopicSubmission.add(tagSubmission);
            // This test is here only to make sure that we have something in repository in order to delete.
            repositoryTopicSubmission.delete(tagSubmission.getId());
            Assert.assertTrue(repositoryTopicSubmission.getAll().isEmpty());
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAll() throws Exception {
        SubmissionTopicEntity tagSubmission = new SubmissionTopicEntity();
        SubmissionTopicEntity tagSubmission2 = new SubmissionTopicEntity();
        try {
            repositoryTopicSubmission.add(tagSubmission);
            repositoryTopicSubmission.add(tagSubmission2);
            ArrayList<SubmissionTopicEntity> result = new ArrayList<>(repositoryTopicSubmission.getAll());
            Assert.assertTrue(result.get(0).getId().equals(tagSubmission.getId()) &&
                    result.get(1).getId().equals(tagSubmission2.getId()));
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getElementById() throws Exception {
        SubmissionTopicEntity tagSubmission = new SubmissionTopicEntity();
        SubmissionTopicEntity tagSubmission2 = new SubmissionTopicEntity();
        try {
            repositoryTopicSubmission.add(tagSubmission);
            repositoryTopicSubmission.add(tagSubmission2);
            SubmissionTopicEntity result = repositoryTopicSubmission.getElementById(1);
            Assert.assertTrue(result.getId().equals(tagSubmission.getId()));
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAllFromTopics() throws Exception {
        SubmissionTopicEntity tagSubmission1 = new SubmissionTopicEntity();
        SubmissionTopicEntity tagSubmission2 = new SubmissionTopicEntity();
        SubmissionTopicEntity tagSubmission3 = new SubmissionTopicEntity();
        TopicEntity topic = new TopicEntity("topic");

        try{
            repositoryTopic.add(topic);
            tagSubmission1.setTopic(repositoryTopic.getElementById(1));
            tagSubmission2.setTopic(repositoryTopic.getElementById(1));
            tagSubmission3.setTopic(repositoryTopic.getElementById(1));
            repositoryTopicSubmission.add(tagSubmission1);
            repositoryTopicSubmission.add(tagSubmission2);
            repositoryTopicSubmission.add(tagSubmission3);
            ArrayList<SubmissionTopicEntity> submissionTopics = new ArrayList<>(repositoryTopic.getElementById(1).getSubmissionTopics());
            Assert.assertTrue(submissionTopics.size() == 3);
        }catch (RepositoryException exception) {
        Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAllFromSubmissions() throws Exception{
        SubmissionTopicEntity tagSubmission1 = new SubmissionTopicEntity();
        SubmissionTopicEntity tagSubmission2 = new SubmissionTopicEntity();
        SubmissionTopicEntity tagSubmission3 = new SubmissionTopicEntity();
        SubmissionEntity submission = new SubmissionEntity("thesis", "status", "abstractUrl", "fullPaperUrl");
        try{
            repositorySubmission.add(submission);
            tagSubmission1.setSubmissionTopic(repositorySubmission.getElementById(1));
            tagSubmission2.setSubmissionTopic(repositorySubmission.getElementById(1));
            tagSubmission3.setSubmissionTopic(repositorySubmission.getElementById(1));
            repositoryTopicSubmission.add(tagSubmission1);
            repositoryTopicSubmission.add(tagSubmission2);
            repositoryTopicSubmission.add(tagSubmission3);
            ArrayList<SubmissionTopicEntity> submissionTopics = new ArrayList<>(repositorySubmission.getElementById(1).getSubmissionTopic());
            Assert.assertTrue(submissionTopics.size() == 3);
        }catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

}