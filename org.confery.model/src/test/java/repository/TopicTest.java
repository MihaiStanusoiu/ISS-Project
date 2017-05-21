package repository;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.TopicEntity;
import exception.SystemException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 * @author Tiron Andreea- Ecaterina
 * @version 1.0
 */
public class TopicTest {
    private RepositoryInterface<TopicEntity, Integer> repositoryTopic;
    private DatabaseLoaderInterface loader;

    @Before
    public void setUp() throws Exception {
        loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        repositoryTopic = new RepositoryEntity<>(TopicEntity.class, loader);
    }

    @After
    public void tearDown() throws Exception {
        loader.getFactory().close();
    }

    @Test
    @Transactional
    public void add() throws Exception {
        TopicEntity user = new TopicEntity("test");
        try {
            Integer idTopic = repositoryTopic.add(user);
            Assert.assertTrue(idTopic.equals(1) &&
                    user.getWord().equals("test")
            );
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void update() throws Exception {
        TopicEntity topic = new TopicEntity("test");
        TopicEntity update = new TopicEntity("testUpdate");
        try {
            repositoryTopic.add(topic);
            repositoryTopic.update(topic, update);
            TopicEntity result = repositoryTopic.getElementById(topic.getId());
            Assert.assertTrue(result.getWord().equals(update.getWord()));
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void delete() throws Exception {
        TopicEntity topic = new TopicEntity("test");
        try {
            repositoryTopic.add(topic);
            // This test is here only to make sure that we have something in repository in order to delete.
            Assert.assertTrue(topic.getWord().equals(repositoryTopic.getElementById(1).getWord()));
            repositoryTopic.delete(topic.getId());
            Assert.assertTrue(repositoryTopic.getAll().isEmpty());
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAll() throws Exception {
        TopicEntity topic = new TopicEntity("test");
        TopicEntity test = new TopicEntity("test");
        try {
            repositoryTopic.add(topic);
            repositoryTopic.add(test);
            ArrayList<TopicEntity> result = new ArrayList<>(repositoryTopic.getAll());
            Assert.assertTrue(result.get(0).getId().equals(topic.getId()) &&
                    result.get(1).getId().equals(test.getId()));
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getElementById() throws Exception {
        TopicEntity topic = new TopicEntity("test");
        TopicEntity test = new TopicEntity("word");
        try {
            repositoryTopic.add(topic);
            repositoryTopic.add(test);
            TopicEntity result = repositoryTopic.getElementById(1);
            Assert.assertTrue(result.getId().equals(topic.getId()) &&
                    result.getWord().equals(topic.getWord())
            );
        } catch (SystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

}