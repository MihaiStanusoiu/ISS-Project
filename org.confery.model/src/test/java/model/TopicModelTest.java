package model;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.TopicEntity;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class TopicModelTest {

    @Test
    public void isAddingTopic() throws Exception {
        // declaration:
        DatabaseLoaderInterface loader =
                new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        TopicModel model = new TopicModel(loader);
        TopicEntity first = new TopicEntity("test");
        // then:
        assertTrue(model.add(first).equals(1));
    }

    @Test
    public void isGettingTopicByWord() throws Exception {
        // declaration:
        DatabaseLoaderInterface loader =
                new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        TopicModel model = new TopicModel(loader);
        TopicEntity first = new TopicEntity("test");
        model.add(first);
        // then:
        assertTrue(model.getTopicByWord("test").getId().equals(1));
        assertTrue(model.getTopicByWord("no").getWord().equals("none"));
    }

    @Test
    public void isAddingTopicAgain() throws Exception {
        // declaration:
        DatabaseLoaderInterface loader =
                new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        TopicModel model = new TopicModel(loader);
        TopicEntity first = new TopicEntity("test");
        TopicEntity second = new TopicEntity("test");
        // then:
        assertEquals((long)model.add(first), 1L);
    }

}