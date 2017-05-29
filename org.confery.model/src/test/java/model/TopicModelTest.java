package model;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.TopicEntity;
import org.junit.Assert;
import org.junit.Test;
import utils.Try;

import static utils.Try.runFunction;

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
        TopicEntity second = new TopicEntity("test");
        // then:
        Assert.assertTrue(model.add(first).equals(1));
        Try.runMethod(() -> Assert.assertTrue(model.add(second).equals(2)))
                .orHandle(exception -> Assert.assertTrue(exception.getMessage()
                        .equals("Error test is already in our system.")));
    }

}