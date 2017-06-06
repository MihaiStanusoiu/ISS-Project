package model;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.TagEntity;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

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


    @Test
    public void isGettingTagByWord() throws Exception {
        // declaration:
        DatabaseLoaderInterface loader =
                new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        TagModel model = new TagModel(loader);
        TagEntity first = new TagEntity("test");
        model.add(first);
        // then:
        assertTrue(model.getTagByWord("test").getId().equals(1));
        assertTrue(model.getTagByWord("no").getWord().equals("none"));
    }


}