package model;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.TagEntity;
import org.junit.Assert;
import org.junit.Test;
import utils.Try;

import static utils.Try.runFunction;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class TagModelTest {

    @Test
    public void isAddingTag() throws Exception {
        // declaration:
        DatabaseLoaderInterface loader =
                new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        TagModel model = new TagModel(loader);
        TagEntity first = new TagEntity("test");
        TagEntity second = new TagEntity("test");
        // then:
        Assert.assertTrue(model.add(first).equals(1));
        Try.runMethod(() -> Assert.assertTrue(model.add(second).equals(2)))
                .orHandle(exception -> Assert.assertTrue(exception
                .getMessage().equals("Error test is already in our system.")));
    }

}