package model;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.TagEntity;
import org.junit.Assert;
import org.junit.Test;

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
        // then:
        Assert.assertTrue(model.add(first).equals(1));
    }

}