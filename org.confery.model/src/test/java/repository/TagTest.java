package repository;

import database.DatabaseLoaderFactory;
import database.DatabaseLoaderInterface;
import database.DatabaseLoaderType;
import domain.TagEntity;
import exception.RepositoryException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 * Name:         {ClassName}
 * Effect:       {ClassEffect}
 * Date:         01/05/2017
 * @author       Tiron Andreea- Ecaterina
 * @version      1.0
 */
public class TagTest {
    private RepositoryInterface<TagEntity, Integer> repositoryTag;
    private DatabaseLoaderInterface loader;

    @Before
    public void setUp() throws Exception {
        loader = new DatabaseLoaderFactory().getLoader(DatabaseLoaderType.TEST);
        repositoryTag = new RepositoryEntity<>(TagEntity.class, loader);
    }

    @After
    public void tearDown() throws Exception {
        loader.getFactory().close();
    }

    @Test
    @Transactional
    public void add() throws Exception {
        TagEntity user = new TagEntity("test");
        try {
            Integer idTag = repositoryTag.add(user);
            Assert.assertTrue(idTag.equals(1) &&
                    user.getWord().equals("test")
            );
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void update() throws Exception {
        TagEntity tag = new TagEntity("test");
        TagEntity update = new TagEntity("testUpdate");
        try {
            repositoryTag.add(tag);
            repositoryTag.update(tag, update);
            TagEntity result = repositoryTag.getElementById(tag.getId());
            Assert.assertTrue(result.getWord().equals(update.getWord()));
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void delete() throws Exception {
        TagEntity tag = new TagEntity("test");
        try {
            repositoryTag.add(tag);
            // This test is here only to make sure that we have something in repository in order to delete.
            Assert.assertTrue(tag.getWord().equals(repositoryTag.getElementById(1).getWord()));
            repositoryTag.delete(tag.getId());
            Assert.assertTrue(repositoryTag.getAll().isEmpty());
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getAll() throws Exception {
        TagEntity tag = new TagEntity("test");
        TagEntity test = new TagEntity("test");
        try {
            repositoryTag.add(tag);
            repositoryTag.add(test);
            ArrayList<TagEntity> result = new ArrayList<>(repositoryTag.getAll());
            Assert.assertTrue(result.get(0).getId().equals(tag.getId()) &&
                    result.get(1).getId().equals(test.getId()));
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

    @Test
    public void getElementById() throws Exception {
        TagEntity tag = new TagEntity("test");
        TagEntity test = new TagEntity("word");
        try {
            repositoryTag.add(tag);
            repositoryTag.add(test);
            TagEntity result = repositoryTag.getElementById(1);
            Assert.assertTrue(result.getId().equals(tag.getId()) &&
                    result.getWord().equals(tag.getWord())
            );
        } catch (RepositoryException exception) {
            Assert.assertEquals(exception.getMessage(), "Unable to add element to database!");
        }
    }

}